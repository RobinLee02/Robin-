import java.awt.Color;
import java.awt.Graphics;
import java.util.*;

import javax.imageio.ImageIO;

import java.awt.Image;

public class PacmanGame {
	
	// what are the things you see in the game?
	List<Object> Pacman = new ArrayList<>();
	
	// list of autos should come from loadLevel
	List<Object> Ghosts = new ArrayList<>();
	
	List<Object> Foods = new ArrayList<>();
	
	
	public final static String PATH_PREFIX = "res/images/";
	Pacman Pacman1;
	Food Food1;
	
	static String closeMouth = "images.png";

	static String openMouth = "images.png";
	
	static String pacmanFood = "images.png";
	
	public static String img = closeMouth;
	
	private Image background = getImage("map.png");
	
	
	int level=0;
	
	public PacmanGame() {

		
		
		Pacman1 = new Pacman(500, 500, 34, 30,img, null);
		Pacman.add(Pacman1);
		
		Food1 = new Food(400, 400, 15, 15, pacmanFood, null);
		Foods.add(Food1);
		
		level++;
		
		loadLevel();
	}
	
	
	
	protected  Image getImage(String fn) {
		Image img = null;
        fn = PATH_PREFIX+fn;
        try {

            img = ImageIO.read(this.getClass().getResource(fn));
           

        } catch (Exception e) {
            
        }
        return img;
	}
	
	
	
	
	private void makeMap(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(10, 10, 980, 590);
		g.setColor(Color.BLUE);
		
		//for (int i = 10; i<600;i+=120) {
			//g.fillRect(100, i, 30, 50);
		//}
		g.drawImage(background, 0, -220, 1000,1000, null);
		//g.fillRect(100, 100, 200, 30);
		
		
		//g.fillRect(100, 400, 30, 200);
	}
	private void loadLevel() {
		// this is just an idea.  Maybe store the different levels as text files
		List<List<Object>> levelObjects = LevelReader.readInLevel(level);
		Pacman.clear();
		if(levelObjects != null) {
			for(List<Object> list: levelObjects) {
				if(list != null) {
					for(Object go: list) {
//						if(go instanceof Log)
//							logs.add(go);
//						if(go instanceof Auto)
//							autos.add(go);
						Pacman.add(go);
					}
				}
			}
		}
		Pacman.add(Pacman1);
		Foods.add(Food1);
	}



	// What do you want to do when a key is hit?
	public void keyHit(String s) {
		img = openMouth;
		
	}

	public void draw(Graphics g) {
		makeMap(g);
		for(Object go:Pacman) {
			go.draw(g);
		}
		for(Object go:Foods) {
			go.draw(g);
			
		}
		
		for (int i = 0; i < Foods.size(); i++) {
			if (Pacman1.hit(Foods.get(i)) == true ) {
				//System.out.println("barack mobamba ");
				((Food) Foods.get(i)).remove();
			}
		}
		
		
		
	}
	
	
}
