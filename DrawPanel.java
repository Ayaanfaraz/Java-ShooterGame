/**
 * @(#)DrawPanel.java
 *
 *
 * @AyaanFaraz
 * @version 1.00 2018/4/9
 */

import java.awt.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.util.Random;
import java.awt.event.KeyEvent;

public class DrawPanel extends JPanel
{

private int x,mx,my,y1,y2,y,shipLocX,shipLocY,enemyPosX,enemyPosY;
private Timer t;
private Graphics g;
private Random r;
private int index;
private boolean isInsane,gameOver;
private boolean missle;
private Image image;
private String[] iconArray;
private	Spaceship ship;

private int high;

    public DrawPanel(int x, int y) 
    {
   		isInsane = false;
		this.x = x;
		this.y = y;
		r = new Random();
		ship= new Spaceship();
		y1=0;
		y2=0;
		gameOver=false;
		shipLocX=850;
		shipLocY=850;
		high=0;
		index=0;
		iconArray = new String[]{"enemy.png","enemy2.png"};
		t = new Timer(100, new ActionListener(){ public void actionPerformed(ActionEvent event){repaint();}});

//		this.addKeyListener(new KeyAdapter()
//        {
//             public void keyPressed(KeyEvent e)
//             {
//                 System.out.println("keypressed");
//             }
//             public void keyReleased(KeyEvent e)
//             {
//                 System.out.println("keyreleased");
//             }
//        });


        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int mouseEventX=e.getX();
                int mouseEventY=e.getY();
                mx=mouseEventX;
                my=mouseEventY;
                missle=true;
            }
        });

    t.start();
        playSound("galagabackground.wav");

    }
    	public void paintComponent(Graphics Graphic)
	{
		super.paintComponent(Graphic);
		//int size = (int) Math.floor(Math.random()*1600);
        int size= r.nextInt(2500 - 500 + 1) + 500;
        randCol();
        setBackground(Color.BLACK);
        for(int i=0;i<50;i++)
        {
            Graphic.fillRect(randStars(size),randStars(size),5,5);
            Graphic.setColor(randCol());
        }
        drawShip(Graphic);
        drawMissile(Graphic);

    if(isInsane)
	{
        drawEnemy(Graphic,70);
        decideColl(30,50);
	}
	else 
	{
		drawEnemy(Graphic,10);
        decideColl(60,100);
	}
	if(gameOver) {
        gameOver(Graphic);


	}
	}


    
	  private void drawShip(Graphics g)
      {
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(ship.getImage(), ship.getX(), ship.getY(), this);
        shipLocX=ship.getX()+50;
        shipLocY=ship.getY();
      }

	private Image enemyImage()
	{

	   Random rand = new Random();
	   //int index=0;
	   if(y1==0)
	       index = rand.nextInt((iconArray.length - 1) + 1);
	   String temp = iconArray[index];
	   ImageIcon ii = new ImageIcon(temp);
	   image = ii.getImage();
	   return image;
	}
	private void drawEnemy(Graphics g, int s)
	{
		if(y1==0)
		{
			Random r = new Random();
			int[] choices = new int[]{200, 400, 600, 800, 1000};//, 1200, 1300, 1400}; for macs res
			int index = r.nextInt((choices.length - 1) + 1);
            x = choices[index];
            enemyPosX=x;
		}

		Graphics2D g2d = (Graphics2D) g;
		//gets highscores;
        getHighscore(g);
		if(y1<800) {
			g2d.drawImage(enemyImage(), x,
					y1 += s, this);
			enemyPosY=y1;
		}

		if(y1>700)
        {
			gameOver=true;
            y1=0;
			//System.out.println(y1);
		}

	}
	public void drawMissile(Graphics g)
    {
        if(missle==true)
        {
           // Graphics2D g2d = (Graphics2D) g;
            g.setColor(Color.RED);
            g.drawLine(getShipLocationX(), shipLocY,mx,my);
            playSound("galagalaser.wav");
        }
        missle=false;
    }
	
	public void setisInsane(boolean x)
	{
	isInsane = x;
	}

	public void getHighscore(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        //Highscore functions
        String a = "Highscore: " + high;
        Font font = new Font("Arial",30,30);
        g.setFont(font);
        g.setColor(Color.white);
        g.drawString(a,1100,50);
    }
    public int getHigh() { return high; }

    public Color randCol()
    {
        int R,G,B=0;
        Random rand = new Random();//making random colors
        R=rand.nextInt(256);	G=rand.nextInt(256);	B=rand.nextInt(256);
        Color rcolor=new Color(R,G,B);
        return rcolor;
    }

    public int randStars(int sze)
    {
        return r.nextInt(sze);
    }
    public void decideColl(int x, int y)
    {
        int upperboundX=enemyPosX+x;
        int lowerboundX=enemyPosX-x;
        int lowerboundY=enemyPosY-y;
        int upperboundY=enemyPosY+y;
        if(mx>=lowerboundX&&mx<=upperboundX) {
            if (my >= lowerboundY && my <= upperboundY)
            {
                high++;
                y1 = 0;
            }

        }

    }
    public void gameOver(Graphics g)
    {
        t.stop();
        setBackground(Color.WHITE);
        Font font = new Font("Papyrus",100,68);
        g.setFont(font);
        g.setColor(Color.RED);
        g.drawString("GAME OVER",550,500);
        gameOver=true;
    }
    public boolean getOver()
    {
        return gameOver;
    }
    public void playSound(String soundName)
    {
        try
        {

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile( ));
            Clip clip = AudioSystem.getClip( );
            clip.open(audioInputStream);
            clip.start( );
        }
        catch(Exception ex)
        {
            System.out.println("Error with playing sound.");
            ex.printStackTrace( );
        }
    }
    /*
    This method returns the x location of the ship that it is currently at
     */
    public int getShipLocationX()
    {
     return shipLocX;
    }
    public void playSoundBack(String soundName)
    {
        try
        {

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile( ));
            Clip clip = AudioSystem.getClip( );
            clip.open(audioInputStream);
            while(true) {
                clip.start();
                clip.loop(clip.LOOP_CONTINUOUSLY);
            }
        }
        catch(Exception ex)
        {
        }
    }

//
}