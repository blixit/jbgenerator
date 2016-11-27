package com.example.main;

import java.lang.Integer;
import java.lang.String;

/**
* Class chargement
* @author : 
*/
public class chargement{
  /**
  * A {@link Integer} which represents chargement_id.
  */
  private Integer chargement_id;
  /**
  * A {@link String} which represents adresse.
  */
  private String adresse;
  /**
  * A {@link String} which represents site.
  */
  private String site;
  
  /**
  * Default constructor for chargement
  */
  public chargement(){
    this.chargement_id = null;
    this.adresse = "";
    this.site = "";
  }
  
  /**
  * Constructor with arguments for chargement
  * @param : A {@link Integer}
  * @param : A {@link String}
  * @param : A {@link String}
  */
  public chargement(Integer chargement_id, String adresse, String site){
    this.chargement_id = chargement_id;
    this.adresse = adresse;
    this.site = site;
  }
  
  /**
  * Copy constructor for chargement
  * @param : A {@link chargement} which represents the element to copy.
  */
  public chargement(chargement _chargement){
    this.chargement_id = _chargement.getChargement_id();
    this.adresse = _chargement.getAdresse();
    this.site = _chargement.getSite();
  }
  
  
  /**
  * A getter for chargement_id
  */
  public Integer getChargement_id(){ return this.chargement_id;}
  /**
  * A setter for chargement_id
  * @param : A {@link Integer} .
  */
  public void setChargement_id(Integer value){ this.chargement_id = value;}
  /**
  * A getter for adresse
  */
  public String getAdresse(){ return this.adresse;}
  /**
  * A setter for adresse
  * @param : A {@link String} .
  */
  public void setAdresse(String value){ this.adresse = value;}
  /**
  * A getter for site
  */
  public String getSite(){ return this.site;}
  /**
  * A setter for site
  * @param : A {@link String} .
  */
  public void setSite(String value){ this.site = value;}
}
