/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asinfo.delarousse.models;

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
    String picture;

    public ExpressionDelahochienne(int ID, String Expression, String Meaning, String Picture)
    {
        id = ID;
        expression = Expression;
        meaning = Meaning;
        picture = Picture;
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

                try (ResultSet result = statement.executeQuery("SELECT id, expression, signification, illustration FROM ExpressionsDelahochiennes ORDER BY LOWER(expression)"))
                {
                    while (result.next()) {
                        int ID;
                        String ExpressionTrans;
                        String Meaning;
                        String Picture;

                        ID = result.getInt("id");
                        ExpressionTrans = result.getString("expression");
                        Meaning = result.getString("signification");
                        Picture = result.getString("illustration");

                        res.add(new ExpressionDelahochienne(ID, ExpressionTrans, Meaning, Picture));

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
    
    public static void updateAtIndex(int index, String expression, String meaning /*ADD BLOB*/) throws SQLException
    {
        if(DB.getConnection() != null)
        {
            if(!DB.getConnection().isClosed())
            {
                PreparedStatement statement = DB.getConnection().prepareStatement("UPDATE ExpressionsDelahochiennes SET expression = ?, signification = ? WHERE id = ?");
                statement.setString(1, expression);
                statement.setString(2, meaning);
                statement.setInt(3, index);
                
                statement.execute();
            }
        }
    }
    
    public static void add(String expression, String meaning /*ADD BLOB*/) throws SQLException
    {
        if(DB.getConnection() != null)
        {
            if(!DB.getConnection().isClosed())
            {
                PreparedStatement statement = DB.getConnection().prepareStatement("INSERT INTO ExpressionsDelahochiennes(expression, signification) VALUES(?, ?)");
                statement.setString(1, expression);
                statement.setString(2, meaning);
                
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

    public String getPicture()
    {
        return picture;
    }

    @Override
    public String toString()
    {
        return "ExpressionDelahochienne{" + "id=" + id + ", expression=" + expression + ", meanning=" + meaning + ", picture=" + picture + '}';
    }

    
}
