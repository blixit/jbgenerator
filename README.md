# JBGenerator #

====================================

JBGenerator est une librairie simple et intuitive qui a pour but de faciliter le développement applicatif. JBGenerator s'attache à accélérer l'écriture des objets métiers. 
Plus précisément, JBGenerator lit le fichier de sauvegarde de projet généré par SQL POWER ARCHITECT et produit pour chaque relation du schéma une classe Java faisant office d'objet métier.

## Le modèle et l'architecture ##

JBGenerator est distribué comme une librairie JAVA réutilisable dans un projet quelconque. Un programme de test est proposé. Ce programme est utilisable en ligne de commande.


## Simple Exemple ##

```
#! java

# ... java imports

import jbgenerator.lib.JBContent;
import jbgenerator.lib.JBGenerator; 
import jbgenerator.lib.JBWriter;

public class JBGeneratorApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
                 
        /**
         * Instantiation du générateur
         */
        JBGenerator gen = new JBGenerator(
            "sample", 
            "com.example.main",  
            "./out/",     
            JBGenerator.KeyManagementPolicy.e,   
            true
        );
         
        gen.setConccurentCalls(4);
         
        Map<String,JBContent> jbclist = null;
        
        try {
            System.out.println("Parsing ..."); 
            jbclist = gen.parseXML("/home/userPath/architect.architect");   
                
            System.out.println("Generating java beans ..."); 
            gen.write(jbclist);  

        } catch (InterruptedException | UnsupportedEncodingException | FileNotFoundException ex) {
            Logger.getLogger(JBGeneratorApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(JBGeneratorApp.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
}
```
