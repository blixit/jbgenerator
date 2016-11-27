# JBGenerator #

====================================

JBGenerator est une librairie simple et intuitive qui a pour but de faciliter le développement applicatif. JBGenerator s'attache à accélérer l'écriture des objets métiers. 
Plus précisément, JBGenerator lit le fichier de sauvegarde de projet généré par SQL POWER ARCHITECT et produit pour chaque relation du schéma une classe Java faisant office d'objet métier.

## Le modèle et l'architecture ##

JBGenerator est distribué comme une librairie JAVA réutilisable dans un projet quelconque. Un programme de test est proposé. Ce programme est utilisable en ligne de commande.

## Exemple d'utilisation depuis la ligne de commande ##
```
#! bash

java -jar JBGeneratorApp/dist/JBGeneratorApp.jar -x=/home/blixit/Documents/2016-2017/PAPPL/architect.architect -c=2 -n=MonProjetSansEspace -p=com.example.main -k=0
```
Voici la syntaxe : 
```
-x|--xml=[xml file] 
-o|--out=[out directory. default=./]
-c|--cores=[number of cores to use. default=4]
-n|--name=[project name]
-p|--pack=[package name]
-k|--kmp=[key management policy (0=default or 1=useComponent) default=0]
-d|--doc=[documentation generation (0=false, 1=true) default=1]
```


Une sortie possible donne : 
```
Parsing /home/blixit/Documents/2016-2017/PAPPL/architect.architect ...
Generating java beans ...
Writing ./out/depot.java
Writing ./out/chauffeur.java
Writing ./out/camion.java
Writing ./out/livraison.java
Writing ./out/contient.java
Writing ./out/ordre.java
Writing ./out/article.java
Writing ./out/chargement.java
Writing ./out/commande.java
Writing ./out/delivre.java
Writing ./out/produit.java
Writing ./out/typecamion.java
Writing ./out/appartienta.java
Writing ./out/entreprise.java
Parsing Time : 65 ms
Generation Time : 18 ms
Total Time : 83 ms
Processors count : 2
```



## Exemple de réutilisation dans un projet##

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
            JBGenerator.KeyManagementPolicy.useForeignKey,   
            true
        );
         
        gen.setConccurentCalls(4);
         
        Map<String,JBContent> jbclist = null;
        
        try {
            System.out.println("Parsing ..."); 
            jbclist = gen.parseXML("/home/userPath/architect.architect");   
                
            System.out.println("Generating java beans ..."); 
            gen.write(new ArrayList(jbclist.values()));

        } catch (InterruptedException | UnsupportedEncodingException | FileNotFoundException ex) {
            Logger.getLogger(JBGeneratorApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(JBGeneratorApp.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
}
```
