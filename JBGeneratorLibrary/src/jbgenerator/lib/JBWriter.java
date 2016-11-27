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
     
    
    private OutputStream out;
    /**
     * A {@link JBGenerator} contains required settings such as paths and policies. 
     */
    private JBGenerator gen;
    private JBContent jbc;
    
    private int indentation;
    private String indentationString;
    static final int defaultIndentation = 2;
    
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
    
    private void indent(){
        indentation += defaultIndentation; 
        setIndentString();
    }
    
    private void deindent(){
        indentation = indentation >= defaultIndentation ? indentation-defaultIndentation : 0; 
        setIndentString();
    }
    
    private void setIndentString(){ indentationString = indentation <= 0 ? "" : new String(new char[indentation]).replace('\0', ' '); }
    
    private void writeLine( String str) throws IOException{
         
        str = indentationString+str+"\n";
        
        this.out.write(str.getBytes());
    }
        
    private void writeProjectPackage() throws IOException{ 
        writeLine("package "+gen.getProjectPackage()+";");
    }
    
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
    
    private void writeConstructors() throws IOException{
        writeDefaultConstructor();
        writeLine("");
        
        writeArgsConstructor();
        writeLine("");
        
        writeCopyConstructor();
        writeLine("");
        
    }
    
    private void writeGSetters() throws IOException{
        for(JBProperty p : jbc.getPropertiesList()){
            String type = p.getTypeToUse().isEmpty() ? p.getType().get().getSimpleName() : p.getTypeToUse() ;
            if(gen.getGenerateDoc()){ 
                writeLine("/**");
                writeLine("* A getter for "+p.getName());
                writeLine("*/");
            }
            writeLine("public "+type+" "+p.getter()+"(){ return this."+p.getName()+";}"); 
            if(gen.getGenerateDoc()){ 
                writeLine("/**");
                writeLine("* A setter for "+p.getName());
                writeLine("* @param : A {@link "+type+"} .");;
                writeLine("*/");
            }
            writeLine("public void "+p.setter()+"("+type+" value){ this."+p.getName()+" = value;}"); 
        }
    }
    
    public void write(int indent) throws IOException{
        System.out.println("Writing "+gen.getOutDir()+jbc.getName()+".java");
        indentation = indent; 
        
        writeProjectPackage();
        writeLine("");
        writeDependenciesPackage();
        writeLine("");
        writeClass(); 
    }

    public static void write(JBGenerator g,Map<String,JBContent> jbclist) throws InterruptedException, UnsupportedEncodingException, FileNotFoundException, IOException{
        ExecutorService pool = Executors.newFixedThreadPool(g.getConccurentCalls());
          
        for(String key : jbclist.keySet())
            pool.execute( new JBWriter(g,jbclist.get(key)) ); 
        
        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.HOURS);  
    }
    
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