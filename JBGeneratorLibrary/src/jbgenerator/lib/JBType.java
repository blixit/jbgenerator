/*
* Java Bean Generator
* Ebauche pour le projet d'application 2016
* @author : Blixit, Tisba F
* @class : 
* 
 */
package jbgenerator.lib;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Blixit, Tisba F
 */
public class JBType {
    /**
     * The type to store. 
     * Using a class object instead of string, for instance, avoid us to make useless convertion any time we need 
     * to retrieve the type.
     */
    Class type ;
    
    public static final Set<Class<?>> WRAPPER_TYPES = getWrapperTypes();
     
    /**
     * Create a type from uuid-field retrieved from the architect file.
     * @param uuid 
     * @throws java.lang.Exception 
     */
    public JBType(String uuid) throws Exception{ 
        try{
            type = SQLType.convertToBasicJavaType(SQLType.getType(Integer.valueOf(uuid)).getType());
        }catch(Exception e){
            throw new Exception("This type (uuid:"+uuid+") is not handled.");
        }
    }
     
    /**
     * Create a type from uuid-field retrieved from the architect file.
     * @param uuid 
     * @throws java.lang.Exception 
     */
    public JBType(int uuid) throws Exception{
        try{
            type = SQLType.convertToBasicJavaType(SQLType.getType(uuid).getType());
        }catch(Exception e){
            throw new Exception("This type (uuid:"+uuid+") is not handled.");
        }
    }
    
    /**
     * Create a non-primitive type. That means you can't create a type like int, char or long ...
     * @param t 
     * @throws java.lang.Exception 
     */
    public JBType(Class t) throws Exception{
        if(t.isPrimitive())
            throw new Exception("Primitive types ("+t.getName()+") are not handled.");
        type = t;
    }
    
    /**
     * Copy Constructor
     * @param t 
     */
    public JBType(JBType t){
        type = t.get();
    }
    
    /**
     * Getter for the type attribute
     * @return 
     */
    public Class get(){ return this.type; }
    
    /**
     * Converts a {@link JBType} to a string
     * @return 
     */
    @Override
    public String toString(){
        return type.getName();
    }
    
    /**
     * Checks if a class is a wrraper type.
     * @param clazz
     * @return 
     */
    public static boolean isWrapperType(Class<?> clazz)
    {
        return WRAPPER_TYPES.contains(clazz);
    }
    
    /**
     * Gives the list of wrapper types.
     * @return 
     */
    private static Set<Class<?>> getWrapperTypes()
    {
        Set<Class<?>> ret = new HashSet<>();
        ret.add(Boolean.class);
        ret.add(Character.class);
        ret.add(Byte.class);
        ret.add(Short.class);
        ret.add(Integer.class);
        ret.add(Long.class);
        ret.add(Float.class);
        ret.add(Double.class);
        ret.add(Void.class);
        return ret;
    }
}
