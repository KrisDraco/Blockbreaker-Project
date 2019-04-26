package com.cctc.amatlock.test;

import java.awt.*;

public class Paddle extends CoreObject {

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
    public Paddle(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);

    }
}
