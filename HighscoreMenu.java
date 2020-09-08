/**
 * @(#)HighscoreMenu.java
 *
 *
 * @AyaanFaraz
 * @version 1.00 2018/4/20
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.io.*;


public class HighscoreMenu extends JPanel{
    JTextArea inputText;
    JTextArea feedbackText;
    JFrame frame;
    JButton ScreenExit;
    int index;
    int current;
    int recent;
    String name;
    String text;
    String arrayText;

    /**
     * Like a constructor, initializes the layout for the highscore menu, and brings all parts together
     */
    public void HighscoreMenuA()
    {
        feedbackText = new JTextArea();
        inputText = new JTextArea();
        frame = new JFrame();
        ScreenExit = new JButton("Back");
        index=0;
        name="";
        recent=0;
        text="";
        arrayText="";

        initFrame(frame);
        readFile("save.txt");
    }

    /**
     * This method sets the score to whatever h is inputted
     * @param h The score
     */
    public void setScore(int h)
    {
        current=h;
    }

    /**
     * This method adds the names from file into an array of ID to display in scrollable area
     */
    public void addName(){
        String[] words = arrayText.split("\\.");
        JList list = new JList(words);
        JScrollPane scroll = new JScrollPane(list);
        list.setSelectedIndex(1);
        frame.add(scroll, BorderLayout.CENTER);
    }

    /**
     * This method reads in the records (Name/Score) from the file
     * @param fhand The filehandle or string name of file
     */
    public void readFile(String fhand)
    {
        try {
            File file = new File(fhand);
            Scanner in = new Scanner(file);
             for(int i=0;i<getFileLength(fhand);i++)
             {
                 String test;
                 test = "" + in.next();
                 int n = in.nextInt();
                 test += " " + n;
                 arrayText += test + ".";
             }
            addName();
            in.close();
        }
        catch(IOException e){System.out.println("IOException");}
    }

    /**
     * This method gets the amount of records within a file, to return how many times will have to parse
     * @param fhand The filehandle or file name
     * @return int The integer of total records
     * @throws FileNotFoundException Handles when file is not found or accessible
     */
    public int getFileLength(String fhand)throws FileNotFoundException
    {
        int count =0;
        File file = new File(fhand);
        Scanner in = new Scanner(file);
        while(in.hasNextLine()) {
            in.nextLine();
            count++;
        }
        return count;
    }

    /**
     * This method initializes the main frame for the highscore panel
     * @param f JFrame that represents the main highscore menu
     */
    public void initFrame(JFrame f)
    {
        f.setSize(600,600);
        f.setBackground(Color.BLACK);
        f.setTitle("Highscores");
        f.setUndecorated(true);
        ScreenExit.setPreferredSize(new Dimension(200, 50));
        ScreenExit.addActionListener(new ActionListener()
        {public void actionPerformed(ActionEvent e) { frame.setVisible(false); }});
        f.add(ScreenExit,BorderLayout.PAGE_END);
        f.setVisible(true);
    }


}