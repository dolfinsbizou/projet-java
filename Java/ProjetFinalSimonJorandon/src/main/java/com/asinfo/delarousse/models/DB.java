/*
 * Copyright (C) 2016 JORANDON Guillaume, SIMON Clément
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.asinfo.delarousse.models;
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * "Singleton" de la BDD (n'est pas strictement un singleton, puisqu'elle ne contient pas un objet de type DB, mais de type Connection, mais se comporte de manière analogue)
 * @author Clément
 */
public class DB
{
    private static Connection connection = null;

    /**
     * initie une connexion
     * @param path chemin d'accès à la BDD
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public static void createConnection(String path) throws ClassNotFoundException, SQLException
    { 
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:" + path);
    }
    
    /**
     * Met fin à la connexion
     */
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
     * Récupération de la connexion
     * @return la connexion à la BDD
     */
    public static Connection getConnection()
    {
        return connection;
    }
}
