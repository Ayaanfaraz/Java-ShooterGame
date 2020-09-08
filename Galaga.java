/**
 * @(#)Galaga.java
 *
 *
 * @AyaanFaraz
 * @version 1.00 2018/4/6
 */

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.SourceDataLine;
import javax.swing.*;

import static javafx.application.Platform.exit;

public class Galaga {


    private JFrame frame;
	private JPanel MainPanel;
    private int w;
    private int h;

    /**
     * Constructs a main menu panel
     */
    public Galaga() 
    {
        w=900;
        h=680;
        MainPanel = new JPanel();
        frame = new JFrame();
        initFrame(frame);

    }

    /**
     * Like the first constructor, pure instance of a main panel and its assorted components in one place
     */
    public void addMain()
	{
		MainMenu pan = new MainMenu();
		frame.add(MainPanel);
		MainPanel.setPreferredSize(new Dimension(w, h));
		BorderLayout layout = new BorderLayout();
		MainPanel.setLayout(layout);
		MainPanel.add(pan.init());
	}

    /**
     * This Method initializes a JFrame adding the essentials for simplification
     * @param f A JFrame to add all the components to
     */
    public void initFrame(JFrame f)
    {
        f.setDefaultLookAndFeelDecorated(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("Intergalactic Survival");
        addMain();
        f.setSize(w, h);
        f.setVisible(true);
        f.setResizable(false);

        f.addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent e)
            {

                if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
                {
                    System.exit(0);

                }
            }
        });
        System.out.println("PRESSED");
    }

//    public void BackgroundSelectListener(JFrame f)
//    {
//        f.addKeyListener(new KeyAdapter() {
//            public void keyPressed(KeyEvent e) {
//                //If numbers pressed the frame changes levels (different music and backgrounds)
//                if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
//                {
//                   System.exit(0);
//                }
//
//            }
//        });
//    }


    /**
     * Main Method creates a main class that connects with the rest of all classes
     * @param args
     * @throws IOException any exception handled from some methods in other classes
     */
    public static void main(String[] args) throws IOException
    {
        Galaga test = new Galaga();
	}



}
