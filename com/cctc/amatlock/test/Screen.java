package com.cctc.amatlock.test;

import com.cctc.amatlock.test.utilities.ResourceLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Screen extends Canvas implements Runnable
{
    private static final long serialVersionUID = -1890564841829395437L;

    private static JFrame frame = new JFrame();  // This is the window object
    private static Screen screen = new Screen();  // Our program
    private static CoreObject[] coreObjects = new CoreObject[200];
    private static int objectCounter = 0;

    // Instance variables
    private boolean running = false;  // Boolean flipped when the program starts or stops.
    private Thread thread;  // Don't worry about what this is.

    public Ball ball;
    public Paddle paddle;
    public Brick brick;

    public static Screen getInstance()
    {
        return screen;
    }
    public static CoreObject[] getCoreObjects()
    {
        return coreObjects;
    }
    public static int getObjectCounter()
    {
        return objectCounter;
    }
    public static void addObject(CoreObject object)
    {
        coreObjects[objectCounter] = object;
        objectCounter++;
    }

    /**
     * Used to draw the backdrop for our program.
     * @param g graphics engine used to draw 2d in window.
     */
    public void drawBackground(Graphics g)
    {

        // Making a dark gray background.
        // First set the draw color to dark grey.
        g.setColor(Color.WHITE);

        // Next make a rectangle starting in the top right corner (first 2 parameters)
        // Make it the width and height of the window (last 2 parameters)
        g.fillRect(0, 0, Reference.WIDTH, Reference.HEIGHT);
    }

    public void drawForeground(Graphics g)
    {
        ball.render(g);
        paddle.render(g);
        brick.render(g);
        for (int i = 0; i < objectCounter; i++ )
        {
            coreObjects[i].render(g);
        }

    }

    public void render()
    {
        // Buffer strategy and graphics are used to draw the pixels on the screen.
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null)
        {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics(); // This is what is used to draw pixels on our screen.

        // Draw here.
        drawBackground(g);
        drawForeground(g);



        g.dispose();  // Disposes our graphics context (if we did not do this, animations would not work properly, it would also eat up a lot of memory
        bs.show();  // Shows whatever graphics were just disposed of
    }

    public void tick() {
        ball.tick();
        paddle.tick();
        brick.tick();
        for (int i = 0; i < objectCounter; i++ )
        {
            coreObjects[i].tick();
        }

    }
    /**
     * Does the things needed when our program starts.
     */
    public void init()
    {
//        ResourceLoader.loadImages();    // loads images from files.

        ball = new Ball(Reference.CENTER_X, Reference.CENTER_Y, 10, 10, Color.BLACK);
        paddle = new Paddle(Reference.CENTER_X, Reference.HEIGHT - 40,50, 10, Color.BLUE);
        brick = new Brick(Reference.CENTER_X, 0, 40, 49, Color.RED);

        ball.setVelX(3);
        ball.setVelY(-3);

        KeyInput keyInput = new KeyInput();
        this.addKeyListener(keyInput);


        int y = 0;

        for(int a = 0; a < 4; a++)
        {
            int x = 0;
            for(int i = 0; i < 21; i++)
            {
                Brick brick = new Brick(x, y, 30, 20, Color.RED);
                x += 31;
                addObject(brick);
            }
            y += 22;
        }

    }

    @Override
    /**
     * This run method is what runs the program.
     * We use a while true loop to repeatedly draw our screen.
     */
    public void run()
    {
        init(); //Initializes our program

        // This code has to do with making sure our program only updates so many times a second.

        long lastTime = System.nanoTime();  // Get the time when the program starts.
        final double numTicks = 60.0;  // This is the number of times per second we want to tick. (fps)
        double n = 1000000000 / numTicks;
        double delta = 0;   // Number of nanoseconds since last tick.
        int frames = 0;     // Counts the frames per second.
        int ticks = 0;      // Number of ticks per second.
        long timer = System.currentTimeMillis();  // Time in milliseconds

        while (running)
        {
            long currentTime = System.nanoTime();   // Current time in milliseconds
            delta += (currentTime - lastTime) / n;  // Add time passed since the last frame.
            lastTime = currentTime;  // Update "lastTime" for when the loop reruns.

            if (delta >= 1)
            {
                // Has it been long enough to update
                tick();  // Used to update things between frames
                ticks++;  // Increment ticks
                delta--; // Reset the delta
            }

            render();  // Renders the screen
            frames++;  // Increment the number of frames

            if (System.currentTimeMillis() - timer > 1000)
            {
                // Used to print the frames and ticks per second
                timer += 1000;
                System.out.println(ticks + " Ticks, FPS: " + frames);  // Prints the TPS and FPS to console
                frame.setTitle(Reference.TITLE + "        Ticks: " + ticks + "    FPS: " + frames);   // Adds frames and ticks to window title.
                ticks = 0;  // Reset ticks and frames
                frames = 0;
            }
        }
        stop(); // Once exit the loop, stop the program
    }

    public static void main(String args[])
    {
        frame.add(screen);  // Adds our program as a component to the frame
        frame.setTitle(Reference.TITLE);
        frame.setSize(Reference.WIDTH + 7, Reference.HEIGHT + 30); // Size of our window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit the program with X
        frame.setFocusable(true); // Allows click and input
        frame.setLocationRelativeTo(null); // Starts the window in the middle of the screen
        frame.setResizable(false); // Allowing resizing will complicate things more.
        frame.setVisible(true); // This shows the frame/window
        frame.pack();
        screen.start(); // Starts the program
    }

    private synchronized void start() {
        if (running) // If program is running, we do not want to run the program again
            return;
        else
            running = true;
        thread = new Thread(this);  // thread that controls our program
        thread.start();  // starts the thread, thus our program
    }

    private synchronized void stop() {
        if (!running)  // If the program has stopped, why stop it again?
            return;
        else
            running = false;

        try {
            thread.join();  // Convenient way to close thread.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.exit(1);  // exits program
    }
}