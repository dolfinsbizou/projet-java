/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asinfo.delarousse.views;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author doug-rattmann
 */
public class DBFileFilter extends FileFilter
{

    @Override
    public boolean accept(File file)
    {
        if(file.isDirectory())
            return false;
        
        String ext = null;
        String s = file.getName();
        int i = s.lastIndexOf('.');
        if(i > 0 && i < s.length())
        {
            ext = s.substring(i+1).toLowerCase();
        }   
        
        return "sqlite".equals(ext);
    }

    @Override
    public String getDescription()
    {
        return "Fichiers de base de données SQLite (.sqlite)";
    }
    
}
