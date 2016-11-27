package com.example.main;

import java.lang.Integer;
import java.lang.String;

/**
* Class produit
* @author : 
*/
public class produit{
  /**
  * A {@link Integer} which represents produit_id.
  */
  private Integer produit_id;
  /**
  * A {@link String} which represents nom.
  */
  private String nom;
  
  /**
  * Default constructor for produit
  */
  public produit(){
    this.produit_id = null;
    this.nom = "";
  }
  
  /**
  * Constructor with arguments for produit
  * @param : A {@link Integer}
  * @param : A {@link String}
  */
  public produit(Integer produit_id, String nom){
    this.produit_id = produit_id;
    this.nom = nom;
  }
  
  /**
  * Copy constructor for produit
  * @param : A {@link produit} which represents the element to copy.
  */
  public produit(produit _produit){
    this.produit_id = _produit.getProduit_id();
    this.nom = _produit.getNom();
  }
  
  
  /**
  * A getter for produit_id
  */
  public Integer getProduit_id(){ return this.produit_id;}
  /**
  * A setter for produit_id
  * @param : A {@link Integer} .
  */
  public void setProduit_id(Integer value){ this.produit_id = value;}
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
