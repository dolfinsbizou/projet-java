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
 * Stocke les données de la JList
 * @author doug-rattmann
 */
public class ListDataControl extends javax.swing.AbstractListModel<String>
{
    private ArrayList<ExpressionDelahochienne> entries;

    /**
     * Récupère toutes les entrées de la BDD
     */
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
    
    /**
     * Récupère l'entrée sélectionnée
     * @param i indice de l'entrée
     * @return l'objet ExpressionDelahochienne correspondant
     */
    public ExpressionDelahochienne getEntryAt(int i)
    {
        return entries!=null?entries.get(i):null;
    }
    
    /**
     * Conversion de l'indice de liste->indice en BDD
     * @param i l'indice de liste
     * @return l'id en BDD correspondant
     */
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
    
    /**
     * Supprime une entrée
     * @param i indice de l'entrée à supprimer
     */
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
    
    /**
     * met à jour une entrée
     * @param i indice de l'entrée
     * @param expression nouvelle valeur de l'expression
     * @param meaning nouvelle valeur de la signification
     * @param illustration nouvelle illustration
     * @param extension nouvelle extension
     */
    public void updateEntryAt(int i, String expression, String meaning, BufferedImage illustration, String extension)
    {
        try
        {
            ExpressionDelahochienne.updateAtIndex(entries.get(i).getId(), expression, meaning, ImageBlobManager.createBlob(illustration, extension), extension);
            entries = ExpressionDelahochienne.getList();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ListDataControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * ajoute une entrée
     * @param expression valeur de l'expression
     * @param meaning valeur de la signification
     * @param illustration illustration
     * @param extension extension
     */
    public void addEntry(String expression, String meaning, BufferedImage illustration, String extension)
    {
        try
        {
            ExpressionDelahochienne.add(expression, meaning, ImageBlobManager.createBlob(illustration, extension), extension);
            entries = ExpressionDelahochienne.getList();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ListDataControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Récupère une illustration
     * @param index indice de l'entrée dont on doit récupérer l'illustration
     * @return l'illustration correspondantes
     */
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
