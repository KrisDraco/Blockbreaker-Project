package com.cctc.amatlock.test;

import java.awt.*;

public abstract class CoreObject extends Rectangle
{
    Color color;    // Set the color of the object.
    double velX = 0;   // Speed obj moves horizontally
    double velY = 0;   // Speed obj moves vertically

    /**
     * Creates the core object. All subclasses
     * will call this with super.
     * The super call to the Rectangle class.
     */
    public CoreObject(int x, int y, int width, int height, Color color)
    {
        super(x, y, width, height);
        this.color = color;
    }

    public abstract void tick();
    public abstract void render(Graphics g);

    public void setVelX(double velX)
    {
        this.velX = velX;
    }
    public void setVelY(double velY)
    {
        this.velY = velY;
    }
    public double getVelX()
    {
        return velX;
    }
    public double getVelY()
    {
        return velY;
    }
}