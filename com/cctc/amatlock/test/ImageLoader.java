package com.cctc.amatlock.test.utilities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader
{
    // These store the path to our image folder.
    private static final String RESOURCES = "./resources/";
    private static final String IMAGES = RESOURCES + "images/";

    private BufferedImage image;

    /**
     * Gets and returns the loaded image.
     * @param imagePath file system path to the image
     * @return the loaded image.
     */
    public BufferedImage loadImage(String imagePath) throws IOException
    {
        image = ImageIO.read(new File(IMAGES + imagePath));
        return image;
    }
}
