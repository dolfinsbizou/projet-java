/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asinfo.delarousse.main;

import com.asinfo.delarousse.controllers.Controller;
import com.asinfo.delarousse.models.DB;
import static java.lang.System.exit;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Clément
 */
public class Main {
    
    
    
    public static void main (String[] args)
    {
        /*
        try
        {
            DB.createConnection("/home/doug-rattmann/Documents/NetBeansProjects/delahaye/Java/DB/BaseDelahochienne.sqlite");
        }
        catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            exit(1);
        }
        //*/
        
        Controller controller = new Controller();
    }
}
