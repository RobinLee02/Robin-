import java.awt.Image;

import javax.swing.ActionMap;

public class Mario extends PlayerControlledObject {
	
	public int dx;
	public Mario(int x, int y, int w, int h, ActionMap am) {
		super(x, y, w, h, "Mario.png", am);
	}
	
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

}