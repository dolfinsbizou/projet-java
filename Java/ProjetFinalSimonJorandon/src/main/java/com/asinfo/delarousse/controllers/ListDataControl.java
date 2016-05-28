/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asinfo.delarousse.controllers;

import com.asinfo.delarousse.models.ExpressionDelahochienne;
import com.asinfo.delarousse.models.ImageBlobManager;
import com.asinfo.delarousse.views.Window;
import java.awt.image.BufferedImage;
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
    
    public int getListIndexFromDBIndex(int i)
    {
        int r = 0;
        for (ExpressionDelahochienne e : entries)
        {
            if(e.getId() == i)
                return r;
            r++;
        }
        return -1;
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
    
    public void updateEntryAt(int i, String expression, String meaning, BufferedImage illustration)
    {
        try
        {
            ExpressionDelahochienne.updateAtIndex(entries.get(i).getId(), expression, meaning, illustration);
            entries = ExpressionDelahochienne.getList();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ListDataControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addEntry(String expression, String meaning, BufferedImage illustration, String extension)
    {
        try
        {
            ExpressionDelahochienne.add(expression, meaning, ImageBlobManager.createBlob(illustration, extension));
            entries = ExpressionDelahochienne.getList();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ListDataControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public byte[] retrieveIllustration(int index)
    {
        try
        {
            return entries!=null?ExpressionDelahochienne.retrieveIllustration(entries.get(index).getId()):null;
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ListDataControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
