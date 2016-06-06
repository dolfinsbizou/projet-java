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
 * Classe technique pour convertir des byte[] en BufferedImage et vice et versa
 * @author doug-rattmann
 */
public class ImageBlobManager
{
    /**
     * crée un blob d'après une image
     * @param img image d'entrée
     * @param extension extension de l'image
     * @return description de l'image sous forme de byte[]
     * @throws SQLException 
     */
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
    
    /**
     * crée une image d'après un blob
     * @param blob le tableau de bytes d'entrée
     * @return la BufferedImage reconstruite
     * @throws SQLException 
     */
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
