/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asinfo.delarousse.models;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author doug-rattmann
 */
public class ImageBlobManager
{
    public static byte[] createBlob(BufferedImage img, String extension) throws SQLException
    {
        try
        {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream(); 
            ImageIO.write(img, extension, bytes);
            return bytes.toByteArray();
        }
        catch (IOException ex)
        {
            Logger.getLogger(ImageBlobManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static BufferedImage createImage(byte[] blob) throws SQLException
    {
        try
        {
            return ImageIO.read(new ByteArrayInputStream(blob));
        }
        catch (IOException ex)
        {
            Logger.getLogger(ImageBlobManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (java.lang.NullPointerException ex)
        {
            return null;
        }
        return null;
    }
}
