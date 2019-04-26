package com.cctc.amatlock.test;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener
{
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        Paddle paddle = Screen.getInstance().paddle;
        Ball ball = Screen.getInstance().ball;

        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {

            paddle.setVelX(4);

        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            paddle.setVelX(-4);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        Paddle paddle = Screen.getInstance().paddle;
        Ball ball = Screen.getInstance().ball;

        if( e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            paddle.setVelX(0);
        }

        else if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            paddle.setVelX(0);
        }

    }
}
