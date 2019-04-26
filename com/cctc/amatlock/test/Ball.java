package com.cctc.amatlock.test;

import java.awt.*;

public class Ball extends CoreObject
{
    /**
     * Creates the core object. All subclasses
     * will call this with super.
     * The super call to the Rectangle class.
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param color
     */
    public Ball(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    public boolean bounce = false;

    @Override
    public void tick() {
        Paddle paddle = Screen.getInstance().paddle;
        x += velX;
        y += velY;
        if(intersects(paddle))
        {
            velY = velY * -1;
        }

        if(x < 0 || x > Reference.WIDTH)
        {
            setVelX(getVelX() * -1);
        }
        if(y < 0 || y > Reference.HEIGHT)
        {
            setVelY(getVelY() * -1);
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
}
