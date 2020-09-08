/**
 * @(#)MenuScreen.java
 *
 *
 * @AyaanFaraz
 * @version 1.00 2018/4/6
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;

public class MenuScreen {
	private DrawPanel screen;
	private JRadioButton insane;
	private JRadioButton normal;
	private JRadioButton exit;
	private boolean over;
	
    public JPanel init() 
    {
    screen = new DrawPanel(1000, 1000);
		screen.setBackground(Color.BLACK);
		over=false;
		insane = new JRadioButton("Normal", true);
		normal = new JRadioButton("Insane", false);
		exit = new JRadioButton("Exit",false);
		normal.setSelected(true);
		insane.setBackground(Color.GRAY);
		insane.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				screen.setisInsane(false);
			}
		});
		normal.setBackground(Color.GRAY);
		normal.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				screen.setisInsane(true);

			} 
		});
		exit.setBackground(Color.RED);
		exit.setForeground(Color.WHITE);
		exit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				if(screen.getOver())
				{
					saveScore();
				}
				else
				    System.exit(0);

			}
		});
			
		ButtonGroup group = new ButtonGroup();
		group.add(insane);
		group.add(normal);
		group.add(exit);
	
		JPanel panel = new JPanel();
		panel.add(normal);
		panel.add(insane);
		panel.add(exit);
		return panel;

    }
    
    public DrawPanel getScreen()
	{
		return screen;
	}
    public boolean getOver()
	{
		return over;
	}
    public void saveScore()
    {
        JTextArea inputText=new JTextArea();
        JFrame frame = new JFrame();
        frame.setSize(500,100);
        frame.setTitle("Enter Name - to go back to menu click x on the game window");
        JLabel instructionLabel = new JLabel("Type in your name and press enter - " +

				" you can check this in highscore tab");
        //add components to main frame
        frame.add(instructionLabel,BorderLayout.AFTER_LAST_LINE);
        frame.setUndecorated(true);
        frame.add(inputText, BorderLayout.CENTER);
        frame.setVisible(true);
        //add enter keylistener
        inputText.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e)
            {
                File file = new File("save.txt");
                if(e.getKeyCode()==KeyEvent.VK_ENTER) {
                    String text=inputText.getText();
                    inputText.setText("");

                    try (PrintWriter out = new PrintWriter(new FileWriter(file,true))) {
                        out.println(text+" "+screen.getHigh());
                    }catch(IOException a){}
                    frame.setVisible(false);
                }
            }
        });

    }
    
}