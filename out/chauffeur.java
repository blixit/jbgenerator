package com.example.main;

import java.lang.Integer;
import java.lang.String;

/**
* Class chauffeur
* @author : 
*/
public class chauffeur{
  /**
  * A {@link Integer} which represents chauffeur_id.
  */
  private Integer chauffeur_id;
  /**
  * A {@link String} which represents nom.
  */
  private String nom;
  /**
  * A {@link String} which represents prenom.
  */
  private String prenom;
  /**
  * A {@link Integer} which represents camion_id.
  */
  private Integer camion_id;
  /**
  * A {@link String} which represents mail.
  */
  private String mail;
  
  /**
  * Default constructor for chauffeur
  */
  public chauffeur(){
    this.chauffeur_id = null;
    this.nom = "";
    this.prenom = "";
    this.camion_id = null;
    this.mail = "";
  }
  
  /**
  * Constructor with arguments for chauffeur
  * @param : A {@link Integer}
  * @param : A {@link String}
  * @param : A {@link String}
  * @param : A {@link Integer}
  * @param : A {@link String}
  */
  public chauffeur(Integer chauffeur_id, String nom, String prenom, Integer camion_id, String mail){
    this.chauffeur_id = chauffeur_id;
    this.nom = nom;
    this.prenom = prenom;
    this.camion_id = camion_id;
    this.mail = mail;
  }
  
  /**
  * Copy constructor for chauffeur
  * @param : A {@link chauffeur} which represents the element to copy.
  */
  public chauffeur(chauffeur _chauffeur){
    this.chauffeur_id = _chauffeur.getChauffeur_id();
    this.nom = _chauffeur.getNom();
    this.prenom = _chauffeur.getPrenom();
    this.camion_id = _chauffeur.getCamion_id();
    this.mail = _chauffeur.getMail();
  }
  
  
  /**
  * A getter for chauffeur_id
  */
  public Integer getChauffeur_id(){ return this.chauffeur_id;}
  /**
  * A setter for chauffeur_id
  * @param : A {@link Integer} .
  */
  public void setChauffeur_id(Integer value){ this.chauffeur_id = value;}
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
  * A getter for prenom
  */
  public String getPrenom(){ return this.prenom;}
  /**
  * A setter for prenom
  * @param : A {@link String} .
  */
  public void setPrenom(String value){ this.prenom = value;}
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
  * A getter for mail
  */
  public String getMail(){ return this.mail;}
  /**
  * A setter for mail
  * @param : A {@link String} .
  */
  public void setMail(String value){ this.mail = value;}
}
