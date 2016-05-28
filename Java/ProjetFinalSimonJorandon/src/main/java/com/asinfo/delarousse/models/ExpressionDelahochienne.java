/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asinfo.delarousse.models;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Clément
 */
public class ExpressionDelahochienne
{

    int id;
    String expression;
    String meaning;
    
    public ExpressionDelahochienne(int ID, String Expression, String Meaning)
    {
        id = ID;
        expression = Expression;
        meaning = Meaning;
    }

    /**
     *
     * @return
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

                try (ResultSet result = statement.executeQuery("SELECT id, expression, signification FROM ExpressionsDelahochiennes ORDER BY LOWER(expression)"))
                {
                    while (result.next())
                    {
                        int ID;
                        String ExpressionTrans;
                        String Meaning;

                        ID = result.getInt("id");
                        ExpressionTrans = result.getString("expression");
                        Meaning = result.getString("signification");

                        res.add(new ExpressionDelahochienne(ID, ExpressionTrans, Meaning));

                    }
                }
                return res;
            }
        }
        return null;
    }
    
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
    
    public static void updateAtIndex(int index, String expression, String meaning, Blob illustration) throws SQLException
    {
        if(DB.getConnection() != null)
        {
            if(!DB.getConnection().isClosed())
            {
                PreparedStatement statement = DB.getConnection().prepareStatement("UPDATE ExpressionsDelahochiennes SET expression = ?, signification = ?, illustration = ? WHERE id = ?");
                statement.setString(1, expression);
                statement.setString(2, meaning);
                statement.setInt(4, index);
                statement.setBlob(3, illustration);
                
                statement.execute();
            }
        }
    }
    
    public static void add(String expression, String meaning, byte[] illustration) throws SQLException
    {
        if(DB.getConnection() != null)
        {
            if(!DB.getConnection().isClosed())
            {
                PreparedStatement statement = DB.getConnection().prepareStatement("INSERT INTO ExpressionsDelahochiennes(expression, signification, illustration) VALUES(?, ?, ?)");
                statement.setString(1, expression);
                statement.setString(2, meaning);
                statement.setBytes(3, illustration);
                
                statement.execute();
            }
        }
    }

    public int getId()
    {
        return id;
    }

    public String getExpression()
    {
        return expression;
    }

    public String getMeaning()
    {
        return meaning;
    }
    
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
