import java.awt.Color;
import java.awt.Graphics;

public class Obstacles extends Object{

	public Obstacles(int x, int y, int w, int h, String str) {
		super(x, y, w, h, str);
		// TODO Auto-generated constructor stub
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		
		for (int i = 10; i<600;i+=120) {
			g.fillRect(100, i, 30, 50);
		}
	}
}
