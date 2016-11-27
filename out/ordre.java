package com.example.main;

import java.lang.Integer;
import java.sql.Timestamp;

/**
* Class ordre
* @author : 
*/
public class ordre{
  /**
  * A {@link Integer} which represents ordre_id.
  */
  private Integer ordre_id;
  /**
  * A {@link Timestamp} which represents date.
  */
  private Timestamp date;
  /**
  * A {@link Integer} which represents livraison_id.
  */
  private Integer livraison_id;
  /**
  * A {@link Integer} which represents chauffeur_id.
  */
  private Integer chauffeur_id;
  /**
  * A {@link Integer} which represents chargement_id.
  */
  private Integer chargement_id;
  
  /**
  * Default constructor for ordre
  */
  public ordre(){
    this.ordre_id = null;
    this.date = null;
    this.livraison_id = null;
    this.chauffeur_id = null;
    this.chargement_id = null;
  }
  
  /**
  * Constructor with arguments for ordre
  * @param : A {@link Integer}
  * @param : A {@link Timestamp}
  * @param : A {@link Integer}
  * @param : A {@link Integer}
  * @param : A {@link Integer}
  */
  public ordre(Integer ordre_id, Timestamp date, Integer livraison_id, Integer chauffeur_id, Integer chargement_id){
    this.ordre_id = ordre_id;
    this.date = date;
    this.livraison_id = livraison_id;
    this.chauffeur_id = chauffeur_id;
    this.chargement_id = chargement_id;
  }
  
  /**
  * Copy constructor for ordre
  * @param : A {@link ordre} which represents the element to copy.
  */
  public ordre(ordre _ordre){
    this.ordre_id = _ordre.getOrdre_id();
    this.date = _ordre.getDate();
    this.livraison_id = _ordre.getLivraison_id();
    this.chauffeur_id = _ordre.getChauffeur_id();
    this.chargement_id = _ordre.getChargement_id();
  }
  
  
  /**
  * A getter for ordre_id
  */
  public Integer getOrdre_id(){ return this.ordre_id;}
  /**
  * A setter for ordre_id
  * @param : A {@link Integer} .
  */
  public void setOrdre_id(Integer value){ this.ordre_id = value;}
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
  * A getter for livraison_id
  */
  public Integer getLivraison_id(){ return this.livraison_id;}
  /**
  * A setter for livraison_id
  * @param : A {@link Integer} .
  */
  public void setLivraison_id(Integer value){ this.livraison_id = value;}
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
  * A getter for chargement_id
  */
  public Integer getChargement_id(){ return this.chargement_id;}
  /**
  * A setter for chargement_id
  * @param : A {@link Integer} .
  */
  public void setChargement_id(Integer value){ this.chargement_id = value;}
}
