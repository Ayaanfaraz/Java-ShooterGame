/**
 * @(#)MainMenu.java
 * @Ayaan Faraz
 * @version 1.00 2018/4/9
 */

import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu
{
	private JButton Instructions;
	private JButton Highscores;
	private JButton Play;
	private JButton Exit;

	protected boolean inMenu;

    public JPanel init() 
    {

    // All about the 4 main buttons
    Instructions = new JButton("About");
    Instructions.setBackground(Color.lightGray);
    Instructions.setPreferredSize(new Dimension(200, 100));
    Instructions.addActionListener(new ActionListener()
    {

            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
              //  System.out.println("You clicked the button Instructions (Debug)");
                drawAbout();
                
            }
    });
    Highscores = new JButton("Highscores");
    Highscores.setBackground(Color.lightGray);
    Highscores.setPreferredSize(new Dimension(200, 100));
    Highscores.addActionListener(new ActionListener()
    {

            public void actionPerformed(ActionEvent e)
            {
                HighscoreMenu highS= new HighscoreMenu();
                highS.HighscoreMenuA();
                //highS.getFrame();
            }
    });
    Play = new JButton(new ImageIcon("playtext.png"));
    Play.setBackground(Color.GREEN);
    Play.setPreferredSize(new Dimension(200, 100));
    Play.addActionListener(new ActionListener() 
    {

     public void actionPerformed(ActionEvent e) 
     	{
                              
      			drawPlay();	
        }

    });

    Exit = new JButton(new ImageIcon("exitText.png"));
    Exit.setBackground(Color.RED);
    Exit.setPreferredSize(new Dimension(200, 100));
    Exit.addActionListener(new ActionListener()
    {

            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
               System.exit(0);
            }
    });
    //Add Buttons to Panel
    JPanel paneldiff = new JPanel();
    addButtons(paneldiff);
    //Add image and add text overlay over that
    paneldiff.add(mainBackground());
	return paneldiff;
    }
    
    public void drawPlay()
    {
     //Execute when button is pressed
    //System.out.println("You clicked the button play(Debug)");
  
  	JFrame frame = new JFrame();
  	frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
  	frame.setBackground(Color.BLACK);
  	
  	frame.setUndecorated(true);

  	MenuScreen pan = new MenuScreen();
  	JPanel panel = new JPanel();	
  	frame.add(panel);
	panel.setPreferredSize(new Dimension(400, 400));
	BorderLayout layout = new BorderLayout();
	layout.setVgap(20);
	panel.setLayout(layout);
	panel.add(pan.init(), BorderLayout.SOUTH);
	panel.add(pan.getScreen(), BorderLayout.CENTER);
  	frame.setVisible(true);

    }
    
    public void addButtons(JPanel panel)
    {
    panel.setPreferredSize(new Dimension(900,680));
    panel.add(Play,BorderLayout.CENTER);
        ImageIcon rollover1 = new ImageIcon("playbutton.png");
        Play.setRolloverIcon(rollover1);
    panel.add(Instructions,BorderLayout.CENTER);
    panel.add(Highscores,BorderLayout.CENTER);
    panel.add(Exit,BorderLayout.CENTER);
    ImageIcon rollover = new ImageIcon("close.png");
    Exit.setRolloverIcon(rollover);
    }
    
    public void drawAbout()
    {
    JFrame frame = new JFrame();
  	frame.setSize(700,700);
  	frame.setBackground(Color.WHITE);
  	JButton ScreenExit = new JButton("Back");
        ScreenExit.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {

                frame.setVisible(false);
            }

        });
        ScreenExit.setPreferredSize(new Dimension(200, 50));
    frame.add(ScreenExit,BorderLayout.PAGE_END);
    frame.setTitle("By : Ayaan Faraz");
    JLabel label=new JLabel(new ImageIcon("whitespace.png"));
    label.setLayout(new BorderLayout());
    frame.add(label);
    frame.setVisible(true);
    }
    
    	
    public JLabel mainBackground()
    {
    //try {
        JLabel label=new JLabel(new ImageIcon("galaga.jpg"));
        label.setLayout(new BorderLayout());
        JLabel labeltext = new JLabel("Intergalactic Survival");
        Font font = new Font("Papyrus",100,68);
        
        labeltext.setFont(font);
        labeltext.setForeground(Color.WHITE);
        labeltext.setHorizontalAlignment(JLabel.CENTER);
        label.add(labeltext);
        return label;
	//} catch (Exception e){}
    }

}