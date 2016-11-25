/*
* Java Bean Generator
* Ebauche pour le projet d'application 2016
* @author : Blixit, Tisba F
* @class : 
* 
 */
package jbgenerator.lib;

/**
 *
 * @author blixit
 */
public class JBProperty {
    /**
     * The name of the attribute.
     */
    private String name;
    /**
     * The type of the attribute.
     */
    private JBType type;
    /**
     * When using the `UseComponent` policy of {@link KeyManagementPolicy}, the type to use may not exist yet.
     * To prevent this case, we define the type to use in this case. This value is read during 
     * the file writting.
     */
    private String typeToUse;
    /**
     * A string giving the default value of this property.
     * @warning We use a string to store this information since the value comes from the
     * .architect file (xml file).
     */
    private String defaultValue;
    /**
     * Unique id. Even the copy constructor is used.
     */
    private int uuid;
    /**
     * The instances generated since the program started.
     */
    private static int instance = 0;
    
    /**
     * Constructor.
     * @param name The attribute name
     * @param t A {@link JBType} giving the type of the attribute
     * @param defValue The default value as a String
     */
    public JBProperty(String name, JBType t, String defValue){
        this.name = name;
        this.type = t;
        this.defaultValue = defValue;
        this.uuid = ++JBProperty.instance; 
        this.typeToUse = "";
    }
    
    /**
     * Copy Constructor
     * @param p 
     */
    public JBProperty(JBProperty p){
        this.name = p.getName();
        this.type = p.getType();
        this.defaultValue = p.getDefaultValue();
        this.uuid = ++JBProperty.instance; 
        this.typeToUse = "";
    }
    
    public String getName(){return this.name;}
    public void setName(String value){ this.name = value;}  
    public JBType getType(){ return this.type;}
    public void setType(JBType value){ this.type = value;}  
    public String getTypeToUse(){return this.typeToUse;}
    public void setTypeToUse(String value){ this.typeToUse = value;}
    public void setType(Class value) throws Exception{ this.type = new JBType(value); }
    public String getDefaultValue(){return this.defaultValue;}
    public void setDefaultValue(String value){ this.defaultValue = value;}
    public int getUuid(){return this.uuid;} 
    
    /**
     * Return the name of the getter method
     * @return 
     */
    public String getter(){
        return "get"+this.name.toUpperCase().charAt(0)+this.name.substring(1);
    }
    
    /**
     * Return the name of the setter method
     * @return 
     */
    public String setter(){
        return "set"+this.name.toUpperCase().charAt(0)+this.name.substring(1);
    }
    /**
     * Converts a {@link JBProperty} to a string
     * @return 
     */
    @Override
    public String toString(){
        return "["+type+" "+name+"("+uuid+") = "+defaultValue+" ]";
    }
    
}
