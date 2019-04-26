package com.cctc.amatlock.test;

import java.awt.*;

public class Brick extends CoreObject {

    private boolean broke = false;
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
    public Brick(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    @Override
    public void tick() {
        Ball ball = Screen.getInstance().ball;

        Rectangle right = new Rectangle(x + width, y, 1, height);
        Rectangle left = new Rectangle(x, y, 1, height);
        Rectangle top = new Rectangle(x, y, width, 1);
        Rectangle bottom = new Rectangle(x, y + height, width, 1);

        if(intersects(ball) && !broke)
        {
            if(top.intersects(ball) || bottom.intersects(ball))
            {
                broke = true;
                if(!ball.bounce)
                {
                    ball.setVelY(ball.getVelY() * -1 );
                }

            }
            if(left.intersects(ball) || right.intersects(ball))
            {
                broke = true;
                if(!ball.bounce) {
                    ball.setVelX(ball.getVelX() * -1);
                }
            }

            broke = true;
        }

        x += velX;
        y += velY;
    }

    @Override
    public void render(Graphics g) {

        if (!broke)
        {
            g.setColor(color);
            g.fillRect(x, y, width, height);
        }

    }
}
