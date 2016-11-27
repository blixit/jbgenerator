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
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger; 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node; 

/**
 *
 * @author blixit
 */
public class JBGenerator {
    /**
     * The project name
     */
    private String projectName;
    /**
     * The project package to add java files into
     */
    private String projectPackage;
    /**
     * The path to store java files
     */
    private String outDir;
    /**
     * A boolean to tell if java files should be documented  
     */
    private boolean generateDoc;
    /**
     * The key management policy to use
     */
    private KeyManagementPolicy policy;
    
    /**
     * Precise how to choose the type of the property representing the imported key.
     * Possible value : 
     *      - useForeignKey : save the type of the foreign key
     *      - useComponent : use JBContent pointed by the foreign key. This case is not handled any more
     *      since multi-column relationship are not easy to handle
     */
    public enum KeyManagementPolicy{ 
        useForeignKey,  
        useComponent;
        public static KeyManagementPolicy fromInteger(int x) {
            switch(x) {
            case 0:
                return useForeignKey;
            case 1:
                return useComponent;
        }
        return null;
    }
    }
    
    //Optimization variables
    /**
     * Lock
     */
    private ReentrantLock lock;
    
    private static int conccurentCalls = Runtime.getRuntime().availableProcessors();
    
    /**
     * Constructor
     * @param project project name
     * @param pack project package
     * @param dir path to store output files
     * @param policy
     * @param doc a boolean 
     */
    public JBGenerator(String project, String pack, String dir,KeyManagementPolicy policy, boolean doc){
        this.projectPackage = pack;
        this.outDir = dir;
        this.policy = policy;
        this.generateDoc = doc;
        this.lock = new ReentrantLock();
        this.conccurentCalls = Runtime.getRuntime().availableProcessors();
    }
    
    public String getProjectName(){ return this.projectName; }
    public void setProjectName(String value){ this.projectName = value; }
    public String getProjectPackage(){ return this.projectPackage; }
    public void setProjectPackage(String value){ this.projectPackage = value; }
    public String getOutDir(){ return this.outDir; }
    public void setOutDir(String value){ this.outDir = value; }
    public boolean getGenerateDoc(){ return this.generateDoc; }
    public void setGenerateDoc(boolean value){ this.generateDoc = value; }
    public int getConccurentCalls() { return JBGenerator.conccurentCalls; }
    public void setConccurentCalls(int value) { JBGenerator.conccurentCalls = value; }
    
