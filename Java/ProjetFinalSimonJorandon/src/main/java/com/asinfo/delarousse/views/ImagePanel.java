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
package com.asinfo.delarousse.views;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * JPanel gérant l'affichage de l'illustration dans la fenêtre
 * @author doug-rattmann
 */
public class ImagePanel extends javax.swing.JPanel
{
    private BufferedImage img;
    private String extension;
    
    /**
     * Construit le panel avec une image de base
     * @param img image de base
     */
    public ImagePanel(BufferedImage img)
    {
        super();
        this.img = img;
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        g.clearRect(0, 0, getWidth(), getHeight());
        if(img != null)
        {
            int width = img.getWidth(), height = img.getHeight();
            int pWidth = this.getWidth(), pHeight = this.getHeight();
            int dWidth, dHeight;
            
            if(width > height)
            {
                dWidth = pWidth;
                dHeight = (int)((height*pWidth)/((float)width));
                if(dHeight > pHeight)
                {
                    dWidth = (int)((width*pHeight)/((float)height));
                    dHeight = pHeight;
                }
            }
            else
            {
                dWidth = (int)((width*pHeight)/((float)height));
                dHeight = pHeight;
                if(dWidth > pWidth)
                {
                    dWidth = pWidth;
                    dHeight = (int)((height*pWidth)/((float)width));
                }
            }
            
            g.drawImage(img, 0, 0, dWidth, dHeight, null);
            
        }
    }

    /**
     * modifie l'image affichée
     * @param img nouvelle image
     * @param extension nouvelle extension
     */
    public void setImg(BufferedImage img, String extension)
    {
        this.img = img;
        this.extension = extension;
    }
    
    /**
     * vérifie si le panel affiche une image
     * @return vrai si img!=null
     */
    public boolean isImgSet()
    {
        return this.img!=null;
    }
    
    /**
     * accesseur de l'illustration
     * @return img
     */
    public BufferedImage getImg()
    {
        return img;
    }
    
    public String getExtension()
    {
        return extension;
    }
}
