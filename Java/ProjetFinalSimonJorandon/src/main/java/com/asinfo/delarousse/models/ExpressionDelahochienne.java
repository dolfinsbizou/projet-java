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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Modèle, stocke une entrée de base de données (sauf l'image correspondante, qui sera chargée à la volée lors de l'affichage afin d'économiser les ressources)
 * @author Clément
 */
public class ExpressionDelahochienne
{

    int id;
    String expression;
    String meaning;
    String extension;
    
    /**
     * Crée une nouvelle entrée, référant à une entrée en BDD
     * @param id id de l'entrée
     * @param expression valeur de l'expression
     * @param meaning valeur de la signification
     * @param extension valeur de l'extension
     */
    public ExpressionDelahochienne(int id, String expression, String meaning, String extension)
    {
        this.id = id;
        this.expression = expression;
        this.meaning = meaning;
        this.extension = extension;
    }

    /**
     * récupère toutes les entrées en BDD
     * @return ArrayList de ExpressionDelahochienne
     * @throws SQLException
     */
    public static ArrayList<ExpressionDelahochienne> getList() throws SQLException
    {
        if(DB.getConnection() != null)
        {
            if(!DB.getConnection().isClosed())
            {
                Statement statement = DB.getConnection().createStatement();

                ArrayList<ExpressionDelahochienne> res = new ArrayList();

                try (ResultSet result = statement.executeQuery("SELECT id, expression, signification, extension FROM ExpressionsDelahochiennes ORDER BY LOWER(expression)"))
                {
                    while (result.next())
                    {
                        int id;
                        String expressionTrans;
                        String meaning;
                        String extension;

                        id = result.getInt("id");
                        expressionTrans = result.getString("expression");
                        meaning = result.getString("signification");
                        extension = result.getString("extension");

                        res.add(new ExpressionDelahochienne(id, expressionTrans, meaning, extension));

                    }
                }
                return res;
            }
        }
        return null;
    }
    
    /**
     * Supprime une entrée
     * @param i id en BDD de l'entrée à supprimer
     * @throws SQLException 
     */
    public static void deleteAtIndex(int i) throws SQLException
    {
        if(DB.getConnection() != null)
        {
            if(!DB.getConnection().isClosed())
            {
                PreparedStatement statement = DB.getConnection().prepareStatement("DELETE FROM ExpressionsDelahochiennes WHERE id = ?");
                
                statement.setInt(1, i);
                
                statement.execute();
            }
        }
    }
    
    /**
     * met à jour une entrée
     * @param index id en BDD de l'entrée à mettre à jour
     * @param expression nouvelle valeur de l'expression
     * @param meaning nouvelle valeur de la signification
     * @param illustration nouvelle valeur de l'illustration
     * @param extension nouvelle valeur de l'extension
     * @throws SQLException 
     */
    public static void updateAtIndex(int index, String expression, String meaning, byte[] illustration, String extension) throws SQLException
    {
        if(DB.getConnection() != null)
        {
            if(!DB.getConnection().isClosed())
            {
                PreparedStatement statement = DB.getConnection().prepareStatement("UPDATE ExpressionsDelahochiennes SET expression = ?, signification = ?, illustration = ?, extension = ? WHERE id = ?");
                statement.setString(1, expression);
                statement.setString(2, meaning);
                statement.setInt(5, index);
                statement.setBytes(3, illustration);
                statement.setString(4, extension);
                
                statement.execute();
            }
        }
    }
    
    /**
     * ajoute une entrée
     * @param expression valeur de l'expression
     * @param meaning valeur de la signification
     * @param illustration valeur de l'illustration
     * @param extension valeur de l'extension
     * @throws SQLException 
     */
    public static void add(String expression, String meaning, byte[] illustration, String extension) throws SQLException
    {
        if(DB.getConnection() != null)
        {
            if(!DB.getConnection().isClosed())
            {
                PreparedStatement statement = DB.getConnection().prepareStatement("INSERT INTO ExpressionsDelahochiennes(expression, signification, illustration, extension) VALUES(?, ?, ?, ?)");
                statement.setString(1, expression);
                statement.setString(2, meaning);
                statement.setBytes(3, illustration);
                statement.setString(4, extension);
                
                statement.execute();
            }
        }
    }

    /**
     * accesseur de l'id
     * @return id
     */
    public int getId()
    {
        return id;
    }

    /**
     * accesseur de l'expression
     * @return expression
     */
    public String getExpression()
    {
        return expression;
    }

    /**
     * accesseur de la signification
     * @return meaning
     */
    public String getMeaning()
    {
        return meaning;
    }
    
    /**
     * accesseur de l'extension
     * @return extension
     */
    public String getExtension()
    {
        return extension;
    }
    
    /**
     * Récupère l'illustration
     * @param index id en BDD de l'entrée dont on doit récupérer l'illustration
     * @return un tableau de bytes décrivant l'image
     * @throws SQLException
     */
    public static byte[] retrieveIllustration(int index) throws SQLException
    {
        if(DB.getConnection() != null)
        {
            if(!DB.getConnection().isClosed())
            {
                PreparedStatement statement = DB.getConnection().prepareStatement("SELECT illustration FROM ExpressionsDelahochiennes WHERE id = ?");
                
                statement.setInt(1, index);
                
                byte[] res = null;

                try (ResultSet result = statement.executeQuery())
                {
                    if (result.next())
                    {
                        int ID;
                        String ExpressionTrans;
                        String Meaning;

                        res = result.getBytes("illustration");
                    }
                }
                return res;
            }
        }
        return null;
    }

    @Override
    public String toString()
    {
        return "ExpressionDelahochienne{" + "id=" + id + ", expression=" + expression + ", meanning=" + meaning + '}';
    }

    
}
