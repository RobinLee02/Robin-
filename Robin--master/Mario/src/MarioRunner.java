import java.awt.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Map;

import javax.swing.*;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;



public class MarioRunner extends JFrame {
	private JPanel panel;
	private MarioGame game = new MarioGame();
	private Timer timer;
	private int ticks;
	
	// Notice this intuitive method for finding the screen size 
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int WIDTH = (int) (screenSize.getWidth()*3/4),HEIGHT=(int) (screenSize.getHeight()*3/4);
	private static final int REFRESH_RATE = 10;
	
	int time = 0;
	
       
 
 
	public MarioRunner() {
		super(" Terrain");
        setSize(550, 250);
 
        MovingTerrain back = new MovingTerrain();
        ((Component)back).setFocusable(true);
        getContentPane().add(back);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
   
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					start();
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
				}
			}
		});
			
	}

	public static void main(String[] args) {
		new MarioRunner();
	}

	private void start() {
		JFrame frame = new JFrame("Frogger");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				drawGame(g);
			}
		};
		// random color to the background
		panel.setBackground(new Color(255,255,255));
		
		// so that the frame isn't minimized
		panel.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		
		// so that the frame is placed a little way from top and left side
		frame.setLocation(WIDTH/10, HEIGHT/10);

		// map the keystrokes that the panel detects to the game
		mapKeyStrokesToActions(panel);

		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		
		// after setting visible to true, you can get focus.  You need focus to consume
		// the keystrokes hit by the user
		panel.requestFocusInWindow();
		
		// this timer controls the actions in the game and then repaints after each update to data
		timer = new Timer(REFRESH_RATE, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				updateGame();
				panel.repaint();
			}
		});
		timer.start();
	}

	// this method is called every time the timer goes off (which right now is every 10 milliseconds = 100 times per second
	protected void updateGame() {
		ticks++;// keeps track of the number of times the timer has gone off
		
		int hurts = 1000/REFRESH_RATE;
		time = ticks/hurts;
		if(ticks %hurts == 0) {
			System.out.println(time);
		}
		
		
	}

	private void mapKeyStrokesToActions(JPanel panel) {

		// A map is an Data storage interface which defines
		// an association of a key with a value
		// to "add" to a map you use the "put" method
		// to "get" from a map you use "get(key)" and the 
		// value associated with the key is returned (or null)
		ActionMap map = panel.getActionMap();
		InputMap inMap = panel.getInputMap();

		// code below associates pressing the up arrow with the command "up"
		// essentially creating the command "up" being broadcast any time the 
		// up key is hit
		inMap.put(KeyStroke.getKeyStroke("pressed UP"), "up");
		inMap.put(KeyStroke.getKeyStroke("pressed W"), "up");
		// code below associates the "up" action with anything in the 
		// actionPerformed method.  Right now, it just prints something
		map.put("up", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hit("up");
				 
				game.Mario1.moveUp();
				
			}
		});
		panel.getInputMap().put(KeyStroke.getKeyStroke("LEFT"),"left");
		panel.getActionMap().put("left",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				hit("left");
				game.Mario1.moveLeft();
			}
		});
		
		panel.getInputMap().put(KeyStroke.getKeyStroke("DOWN"),"down");
		panel.getActionMap().put("down",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				hit("down");
				//crouch
			}
		});
		
		panel.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"),"right");
		panel.getActionMap().put("right",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				hit("right");
				game.Mario1.moveRight();
			}
		});

	}
	public  void hit(String s) {
		game.keyHit(s);
		panel.repaint();
	}
	protected void drawGame(Graphics g) {
		game.draw(g);
		Toolkit.getDefaultToolkit().sync();
	}

}
