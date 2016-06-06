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
 * Filtre de sélection de BDD
 * @author doug-rattmann
 */
public class DBFileFilter extends FileFilter
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
        
        return "sqlite".equals(ext);
    }

    @Override
    public String getDescription()
    {
        return "Fichiers de base de données SQLite (.sqlite)";
    }
    
}
