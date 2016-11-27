package com.example.main;

import java.lang.Integer;

/**
* Class article
* @author : 
*/
public class article{
  /**
  * A {@link Integer} which represents article_id.
  */
  private Integer article_id;
  /**
  * A {@link Integer} which represents quantite.
  */
  private Integer quantite;
  /**
  * A {@link Integer} which represents commande_id.
  */
  private Integer commande_id;
  /**
  * A {@link Integer} which represents produit_id.
  */
  private Integer produit_id;
  
  /**
  * Default constructor for article
  */
  public article(){
    this.article_id = null;
    this.quantite = null;
    this.commande_id = null;
    this.produit_id = null;
  }
  
  /**
  * Constructor with arguments for article
  * @param : A {@link Integer}
  * @param : A {@link Integer}
  * @param : A {@link Integer}
  * @param : A {@link Integer}
  */
  public article(Integer article_id, Integer quantite, Integer commande_id, Integer produit_id){
    this.article_id = article_id;
    this.quantite = quantite;
    this.commande_id = commande_id;
    this.produit_id = produit_id;
  }
  
  /**
  * Copy constructor for article
  * @param : A {@link article} which represents the element to copy.
  */
  public article(article _article){
    this.article_id = _article.getArticle_id();
    this.quantite = _article.getQuantite();
    this.commande_id = _article.getCommande_id();
    this.produit_id = _article.getProduit_id();
  }
  
  
  /**
  * A getter for article_id
  */
  public Integer getArticle_id(){ return this.article_id;}
  /**
  * A setter for article_id
  * @param : A {@link Integer} .
  */
  public void setArticle_id(Integer value){ this.article_id = value;}
  /**
  * A getter for quantite
  */
  public Integer getQuantite(){ return this.quantite;}
  /**
  * A setter for quantite
  * @param : A {@link Integer} .
  */
  public void setQuantite(Integer value){ this.quantite = value;}
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
  * A getter for produit_id
  */
  public Integer getProduit_id(){ return this.produit_id;}
  /**
  * A setter for produit_id
  * @param : A {@link Integer} .
  */
  public void setProduit_id(Integer value){ this.produit_id = value;}
}