    /**
     * This method parses the xml file using java xml reader. The 'table' tags are transformed into JBContent objects.
     * 'Relation' tags are used to find relation between tables.
     * @param filename The xml file to use.
     * @return a list of JBContent objects.
     * @throws Exception 
     */
    public List<JBContent> parseXML(String filename) throws Exception{
        Map<String,JBContent> jbclist = new HashMap<>(); 
        try {
            File xml = new File(filename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder(); 
            Document doc = dBuilder.parse(xml);
            
            //target-database
            NodeList targetdatabase = doc.getElementsByTagName("target-database"); 
            if(targetdatabase.getLength() == 0)
                throw new Exception("No tables found in the architect file.");
            
            //Tables
            Element tags = (Element)targetdatabase.item(0).getChildNodes();
            NodeList tables = tags.getElementsByTagName("table");
            Node RelationShips = tags.getElementsByTagName("relationships").item(0);
            
            
            //the list which link colid to colname            
            Map<String,String> colIdToName = new HashMap<>(); 
            
            ExecutorService pool = Executors.newFixedThreadPool(this.getConccurentCalls());               
            //Alain : We noticed that invokeAll is a little bit faster than execute()
            //Therefore we prefer use of Callable collections
            
            Set<Callable<String>> callables = new HashSet<>();
            Set<Callable<String>> callablesRelation = new HashSet<>();
            try{
                
                for(int i = 0; i< tables.getLength(); ++i ){ 
                    Node n = tables.item(i);
                    //Tables
                    if(n.getNodeName().equals("table")){ 
                        Callable<String> job = new Callable<String>() {
                            public String call() {
                                try { 
                                    tableExtractor(n, jbclist, colIdToName); 
                                } catch (Exception ex) {
                                    Logger.getLogger(JBGenerator.class.getName()).log(Level.SEVERE, null, ex);
                                    System.out.println("Error Table Extractor : "+ex.getMessage());
                                }
                                return "Task on "+n.getAttributes().getNamedItem("name").getNodeValue();
                            }
                        }; 
                        callables.add(job); 
                    }
                } 
                pool.invokeAll(callables);   
                 
                /*
                *   For the while, thix program just manages 2 policies (useComponent, useForeignKey).
                *   Therefore, if the choosen policy is useForeignKey (the default one), there's nothing
                *   to do. Otherwize, we change type of the foreign key by the Entity's class pointed by this key.
                */
                if(policy != JBGenerator.KeyManagementPolicy.useForeignKey){  
                    Element relationsContainer = (Element)RelationShips.getChildNodes();
                    NodeList relations = relationsContainer.getElementsByTagName("relationship");
                    for(int j = 0; j< relations.getLength(); ++j){
                        Node relationship = relations.item(j);
                        //Relationship                           
                        Callable<String> job = new Callable<String>() {
                            public String call() {
                                try { 
                                    relationAnalyzer(relationship,jbclist,colIdToName);
                                } catch (Exception ex) {
                                    Logger.getLogger(JBGenerator.class.getName()).log(Level.SEVERE, null, ex);
                                    System.out.println("Error RelationShip Analyzer : "+ex.getMessage());
                                }
                                return "Task on "+RelationShips.getAttributes().getNamedItem("name").getNodeValue();
                            }
                        }; 
                        callablesRelation.add(job);  
                    }
                    pool.invokeAll(callablesRelation);
                }
                //System.out.println("NB Relations  : "+ RelationShips.getNodeName());//callablesRelation.size());
            }catch(Exception ex){
                Logger.getLogger(JBGenerator.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Error : "+ex.getMessage());
            }
            
            pool.shutdown(); 
            pool.awaitTermination(1, TimeUnit.HOURS);              
        } catch (org.xml.sax.SAXParseException ex) {
            Logger.getLogger(JBGenerator.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(JBGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList(jbclist.values());
    }
    
    /**
     * Extracts information in a table and adds it in the {@link JBContent} container.
     * 'synchronized' ensures that the call is atomic
     * @param n
     * @param jbclist
     * @param colIdToName
     * @throws Exception 
     */
    private /*synchronized*/ void tableExtractor(Node table,Map<String,JBContent> jbclist,Map<String,String>colIdToName) throws Exception {
        ArrayList<JBProperty> properties = new ArrayList<>(); 
        String colName = "", colId = "";
        //lock.lock();
        NodeList folders = table.getChildNodes(); 
        //lock.unlock();
        for(int j = 0; j< folders.getLength(); ++j){
            Node folder = folders.item(j);
            //Folder where type = 1
            if(folder.getNodeName().equals("folder")
                && folder.getAttributes().getNamedItem("type").getNodeValue().equals("1")    ){ 
                //Columns
                NodeList cols = ((Element)folder).getElementsByTagName("column"); 
                for(int k = 0; k < cols.getLength(); ++k){
                    //Column  
                    NamedNodeMap cattrs = cols.item(k).getAttributes();
                    
                    colName = cattrs.getNamedItem("physicalName").getNodeValue();
                    colId = cattrs.getNamedItem("id").getNodeValue();
                    JBProperty prop = new JBProperty(colName
                                ,new JBType(cattrs.getNamedItem("type").getNodeValue())
                                ,cattrs.getNamedItem("defaultValue").getNodeValue()
                                );

                    properties.add(prop); 
                    lock.lock();
                    colIdToName.put(colId, colName);  
                    lock.unlock();

                }   
                // there is only folder that satisfy this condition
                break;
            }
        }
        
        //table attributes        
        //lock.lock();
        NamedNodeMap tattrs = table.getAttributes();
        //lock.unlock();
        String tableid = tattrs.getNamedItem("id").getNodeValue();
        JBContent jbc = new JBContent(tattrs.getNamedItem("physicalName").getNodeValue(),properties); 
        lock.lock();
        jbclist.put(tableid,jbc);  
        lock.unlock();
    }

    /**
     * Extracts information in a relation and updates the {@link JBContent} container following the policy of key management.
     * @param relationship
     * @param jbclist
     * @param colIdToName 
     */
    private /*synchronized*/ void relationAnalyzer(Node relationship, Map<String,JBContent> jbclist,Map<String,String>colIdToName){
        //attrs
        NamedNodeMap attrs = relationship.getAttributes();
        //Retrieving the 2 JBContent (Table) involved in the relation
        //lock.lock();
        JBContent tabfk = jbclist.get(attrs.getNamedItem("fk-table-ref").getNodeValue());
        JBContent tabpk = jbclist.get(attrs.getNamedItem("pk-table-ref").getNodeValue()); 
        //lock.unlock();

        //relationship has 3 child nodes (0, 1 and 2). 1 gives the column-mapping node
        Node columnMap = relationship.getChildNodes().item(1);

        if(columnMap.getNodeName().equals("column-mapping")){
            String colRef = columnMap.getAttributes().getNamedItem("fk-column-ref").getNodeValue();
            //lock.lock();
            colRef = colIdToName.get(colRef);
            //lock.unlock();
            switch(policy){
                case useForeignKey:
                    break;
                case useComponent: 
                    List<JBProperty> jbprops = tabfk.getPropertiesList(); 
                    for(int k = 0; k < jbprops.size(); ++k){
                        if(jbprops.get(k).getName().equals(colRef) ){                                                 
                            tabfk.getPropertiesList().get(k).setTypeToUse(tabpk.getName());                                                 
                            break;
                        }
                    }
                    break;
            } 
            lock.lock();
            jbclist.replace(attrs.getNamedItem("fk-table-ref").getNodeValue(), tabfk);
            lock.unlock();
            //System.out.println("Relation : "+tabfk.getName()+" importe "+tabpk.getName()+" sur "+colRef);
        }
    }

    public void write(List<JBContent> jbclist) throws InterruptedException, UnsupportedEncodingException, FileNotFoundException, IOException{
        JBWriter.write(this, jbclist); 
    }
    
    /**
     * Retourne la page de d'aide du programme de test.
     * @return  une chaine de charactère contenant l'aide.
     */
    public static String getSyntaxe(){
        return "Please follow the previous syntax : \n"
                + "-x|--xml=[xml file]\n"
                + "-o|--out=[out directory. default=./]\n"
                + "-c|--cores=[number of cores to use. default=4]\n"
                + "-n|--name=[project name]\n"
                + "-p|--pack=[package name]\n"
                + "-k|--kmp=[key management policy (0=default or 1=useComponent) default=0]\n"
                + "-d|--doc=[documentation generation (0=false, 1=true) default=1]\n\n"
                + "Example : \n"
                + "jbgenerator -x=filename.architect -c=4 -n=monprojetSansEspace -p=com.example.main -k=0\n\n";
    }
    
    /**
     * Extrait les arguments saisis lors d'une exécution depuis la console.
     * @param args les arguments saisis
     * @param xml le fichier xml
     * @param out le répertoire de sortie
     * @param outdir 
     * @param cores
     * @param name
     * @param pack
     * @param kmp
     * @param doc
     * @param kmpolicy
     * @param generateDoc
     * @throws Exception 
     */
    public static void extractConsoleArgs(String[] args, Map<String,Object> valeurs/*,
            String xml, String out, String cores, String name, String pack, String kmp, String doc,
            JBGenerator.KeyManagementPolicy kmpolicy, boolean generateDoc*/
    ) throws Exception{
        for (int i = 0; i < args.length; i++) {
            String[] arg = args[i].split("="); 
            if(arg[0].equals("-x") || arg[0].equals("--xml")){  
                valeurs.put("xml", arg[1]); 
            }else if(arg[0].equals("-o") || arg[0].equals("--out")){ 
                valeurs.put("out", arg[1]); 
            }else if(arg[0].equals("-c") || arg[0].equals("--cores")){ 
                valeurs.put("cores", arg[1]); 
            }else if(arg[0].equals("-n") || arg[0].equals("--name")){ 
                valeurs.put("name", arg[1]); 
            }else if(arg[0].equals("-p") || arg[0].equals("--pack")){ 
                valeurs.put("pack", arg[1]); 
            }else if(arg[0].equals("-k") || arg[0].equals("--kmp")){ 
                valeurs.put("kmp", arg[1]); 
            }else if(arg[0].equals("-d") || arg[0].equals("--doc")){ 
                valeurs.put("doc", arg[1]); 
            }          
        }
        
        if(valeurs.get("xml") == null)
            throw new Exception("The file name is uncorrect.");
        if(valeurs.get("cores") == null || ! ((String)valeurs.get("cores")).chars().allMatch(Character::isDigit))
            throw new Exception("The number of cores is uncorrect.");
        if(valeurs.get("name") == null)
            throw new Exception("The project name is uncorrect.");
        if(valeurs.get("pack") == null)
            throw new Exception("The package name is uncorrect.");
        if( valeurs.get("kmp") == null)
            throw new Exception("The choosen policy is uncorrect.");
        else{
            int v = Integer.valueOf(valeurs.get("kmp").toString());
            valeurs.put("kmp", v);                        
        }
        if( valeurs.get("doc") == null )
            throw new Exception(" : The value for the option `-d|--doc` is uncorrect.");
        else{
            int v = Integer.valueOf(valeurs.get("doc").toString());
            valeurs.put("doc", v != 0 ? true : false);
        }
         
        
    }

}
