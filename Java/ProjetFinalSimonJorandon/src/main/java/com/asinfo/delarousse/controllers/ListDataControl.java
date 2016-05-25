/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asinfo.delarousse.controllers;

import com.asinfo.delarousse.models.ExpressionDelahochienne;
import com.asinfo.delarousse.views.Window;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author doug-rattmann
 */
public class ListDataControl extends javax.swing.AbstractListModel<String>
{
    private ArrayList<ExpressionDelahochienne> entries;

    public ListDataControl()
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
        return entries!=null?entries.size():0;
    }
    
    @Override
    public String getElementAt(int i)
    {
        return entries!=null?entries.get(i).getExpression():"";
    }
    
    public ExpressionDelahochienne getEntryAt(int i)
    {
        return entries!=null?entries.get(i):null;
    }
    
    public void deleteEntryAt(int i)
    {
        try
        {
            ExpressionDelahochienne.deleteAtIndex(entries.get(i).getId());
            entries = ExpressionDelahochienne.getList();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
