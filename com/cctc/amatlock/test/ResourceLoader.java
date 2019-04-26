package com.cctc.amatlock.test.utilities;

import java.io.IOException;

public class ResourceLoader
{
    // Creates an instance of ImageLoader
    // This is used to load each image
    private static ImageLoader imageLoader = new ImageLoader();

    /**
     * This method will load and store every image
     * we plan to use in our program. This is the
     * only method we will change when we need to
     * add more images.
     */
    public static void loadImages()
    {
        // The try/catch is code to catch if
        // any image isn't found.
        try{
            Images.background = imageLoader.loadImage("background.png");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
