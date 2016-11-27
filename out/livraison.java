package com.example.main;

import java.lang.Integer;
import java.sql.Timestamp;
import java.lang.String;

/**
* Class livraison
* @author : 
*/
public class livraison{
  /**
  * A {@link Integer} which represents livraison_id.
  */
  private Integer livraison_id;
  /**
  * A {@link Timestamp} which represents date.
  */
  private Timestamp date;
  /**
  * A {@link Integer} which represents depot_id.
  */
  private Integer depot_id;
  /**
  * A {@link String} which represents adresse.
  */
  private String adresse;
  
  /**
  * Default constructor for livraison
  */
  public livraison(){
    this.livraison_id = null;
    this.date = null;
    this.depot_id = null;
    this.adresse = "";
  }
  
  /**
  * Constructor with arguments for livraison
  * @param : A {@link Integer}
  * @param : A {@link Timestamp}
  * @param : A {@link Integer}
  * @param : A {@link String}
  */
  public livraison(Integer livraison_id, Timestamp date, Integer depot_id, String adresse){
    this.livraison_id = livraison_id;
    this.date = date;
    this.depot_id = depot_id;
    this.adresse = adresse;
  }
  
  /**
  * Copy constructor for livraison
  * @param : A {@link livraison} which represents the element to copy.
  */
  public livraison(livraison _livraison){
    this.livraison_id = _livraison.getLivraison_id();
    this.date = _livraison.getDate();
    this.depot_id = _livraison.getDepot_id();
    this.adresse = _livraison.getAdresse();
  }
  
  
  /**
  * A getter for livraison_id
  */
  public Integer getLivraison_id(){ return this.livraison_id;}
  /**
  * A setter for livraison_id
  * @param : A {@link Integer} .
  */
  public void setLivraison_id(Integer value){ this.livraison_id = value;}
  /**
  * A getter for date
  */
  public Timestamp getDate(){ return this.date;}
  /**
  * A setter for date
  * @param : A {@link Timestamp} .
  */
  public void setDate(Timestamp value){ this.date = value;}
  /**
  * A getter for depot_id
  */
  public Integer getDepot_id(){ return this.depot_id;}
  /**
  * A setter for depot_id
  * @param : A {@link Integer} .
  */
  public void setDepot_id(Integer value){ this.depot_id = value;}
  /**
  * A getter for adresse
  */
  public String getAdresse(){ return this.adresse;}
  /**
  * A setter for adresse
  * @param : A {@link String} .
  */
  public void setAdresse(String value){ this.adresse = value;}
}
