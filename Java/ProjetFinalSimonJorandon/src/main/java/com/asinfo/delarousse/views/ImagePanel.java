/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asinfo.delarousse.views;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author doug-rattmann
 */
public class ImagePanel extends javax.swing.JPanel
{
    private BufferedImage img;
    private String extension;
    
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

    public void setImg(BufferedImage img, String extension)
    {
        this.img = img;
        this.extension = extension;
    }
    
    public boolean isImgSet()
    {
        return this.img!=null;
    }
    
    public BufferedImage getImg()
    {
        return img;
    }
    
    public String getExtension()
    {
        return extension;
    }
}
