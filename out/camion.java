package com.example.main;

import java.lang.Integer;
import java.lang.String;
import java.lang.Boolean;

/**
* Class camion
* @author : 
*/
public class camion{
  /**
  * A {@link Integer} which represents camion_id.
  */
  private Integer camion_id;
  /**
  * A {@link String} which represents nom.
  */
  private String nom;
  /**
  * A {@link Boolean} which represents augarage.
  */
  private Boolean augarage;
  /**
  * A {@link Integer} which represents typecamion_id.
  */
  private Integer typecamion_id;
  
  /**
  * Default constructor for camion
  */
  public camion(){
    this.camion_id = null;
    this.nom = "";
    this.augarage = False;
    this.typecamion_id = null;
  }
  
  /**
  * Constructor with arguments for camion
  * @param : A {@link Integer}
  * @param : A {@link String}
  * @param : A {@link Boolean}
  * @param : A {@link Integer}
  */
  public camion(Integer camion_id, String nom, Boolean augarage, Integer typecamion_id){
    this.camion_id = camion_id;
    this.nom = nom;
    this.augarage = augarage;
    this.typecamion_id = typecamion_id;
  }
  
  /**
  * Copy constructor for camion
  * @param : A {@link camion} which represents the element to copy.
  */
  public camion(camion _camion){
    this.camion_id = _camion.getCamion_id();
    this.nom = _camion.getNom();
    this.augarage = _camion.getAugarage();
    this.typecamion_id = _camion.getTypecamion_id();
  }
  
  
  /**
  * A getter for camion_id
  */
  public Integer getCamion_id(){ return this.camion_id;}
  /**
  * A setter for camion_id
  * @param : A {@link Integer} .
  */
  public void setCamion_id(Integer value){ this.camion_id = value;}
  /**
  * A getter for nom
  */
  public String getNom(){ return this.nom;}
  /**
  * A setter for nom
  * @param : A {@link String} .
  */
  public void setNom(String value){ this.nom = value;}
  /**
  * A getter for augarage
  */
  public Boolean getAugarage(){ return this.augarage;}
  /**
  * A setter for augarage
  * @param : A {@link Boolean} .
  */
  public void setAugarage(Boolean value){ this.augarage = value;}
  /**
  * A getter for typecamion_id
  */
  public Integer getTypecamion_id(){ return this.typecamion_id;}
  /**
  * A setter for typecamion_id
  * @param : A {@link Integer} .
  */
  public void setTypecamion_id(Integer value){ this.typecamion_id = value;}
}
