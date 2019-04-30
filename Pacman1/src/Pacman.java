import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ActionMap;
import javax.swing.Timer;

public class Pacman extends PlayerControlledObject {
	
	
	static String closeMouth = "PacmanO.png";
	static String openMouth = "PacmanO.png";
	static String img = closeMouth;
	
	
	public boolean mouth = false;
	
	
	
	public int dx;
	public Pacman(int x, int y, int w, int h, ActionMap am) {
		super(x, y, w, h, img, am);
		
	}
	
	
	
	public void moveMouth(){
		if (mouth) {
			img = openMouth;
		} else {
			img = closeMouth;
		}
	}
	
	
	
	public void moveLeft() {
		this.getRect().translate(-1*(int) this.getRect().getWidth(), 0);
		moveMouth();
		
	}
	public void moveRight() {
		this.getRect().translate(1*(int) this.getRect().getWidth(), 0);
		moveMouth();
	}
	public void moveUp() {
		this.getRect().translate(0, -1*(int) this.getRect().getHeight());
		moveMouth();
	}
	public void moveDown() {
		this.getRect().translate(0, 1*(int) this.getRect().getHeight());
		moveMouth();
	}

}