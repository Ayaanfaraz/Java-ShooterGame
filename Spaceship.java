/**
 * @(#)Spaceship.java
 *
 *
 * @AyaanFaraz
 * @version 1.00 2018/4/20
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyEvent;

public class Spaceship {


    private int x = 680;
    private int y = 600;
    private Image image;

    /**
     * Constructor: Constructs an image icon of a spaceship sprite
     */
    public Spaceship() 
    {

       ImageIcon ii = new ImageIcon("spaceship.png");
        image = ii.getImage(); 

    }

    /**
     * This Method returns the x coordinate of the ship
     * @return int Returns an integer of x coordinate of ship
     */
    public int getX() {
        
        return x;
    }

    /**
     * This Method returns the y coordinates of the ship
     * @return int Returns an integer of y coordinate of ship
     */
    public int getY() {
        
       return y;
    }

    /**
     * This Method returns the image of the ship
     * @return Image The image icon (Ship)
     */
    public Image getImage() {
        
        return image;
    }

    /**
     * This is a keylistener which listens to AD keys pressed
     * @param e KeyEvent, which key is pressed
     */
    public void keyPresse(KeyEvent e) {

        int key = e.getKeyCode();

        if (key ==KeyEvent.VK_A) {
            x += -10;
        }

        if (key == KeyEvent.VK_D) {
            x+= 10;
        }


    }

    /**
     * This is a keylistener which listens to AD keys released
     * @param e KeyEvent, which key is released
     */
    public void keyRelease(KeyEvent e) {
        
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A) {
            x += 0;
        }

        if (key == KeyEvent.VK_D) {
            x += 0;
        }


    }
}
