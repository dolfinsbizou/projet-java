/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import Models.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Clément
 */
public class Main {
    
    
    
    public static void main ( String[] args) throws SQLException
    {
        DB base = new DB();
        
        try {
            for (ExpressionDelahochienne expression : ExpressionDelahochienne.RecupALL(DB.connect()))
            {
                System.out.println(expression);
            }   } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
