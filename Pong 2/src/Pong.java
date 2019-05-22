import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.*;


public class Pong extends JPanel implements KeyListener, ActionListener {

    private int height, width;
    private int time = 0;
    
    private Timer t = new Timer(5, this);
    
    private boolean start;

    private HashSet<String> Arrows = new HashSet<String>();


    
    private static int paddleSPEED = 1;
    private int padHeight = 5, padWidth = 40;
    
    private int bottomPadX, topPadX;
    private int distanceFromEnd = 70;

    
    private double ballX, ballY, velX = 1.05, velY = 1.05, ballSize = 20;

    
    
    
    private int  scoreAI, scoreHuman;

    public Pong() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        start = true;
        t.setInitialDelay(100);
        t.start();
    }
    
    
    
    
    
    public Color ballColor = Color.white;
    
    
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, width, height/2);
        //make look better
        Graphics2D g1 = (Graphics2D) g;
        
        height = getHeight();
        width = getWidth();
        
        
        
        // initial positioning
        if (start) {
            bottomPadX = width / 2 - padWidth / 2;
            topPadX = bottomPadX;
            ballX = width / 2 - ballSize / 2;
            ballY = height / 2 - ballSize / 2;
            start = false;
        }
        // bottom pad
        Rectangle2D bottomPad = new Rectangle(bottomPadX, height - padHeight - distanceFromEnd, padWidth, padHeight);
        g1.setPaint(Color.BLACK);
        g1.fill(bottomPad);
        

        // top pad
        Rectangle2D topPad = new Rectangle(topPadX, distanceFromEnd, padWidth, padHeight);
        g1.setPaint(Color.WHITE);
        g1.fill(topPad);

        // ball
        Ellipse2D ball = new Ellipse2D.Double(ballX, ballY, ballSize, ballSize);
        g1.setPaint(ballColor);
        g1.fill(ball);
        
        g1.setPaint(Color.BLACK);
        String score = "You missed:    " + new Integer(scoreAI);
        g1.drawString(score, 240, 560);
        
        
        
        g1.setPaint(Color.WHITE);
        String time1 = "Computer missed: " + new Integer(scoreHuman);
        g1.drawString(time1, 240, 20);
        
        
        
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
        if (ballX < 0 || ballX > width - ballSize) {
            velX = -velX;
        }
        // reVERSE direCtion
        if (ballY < 0) {
            velY = -velY;
            scoreHuman++;
        }

        if (ballY + ballSize > height) {
            velY = -velY;
            scoreAI++;
   		 	System.out.println("You failed:" + scoreAI + " times!");
        }
        
        if (ballY + ballSize >= height - padHeight - distanceFromEnd && velY > 0) {
        	 if (ballX + ballSize >= bottomPadX && ballX <= bottomPadX + padWidth) {
        		 velY = -velY;
        		 
        	 }
                
        }
           

        
        if (ballY <= padHeight + distanceFromEnd && velY < 0) {
        	if (ballX + ballSize >= topPadX && ballX <= topPadX + padWidth) {
        		 velY = -velY;
        	}
               
        }
        
        
            

        ballX += velX;
        ballY += velY;

      
        if (Arrows.contains("LEFT")) {
        	if (bottomPadX > 0) {
        		bottomPadX -= paddleSPEED;
        	} else {
        		bottomPadX -= 0;
        	}
        }
        else if (Arrows.contains("RIGHT")) {
        	if (bottomPadX < width - padWidth) {
        		bottomPadX += paddleSPEED;
        	} else {
        		bottomPadX += 0;
        	}
        }
        

        double ballLOC = ballX - topPadX;
        
        if (ballLOC > 0) {
        	if (topPadX < width - padWidth) {
        		topPadX += paddleSPEED;
        	} else {
        		topPadX +=0;
        	}
        }
        else if (ballLOC < 0) {
        	if (topPadX > 0) {
        		topPadX -= paddleSPEED;
        	} else {
        		topPadX -=0;
        	}
        }
        
        if (ballY < 300) {
        	ballColor = Color.white;
        } else {
        	ballColor = Color.black;
        }
        
        
        
        
        time++;
        if (time % 200 == 0) {
        	//System.out.println(time/200);
        }
        
        if (((time/200)+1)%50==0 && scoreAI == scoreAI) {
        	System.out.println("INCREASE");
        	
        }
       
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                Arrows.add("LEFT");
                break;
            case KeyEvent.VK_RIGHT:
                Arrows.add("RIGHT");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                Arrows.remove("LEFT");
                break;
            case KeyEvent.VK_RIGHT:
                Arrows.remove("RIGHT");
                break;
        }
    }
}