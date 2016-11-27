/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jbgeneratorapp;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import jbgenerator.lib.JBContent;
import jbgenerator.lib.JBGenerator; 
import jbgenerator.lib.JBWriter;

/**
 *
 * @author Blixit
 */
public class JBGeneratorApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /**
         * Pour simuler un appel avec des arguments sans passer par la ligne de commande, décommenter le code ci-dessous.
         */
        /*args = new String[5];
        args[0] = "-x=/home/blixit/Documents/2016-2017/PAPPL/architect.architect";
        args[1] = "-c=1";
        args[2] = "-n=monprojetSansEspace";
        args[3] = "-p=com.example.main";
        args[4] = "-k=0";*/
        
        /**
         * il s'agit d'un utilitaire. on peut l'utiliser depuis la console.   
         * La vérification ci-dessous peut être supprimée dans le cas ou la librairie est réutilisée dans un autre projet.
         */
        if(args.length < 3){
            System.out.println("The argument list is uncorrect.\n"+JBGenerator.getSyntaxe());
            System.exit(-1);
        }
        
        /**
         * La map permettant d'extraire les valeurs des arguments.
         */
        Map parameters = new HashMap<String,Object>();
        parameters.put("out","./out/");
        parameters.put("cores","8");
        parameters.put("kmp","0");
        parameters.put("doc","1"); 
        
        try {
            JBGenerator.extractConsoleArgs(args,parameters); 
        } catch (Exception ex) {
            Logger.getLogger(JBGeneratorApp.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-1);
        }
                 
        /**
         * Instantiation du générateur
         */
        JBGenerator gen = new JBGenerator(
            parameters.get("name").toString(), 
            parameters.get("pack").toString(),  
            parameters.get("out").toString(),     
            JBGenerator.KeyManagementPolicy.fromInteger((int)parameters.get("kmp")),   
            (boolean)parameters.get("doc")
        );
        
        //Set processor count
        gen.setConccurentCalls(Integer.valueOf(parameters.get("cores").toString()));
        
        long parseTime = 0, time = 0;
        List<JBContent> jbclist = null;
        
        try {
            System.out.println("Parsing "+parameters.get("xml").toString()+" ...");
            parseTime = System.nanoTime(); 
            jbclist = gen.parseXML(parameters.get("xml").toString());            
        
            time = System.nanoTime(); 
            parseTime = time - parseTime;
                
            System.out.println("Generating java beans ..."); 
            gen.write(jbclist); 
            
            time = System.nanoTime() - time;
        } catch (InterruptedException | UnsupportedEncodingException | FileNotFoundException ex) {
            Logger.getLogger(JBGeneratorApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(JBGeneratorApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        parseTime /= 1000000.;
        time /= 1000000.;
        System.out.println("Parsing Time : "+parseTime+" ms");
        System.out.println("Generation Time : "+time+" ms");
        System.out.println("Total Time : "+(time+parseTime)+" ms");
        System.out.println("Processors count : "+gen.getConccurentCalls());
    }
    
}
