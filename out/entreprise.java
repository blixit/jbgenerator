package com.example.main;

import java.lang.Integer;
import java.lang.String;

/**
* Class entreprise
* @author : 
*/
public class entreprise{
  /**
  * A {@link Integer} which represents entreprise_id.
  */
  private Integer entreprise_id;
  /**
  * A {@link String} which represents nom.
  */
  private String nom;
  /**
  * A {@link String} which represents adresse.
  */
  private String adresse;
  
  /**
  * Default constructor for entreprise
  */
  public entreprise(){
    this.entreprise_id = null;
    this.nom = "";
    this.adresse = "";
  }
  
  /**
  * Constructor with arguments for entreprise
  * @param : A {@link Integer}
  * @param : A {@link String}
  * @param : A {@link String}
  */
  public entreprise(Integer entreprise_id, String nom, String adresse){
    this.entreprise_id = entreprise_id;
    this.nom = nom;
    this.adresse = adresse;
  }
  
  /**
  * Copy constructor for entreprise
  * @param : A {@link entreprise} which represents the element to copy.
  */
  public entreprise(entreprise _entreprise){
    this.entreprise_id = _entreprise.getEntreprise_id();
    this.nom = _entreprise.getNom();
    this.adresse = _entreprise.getAdresse();
  }
  
  
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
  * A getter for nom
  */
  public String getNom(){ return this.nom;}
  /**
  * A setter for nom
  * @param : A {@link String} .
  */
  public void setNom(String value){ this.nom = value;}
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
