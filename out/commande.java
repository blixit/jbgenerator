package com.example.main;

import java.lang.Integer;

/**
* Class commande
* @author : 
*/
public class commande{
  /**
  * A {@link Integer} which represents commande_id.
  */
  private Integer commande_id;
  /**
  * A {@link Integer} which represents entreprise_id.
  */
  private Integer entreprise_id;
  
  /**
  * Default constructor for commande
  */
  public commande(){
    this.commande_id = null;
    this.entreprise_id = null;
  }
  
  /**
  * Constructor with arguments for commande
  * @param : A {@link Integer}
  * @param : A {@link Integer}
  */
  public commande(Integer commande_id, Integer entreprise_id){
    this.commande_id = commande_id;
    this.entreprise_id = entreprise_id;
  }
  
  /**
  * Copy constructor for commande
  * @param : A {@link commande} which represents the element to copy.
  */
  public commande(commande _commande){
    this.commande_id = _commande.getCommande_id();
    this.entreprise_id = _commande.getEntreprise_id();
  }
  
  
  /**
  * A getter for commande_id
  */
  public Integer getCommande_id(){ return this.commande_id;}
  /**
  * A setter for commande_id
  * @param : A {@link Integer} .
  */
  public void setCommande_id(Integer value){ this.commande_id = value;}
  /**
  * A getter for entreprise_id
  */
  public Integer getEntreprise_id(){ return this.entreprise_id;}
  /**
  * A setter for entreprise_id
  * @param : A {@link Integer} .
  */
  public void setEntreprise_id(Integer value){ this.entreprise_id = value;}
}
