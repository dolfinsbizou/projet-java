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
public class ImageFileFilter extends FileFilter
{

    @Override
    public boolean accept(File file)
    {
        if(file.isDirectory())
            return true;
        
        String ext = null;
        String s = file.getName();
        int i = s.lastIndexOf('.');
        if(i > 0 && i < s.length())
        {
            ext = s.substring(i+1).toLowerCase();
        }   
        
        if(ext!=null)
            switch(ext)
            {
                case "jpeg":
                case "jpg":
                case "png":
                case "tiff":
                case "tif":
                case "gif":
                    return true;
                default:
                    return false;
            }
        else
            return false;
    }

    @Override
    public String getDescription()
    {
        return "Fichiers image (.png, .jp(e)g, .tif(f), .gif)";
    }
    
}
