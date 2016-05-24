/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asinfo.delarousse.models;
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Clément
 */
public class DB
{
    private static Connection connection = null;

    /**
     *
     * @param path
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public static void createConnection(String path) throws ClassNotFoundException, SQLException
    { 
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:" + path);
    }
    
    public static void closeConnection()
    {
        try
        {
            connection.close();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * 
     * @return la connexion à la BDD
     */
    public static Connection getConnection()
    {
        return connection;
    }
}
