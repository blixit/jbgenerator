/*
* Java Bean Generator
* Ebauche pour le projet d'application 2016
* @author : Blixit, Tisba F
* @class : 
* 
 */
package jbgenerator.lib;

import java.util.ArrayList;
import java.util.List;

/**
 * This class stores the content of a SQL relation (= a table in the architect schema).
 * @author Blixit, Tisba F
 */
public class JBContent {
    /**
     * The Table name
     */
    private String name; 
    /**
     * The list of properties in the table
     */
    private List<JBProperty> propertiesList;
    /**
     * Unique id. Even the copy constructor is used.
     */
    private int uuid;
    /**
     * The instances generated since the program started.
     */
    private static int instance = 0;
    
    /**
     * Constructeur
     * @param Name Nom de l'entité correspondant à la classe java à générer. Il est extrait de la balise table du fichier .architect.
     * @param prop Liste des propriétés de l'entité
     */
    public JBContent(String Name, List<JBProperty> prop){
        this.name = Name; 
        this.propertiesList = new ArrayList<>(prop); 
        uuid = ++instance;
    }
    
    public String getName(){ return this.name; } 
    public void setName(String value){ this.name = value; }     
    public List<JBProperty> getPropertiesList(){ return this.propertiesList; }
    public void setPropertiesList(List<JBProperty> value){ this.propertiesList = value; }
    public int getUuid(){ return this.uuid; } 
    
    /**
     * Converts a {@link JBContent} to a string.
     * @return A text which contains the name of the object
     */
    @Override
    public String toString(){
        String str = "Entity : "+name+"("+uuid+") : ";
        for(JBProperty prop : propertiesList){
            str += prop.getName()+( prop.getDefaultValue().length() > 0 ? "(="+prop.getDefaultValue()+")" : "")+" ";
        }
        return str;
    }
    
}
