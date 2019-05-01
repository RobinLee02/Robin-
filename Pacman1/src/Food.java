import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ActionMap;
import javax.swing.Timer;

import java.awt.Graphics;


public class Food extends Object{
	static String closeMouth = "PacmanC.png";
	
	public static String img = closeMouth;

	public Food(int x, int y, int w, int h, String img, ActionMap am) {
		super(x, y, w, h, img);
		
	}

}
