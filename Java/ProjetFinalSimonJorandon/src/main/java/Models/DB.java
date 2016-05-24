/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement; 
import java.util.ArrayList; 


/**
 *
 * @author Clément
 */
public class DB {
    private static Connection connection;
    //Prototype final de la méthode.
    //public static void connect(String Path) throws ClassNotFoundException{
    public static Statement connect () throws ClassNotFoundException, SQLException{ 
    
        Class.forName("org.sqlite.JDBC");
        //Méthode finale de la fonction
        //Connection connection = DriverManager.getConnection(Path);
        connection = DriverManager.getConnection("C:\\Users\\Clément\\Desktop\\Java\\DB\\BaseDelahochienne.sqlite");
        return connection.createStatement();
    }
    
}
