import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ActionMap;
import javax.swing.Timer;

import java.awt.Graphics;

public class Pacman extends PlayerControlledObject {
	
	
	static String closeMouth = "PacmanC.png";
	
	
	public static String img = closeMouth;
	
	
	
	public void moveLeft() {
		this.getRect().translate(-1*(int) this.getRect().getWidth(), 0);
		
		
	}
	public void moveRight() {
		this.getRect().translate(1*(int) this.getRect().getWidth(), 0);
		
	}
	public void moveUp() {
		this.getRect().translate(0, -1*(int) this.getRect().getHeight());
		
	}
	public void moveDown() {
		
		this.getRect().translate(0, 1*(int) this.getRect().getHeight());
		
		
	}
	
	
	public Pacman(int x, int y, int w, int h, String img, ActionMap am) {
		super(x, y, w, h, img, am);
		
	}

}