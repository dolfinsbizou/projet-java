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
import java.sql.Blob;
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
    public static Blob createBlob(BufferedImage img, String extension) throws SQLException
    {
        try
        {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream(); 
            ImageIO.write(img, extension, bytes);
            Blob blob = DB.getConnection().createBlob();
            blob.setBytes(1, bytes.toByteArray());
            return blob;
        }
        catch (IOException ex)
        {
            Logger.getLogger(ImageBlobManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static BufferedImage createImage(Blob blob) throws SQLException
    {
        try
        {
            byte[] bytes = blob.getBytes(1, (int) blob.length());
            return ImageIO.read(new ByteArrayInputStream(bytes));
        }
        catch (IOException ex)
        {
            Logger.getLogger(ImageBlobManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
