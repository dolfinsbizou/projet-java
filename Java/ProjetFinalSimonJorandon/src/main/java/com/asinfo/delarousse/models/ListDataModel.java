/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asinfo.delarousse.models;

import com.asinfo.delarousse.views.Window;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author doug-rattmann
 */
public class ListDataModel extends javax.swing.AbstractListModel<String>
{
    private ArrayList<ExpressionDelahochienne> entries;

    public ListDataModel()
    {
        try
        {
            entries = ExpressionDelahochienne.getList();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getSize()
    {
        return entries.size();
    }
    
    @Override
    public String getElementAt(int i)
    {
        return entries.get(i).getExpression();
    }
    
    public ExpressionDelahochienne getEntryAt(int i)
    {
        return entries.get(i);
    }
}
