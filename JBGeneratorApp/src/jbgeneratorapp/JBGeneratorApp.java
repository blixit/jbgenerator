/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jbgeneratorapp;

import jbgenerator.lib.JBType;
import java.lang.Integer;

/**
 *
 * @author Blixit
 */
public class JBGeneratorApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JBType type;
        try{
            Integer i = 5;
            type = new JBType(i.getClass());
        }catch(Exception e){
            
        }
    }
    
}
