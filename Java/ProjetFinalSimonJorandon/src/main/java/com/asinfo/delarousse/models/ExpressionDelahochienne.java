/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asinfo.delarousse.models;

import java.sql.Connection;
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
    String meanning;
    String picture;

    public ExpressionDelahochienne(int ID, String Expression, String Meanning, String Picture)
    {
        id = ID;
        expression = Expression;
        meanning = Meanning;
        picture = Picture;
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    public static ArrayList<ExpressionDelahochienne> getAll() throws SQLException
    {
        Statement statement = DB.getConnection().createStatement();
        
        ArrayList<ExpressionDelahochienne> res = new ArrayList();
        
        try (ResultSet result = statement.executeQuery("SELECT * FROM ExpressionsDelahochiennes"))
        {
            while (result.next()) {
                int ID;
                String ExpressionTrans;
                String Meanning;
                String Picture;
                
                ID = result.getInt("id");
                ExpressionTrans = result.getString("expression");
                Meanning = result.getString("signification");
                Picture = result.getString("illustration");
                
                res.add(new ExpressionDelahochienne(ID, ExpressionTrans, Meanning, Picture));
                
            }
        }
        return res;
        
    }

    @Override
    public String toString()
    {
        return "ExpressionDelahochienne{" + "id=" + id + ", expression=" + expression + ", meanning=" + meanning + ", picture=" + picture + '}';
    }

    
}
