package com.example.main;

import java.lang.Integer;
import java.lang.String;

/**
* Class appartienta
* @author : 
*/
public class appartienta{
  /**
  * A {@link Integer} which represents depot_id.
  */
  private Integer depot_id;
  /**
  * A {@link Integer} which represents entreprise_id.
  */
  private Integer entreprise_id;
  /**
  * A {@link String} which represents adresse.
  */
  private String adresse;
  
  /**
  * Default constructor for appartienta
  */
  public appartienta(){
    this.depot_id = null;
    this.entreprise_id = null;
    this.adresse = "";
  }
  
  /**
  * Constructor with arguments for appartienta
  * @param : A {@link Integer}
  * @param : A {@link Integer}
  * @param : A {@link String}
  */
  public appartienta(Integer depot_id, Integer entreprise_id, String adresse){
    this.depot_id = depot_id;
    this.entreprise_id = entreprise_id;
    this.adresse = adresse;
  }
  
  /**
  * Copy constructor for appartienta
  * @param : A {@link appartienta} which represents the element to copy.
  */
  public appartienta(appartienta _appartienta){
    this.depot_id = _appartienta.getDepot_id();
    this.entreprise_id = _appartienta.getEntreprise_id();
    this.adresse = _appartienta.getAdresse();
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
  * A getter for entreprise_id
  */
  public Integer getEntreprise_id(){ return this.entreprise_id;}
  /**
  * A setter for entreprise_id
  * @param : A {@link Integer} .
  */
  public void setEntreprise_id(Integer value){ this.entreprise_id = value;}
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
