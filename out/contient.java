package com.example.main;

import java.lang.Integer;

/**
* Class contient
* @author : 
*/
public class contient{
  /**
  * A {@link Integer} which represents livraison_id.
  */
  private Integer livraison_id;
  /**
  * A {@link Integer} which represents article_id.
  */
  private Integer article_id;
  
  /**
  * Default constructor for contient
  */
  public contient(){
    this.livraison_id = null;
    this.article_id = null;
  }
  
  /**
  * Constructor with arguments for contient
  * @param : A {@link Integer}
  * @param : A {@link Integer}
  */
  public contient(Integer livraison_id, Integer article_id){
    this.livraison_id = livraison_id;
    this.article_id = article_id;
  }
  
  /**
  * Copy constructor for contient
  * @param : A {@link contient} which represents the element to copy.
  */
  public contient(contient _contient){
    this.livraison_id = _contient.getLivraison_id();
    this.article_id = _contient.getArticle_id();
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
  * A getter for article_id
  */
  public Integer getArticle_id(){ return this.article_id;}
  /**
  * A setter for article_id
  * @param : A {@link Integer} .
  */
  public void setArticle_id(Integer value){ this.article_id = value;}
}
