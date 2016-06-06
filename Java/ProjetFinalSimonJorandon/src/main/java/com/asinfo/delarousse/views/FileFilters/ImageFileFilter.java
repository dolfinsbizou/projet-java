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
package com.asinfo.delarousse.views.FileFilters;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 * Filtre de sélection d'image
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
