/*
* Java Bean Generator
* Ebauche pour le projet d'application 2016
* @author : Blixit, Tisba F
* @class : 
* 
 */
package jbgenerator.lib;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream; 
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;  
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author blixit
 */
public class JBWriter implements Runnable{ 
     
    /**
     * File Handle 
     */
    private OutputStream out;
    
    /**
     * A {@link JBGenerator} object contains required settings such as paths and policies. 
     */
    private JBGenerator gen;
    
    /**
     * The {@link JBContent} object to convert into a java class
     */
    private JBContent jbc;
    
    /**
     * The number of spaces to make an indentation.
     */
    private int indentation;
    
    /**
     * The space string preceding each line
     */
    private String indentationString;
    
    /**
     * The default indentation 
     */
    static final int defaultIndentation = 2;
    
    /**
     * Constructeur
     * @param gen a JBGenerator object
     * @param jbc the JBContent to convert into a java class
     * @throws UnsupportedEncodingException
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public JBWriter(JBGenerator gen, JBContent jbc) throws UnsupportedEncodingException, FileNotFoundException, IOException{
                
        File file = new File(gen.getOutDir()+jbc.getName()+".java");
        if( ! file.exists())
            file.createNewFile();
        
        this.out = new FileOutputStream(file);
        this.gen = gen;
        this.jbc = jbc;
        this.indentation = 0;
        this.indentationString = "";
    }
    
    /**
     * Increase the current indentation and update the indentation string.
     */
    private void indent(){
        indentation += defaultIndentation; 
        setIndentString();
    }
    
    /**
     * Decrease the current indentation and update the indentation string.
     */
    private void deindent(){
        indentation = indentation >= defaultIndentation ? indentation-defaultIndentation : 0; 
        setIndentString();
    }
    
    /**
     * Update the indentation string. 
     */
    private void setIndentString(){ indentationString = indentation <= 0 ? "" : new String(new char[indentation]).replace('\0', ' '); }
    
    /**
     * Write a line with the given string as content
     * @param str the line to write
     * @throws IOException 
     */
    private void writeLine( String str) throws IOException{
         
        str = indentationString+str+"\n";
        
        this.out.write(str.getBytes());
    }
        
    /**
     * Write the project package line.
     * @throws IOException 
     */
    private void writeProjectPackage() throws IOException{ 
        writeLine("package "+gen.getProjectPackage()+";");
    }
    
    /**
     * Write the dependencies lines (imports).
     * @throws IOException 
     */
    private void writeDependenciesPackage() throws IOException{  
        List<String> dependencies = new ArrayList<>();
        
        for(JBProperty p : jbc.getPropertiesList()){
            //if the property defines a typeToUse (a generated class ), there's no package to import
            if( ! p.getTypeToUse().isEmpty())
                continue;
            String dep = p.getType().get().getPackage().getName()+"."+p.getType().get().getSimpleName();
            if( ! dependencies.contains(dep)){
                dependencies.add(dep);
            }
        }
        for(String dep : dependencies){
            writeLine("import "+dep+";");
        }
    }
    
    /**
     * Write the class body.
     * @throws IOException 
     */
    private void writeClass() throws IOException{
        if(gen.getGenerateDoc()){ 
            writeLine("/**");
            writeLine("* Class "+jbc.getName());
            writeLine("* @author : ");
            writeLine("*/");
        }
        writeLine("public class "+jbc.getName()+"{");
        indent();
        writeProperties();
        writeLine("");
        writeConstructors();
        writeLine("");
        writeGSetters();
        deindent();
        writeLine("}");
    }
    
    /**
     * Write class properties.
     * @throws IOException 
     */
    private void writeProperties() throws IOException{
        for(JBProperty p : jbc.getPropertiesList()){            
            if(gen.getGenerateDoc()){ 
                writeLine("/**");
                writeLine("* A {@link "+(p.getTypeToUse().isEmpty() ? p.getType().get().getSimpleName() : p.getTypeToUse())+"} which represents "+p.getName()+"."); 
                writeLine("*/");
            }
            if(p.getTypeToUse().isEmpty())
                writeLine("private "+p.getType().get().getSimpleName()+" "+p.getName()+";");
            else
                writeLine("private "+p.getTypeToUse()+" "+p.getName()+";");
        }
    }
    
    /**
     * Write the default constructor.
     * @throws IOException 
     */
    private void writeDefaultConstructor() throws IOException{
        if(gen.getGenerateDoc()){ 
            writeLine("/**");
            writeLine("* Default constructor for "+jbc.getName()); 
            writeLine("*/");
        }
        writeLine("public "+jbc.getName()+"(){");
        indent();   
        for(JBProperty p : jbc.getPropertiesList()){
            String str = "this."+p.getName()+" = ";
            if(p.getTypeToUse().isEmpty() && p.getType().get().getSimpleName().equals("String"))
                str += "\""+p.getDefaultValue()+"\"";
            else
                str += p.getDefaultValue().isEmpty() ? "null" : p.getDefaultValue();
            str += ";";
            writeLine(str);
        }
        deindent();
        writeLine("}");
    }
    
