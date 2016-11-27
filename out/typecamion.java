package com.example.main;

import java.lang.Integer;
import java.lang.String;

/**
* Class typecamion
* @author : 
*/
public class typecamion{
  /**
  * A {@link Integer} which represents typecamion_id.
  */
  private Integer typecamion_id;
  /**
  * A {@link String} which represents nom.
  */
  private String nom;
  
  /**
  * Default constructor for typecamion
  */
  public typecamion(){
    this.typecamion_id = null;
    this.nom = "";
  }
  
  /**
  * Constructor with arguments for typecamion
  * @param : A {@link Integer}
  * @param : A {@link String}
  */
  public typecamion(Integer typecamion_id, String nom){
    this.typecamion_id = typecamion_id;
    this.nom = nom;
  }
  
  /**
  * Copy constructor for typecamion
  * @param : A {@link typecamion} which represents the element to copy.
  */
  public typecamion(typecamion _typecamion){
    this.typecamion_id = _typecamion.getTypecamion_id();
    this.nom = _typecamion.getNom();
  }
  
  
  /**
  * A getter for typecamion_id
  */
  public Integer getTypecamion_id(){ return this.typecamion_id;}
  /**
  * A setter for typecamion_id
  * @param : A {@link Integer} .
  */
  public void setTypecamion_id(Integer value){ this.typecamion_id = value;}
  /**
  * A getter for nom
  */
  public String getNom(){ return this.nom;}
  /**
  * A setter for nom
  * @param : A {@link String} .
  */
  public void setNom(String value){ this.nom = value;}
}
