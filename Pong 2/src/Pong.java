import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import javax.swing.*;


public class Pong extends JPanel implements KeyListener, ActionListener {

    private int height, width;
    private int time = 0;
    
    private Timer t = new Timer(5, this);
    
    private boolean start;

    
    //only need for keys
    private HashSet<String> Arrows = new HashSet<String>();


    
    private static double paddleSPEED = 2;
    private int padHeight = 5, padWidth = 40;
    
    private int bottomPadX, topPadX;
    private int distanceFromEnd = 30;

    
    private double ballX, ballY, velX = 1.1, velY = 1.8, ballSize = 40;
    
    //how much u can miss before losing
    private int limit = 5;
    
    
    private int  scoreAI, scoreHuman;
    int num = (int)Math.random() * 3 + 1;
    
    public Pong() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        
        startGame();
        
    }
    
    
    
    
    
    public Color ballColor = Color.white;
    
    boolean alive = true;
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, width, height/2);
        g.setColor(Color.white);
        g.fillRect(0, height/2, width, height/2);
        //make look better
        Graphics2D g1 = (Graphics2D) g;
        
        height = getHeight();
        width = getWidth();
        
        
        
        // initial positioning
        if (start) {
        	
        	ballX = 150;
        	ballY = 150;
        	
        	velX = new Random().nextInt(1) -1;
        	velY = 1.5;
        	
            bottomPadX = width / 2 - padWidth / 2;
            topPadX = bottomPadX;
            
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
        
        
        g1.setFont(new Font("default", Font.BOLD, 16));
        
        g1.setPaint(Color.BLACK);
        String score = "MISSED:    " + new Integer(scoreAI);
        g1.drawString(score, 0, 560);
        
        
        
        g1.setPaint(Color.WHITE);
        String time1 = "TIME    ALIVE:    " + new Integer(time/200);
        g1.drawString(time1, 0, 20);
        
        
        
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
        if (ballX < 0 || ballX > width - ballSize) {
            velX = -velX;
        }
        
        
        if (ballY < 0) {
            velY = -velY;
        }

        if (ballY + ballSize > height) {
            velY = -velY;
        }
        
        
        
        
        
        
        //SCORING
        if (ballY < 0) {
            scoreHuman++;
           // return;
        }

        if (ballY + ballSize > height) {
            scoreAI++;
   		 	System.out.println("You failed:" + scoreAI + " times!");
   		 	//return;
        }
        
        
        
        
        
        //TO OTHER DIRECT
        
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
        
        
            
        //MOVE BALL
        ballX += velX;
        ballY += velY;

        //MOVE PADDLE
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
        
        //BOUNCE BALL
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
        
        //switching colors
        if (ballY < 300) {
        	ballColor = Color.white;
        } else {
        	ballColor = Color.black;
        }
        
        
        
        
        time++;
        
       challenges();
       if (scoreAI > limit){
    	   endGame();
    	   alive = false;
    	   
       }
       
       if (alive == true){
    	   repaint();
       }
       
   
       
    }
    
    public void challenges(){
    	if (time%1000==0) {
    		this.ballSize -=1;
    		this.padWidth -=1;
    		
    		if (padWidth < 5 || ballSize < 5){
    			endGame();
    		}
    		if (velY > 0){
    			this.velY +=1;
    			
    		} else {
    			this.velY -= 1;
    		}
    		
    	}
    	
    	
    	if (time%2000 == 0) {

    		if (velY > 0){
    			this.velY =1.8;
    		} else {
    			this.velY = -1.8;
    		}
			//this.ballY = 1;
    	}
    	
    }
    public void startGame(){
    	
    	start = true;
    	t.setInitialDelay(50);
    	t.start();


    }

    public void endGame(){
    	//System.out.println("ended");
    	ballY = 0;
    	ballX = 0;
    	this.paddleSPEED = 0;
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