    /**
     * Write the constructor with arguments.
     * @throws IOException 
     */
    private void writeArgsConstructor() throws IOException{
        if(jbc.getPropertiesList().size() > 0){                   
            if(gen.getGenerateDoc()){ 
                writeLine("/**");
                writeLine("* Constructor with arguments for "+jbc.getName());
                for(JBProperty p : jbc.getPropertiesList()){
                    String type = p.getTypeToUse().isEmpty() ? p.getType().get().getSimpleName() : p.getTypeToUse() ;
                    writeLine("* @param : A {@link "+type+"}");
                }                   
                writeLine("*/");
            }
        
            String args = "";
            for(JBProperty p : jbc.getPropertiesList()){
                String type = p.getTypeToUse().isEmpty() ? p.getType().get().getSimpleName() : p.getTypeToUse() ;
                args += type+" "+p.getName(); 
                if(p != jbc.getPropertiesList().get(jbc.getPropertiesList().size()-1))
                    args += ", ";
            }
            writeLine("public "+jbc.getName()+"("+args+"){");
            indent();      
            for(JBProperty p : jbc.getPropertiesList()){
                String str = "this."+p.getName()+" = "+p.getName()+";"; 
                writeLine(str);
            }
            deindent();
            writeLine("}");
        }
    }
    
    /**
     * Write the copy constructor.
     * @throws IOException 
     */
    private void writeCopyConstructor() throws IOException{        
        if(gen.getGenerateDoc()){ 
            writeLine("/**");
            writeLine("* Copy constructor for "+jbc.getName());
            writeLine("* @param : A {@link "+jbc.getName()+"} which represents the element to copy.");
            writeLine("*/");
        }
        writeLine("public "+jbc.getName()+"("+jbc.getName()+" _"+jbc.getName().toLowerCase() +"){");
        indent();   
        for(JBProperty p : jbc.getPropertiesList()){
                String str = "this."+p.getName()+" = _"+jbc.getName()+"."+p.getter()+"();"; 
                writeLine(str);
        }
        deindent();
        writeLine("}");
    }
    
    /**
     * Write all the constructors.
     * @throws IOException 
     */
    private void writeConstructors() throws IOException{
        writeDefaultConstructor();
        writeLine("");
        
        writeArgsConstructor();
        writeLine("");
        
        writeCopyConstructor();
        writeLine("");
        
    }
    
    /**
     * Write the getters and setters.
     * @throws IOException 
     */
    private void writeGSetters() throws IOException{
        for(JBProperty p : jbc.getPropertiesList()){
            String type = p.getTypeToUse().isEmpty() ? p.getType().get().getSimpleName() : p.getTypeToUse() ;
            if(gen.getGenerateDoc()){ 
                writeLine("/**");
                writeLine("* A getter for "+p.getName());
                writeLine("*/");
            }
            writeLine("public "+type+" "+p.getter()+"(){"); 
            indent();
            writeLine("return this."+p.getName()+";"); 
            deindent();
            writeLine("}"); 
            if(gen.getGenerateDoc()){ 
                writeLine("/**");
                writeLine("* A setter for "+p.getName());
                writeLine("* @param : A {@link "+type+"} .");;
                writeLine("*/");
            }
            writeLine("public void "+p.setter()+"("+type+" value){"); 
            indent();
            writeLine("this."+p.getName()+" = value;"); 
            deindent();
            writeLine("}"); 
        }
    }
    
    /**
     * Write the entire java class file. 
     * @param indent the initial indentation. Use 0 to start to the left corner.
     * @throws IOException 
     */
    public void write(int indent) throws IOException{
        System.out.println("Writing "+gen.getOutDir()+jbc.getName()+".java");
        indentation = indent; 
        
        writeProjectPackage();
        writeLine("");
        writeDependenciesPackage();
        writeLine("");
        writeClass(); 
    }

    /**
     * Each JBContent object of the list are written. If there are enough cores, the writting can be done
     * simultaneously.
     * @param g a JBGenerator object
     * @param jbclist a map 
     * @throws InterruptedException
     * @throws UnsupportedEncodingException
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static void write(JBGenerator g, List<JBContent> jbclist) throws InterruptedException, UnsupportedEncodingException, FileNotFoundException, IOException{
        ExecutorService pool = Executors.newFixedThreadPool(g.getConccurentCalls());
          
        for(JBContent jbc : jbclist)
            pool.execute( new JBWriter(g,jbc) ); 
        
        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.HOURS);  
    }
    
    /**
     * This method is inherited from Runable. It's a callback function for simultaneous calls.
     */
    @Override
    public void run() {
        try {
            write(0);  
        } catch (IOException ex) {
            System.out.println("Error : "+ex.getMessage());
            Logger.getLogger(JBWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
