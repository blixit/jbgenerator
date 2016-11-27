package com.example.main;

import java.lang.Integer;
import java.sql.Timestamp;

/**
* Class delivre
* @author : 
*/
public class delivre{
  /**
  * A {@link Integer} which represents delivre_id.
  */
  private Integer delivre_id;
  /**
  * A {@link Timestamp} which represents date.
  */
  private Timestamp date;
  /**
  * A {@link Integer} which represents chauffeur_id.
  */
  private Integer chauffeur_id;
  /**
  * A {@link Integer} which represents camion_id.
  */
  private Integer camion_id;
  /**
  * A {@link Integer} which represents article_id.
  */
  private Integer article_id;
  
  /**
  * Default constructor for delivre
  */
  public delivre(){
    this.delivre_id = null;
    this.date = null;
    this.chauffeur_id = null;
    this.camion_id = null;
    this.article_id = null;
  }
  
  /**
  * Constructor with arguments for delivre
  * @param : A {@link Integer}
  * @param : A {@link Timestamp}
  * @param : A {@link Integer}
  * @param : A {@link Integer}
  * @param : A {@link Integer}
  */
  public delivre(Integer delivre_id, Timestamp date, Integer chauffeur_id, Integer camion_id, Integer article_id){
    this.delivre_id = delivre_id;
    this.date = date;
    this.chauffeur_id = chauffeur_id;
    this.camion_id = camion_id;
    this.article_id = article_id;
  }
  
  /**
  * Copy constructor for delivre
  * @param : A {@link delivre} which represents the element to copy.
  */
  public delivre(delivre _delivre){
    this.delivre_id = _delivre.getDelivre_id();
    this.date = _delivre.getDate();
    this.chauffeur_id = _delivre.getChauffeur_id();
    this.camion_id = _delivre.getCamion_id();
    this.article_id = _delivre.getArticle_id();
  }
  
  
  /**
  * A getter for delivre_id
  */
  public Integer getDelivre_id(){ return this.delivre_id;}
  /**
  * A setter for delivre_id
  * @param : A {@link Integer} .
  */
  public void setDelivre_id(Integer value){ this.delivre_id = value;}
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
  * A getter for chauffeur_id
  */
  public Integer getChauffeur_id(){ return this.chauffeur_id;}
  /**
  * A setter for chauffeur_id
  * @param : A {@link Integer} .
  */
  public void setChauffeur_id(Integer value){ this.chauffeur_id = value;}
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
  * A getter for article_id
  */
  public Integer getArticle_id(){ return this.article_id;}
  /**
  * A setter for article_id
  * @param : A {@link Integer} .
  */
  public void setArticle_id(Integer value){ this.article_id = value;}
}
