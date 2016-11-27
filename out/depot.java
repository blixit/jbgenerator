package com.example.main;

import java.lang.Integer;
import java.lang.String;

/**
* Class depot
* @author : 
*/
public class depot{
  /**
  * A {@link Integer} which represents depot_id.
  */
  private Integer depot_id;
  /**
  * A {@link String} which represents adresse.
  */
  private String adresse;
  
  /**
  * Default constructor for depot
  */
  public depot(){
    this.depot_id = null;
    this.adresse = "";
  }
  
  /**
  * Constructor with arguments for depot
  * @param : A {@link Integer}
  * @param : A {@link String}
  */
  public depot(Integer depot_id, String adresse){
    this.depot_id = depot_id;
    this.adresse = adresse;
  }
  
  /**
  * Copy constructor for depot
  * @param : A {@link depot} which represents the element to copy.
  */
  public depot(depot _depot){
    this.depot_id = _depot.getDepot_id();
    this.adresse = _depot.getAdresse();
  }
  
  
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
