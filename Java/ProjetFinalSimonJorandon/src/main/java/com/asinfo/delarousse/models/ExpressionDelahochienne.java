/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asinfo.delarousse.models;

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

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getExpression()
    {
        return expression;
    }

    public void setExpression(String expression)
    {
        this.expression = expression;
    }

    public String getMeaning()
    {
        return meaning;
    }

    public void setMeaning(String meaning)
    {
        this.meaning = meaning;
    }

    public String getPicture()
    {
        return picture;
    }

    public void setPicture(String picture)
    {
        this.picture = picture;
    }

    @Override
    public String toString()
    {
        return "ExpressionDelahochienne{" + "id=" + id + ", expression=" + expression + ", meanning=" + meaning + ", picture=" + picture + '}';
    }

    
}
