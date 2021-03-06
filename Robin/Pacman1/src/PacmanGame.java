import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.*;

import javax.imageio.ImageIO;

import java.awt.Image;

public class PacmanGame {
	
	// what are the things you see in the game?
	List<Object> Pacman = new ArrayList<>();
	
	// list of autos should come from loadLevel
	List<Object> Obstacles = new ArrayList<>();
	
	List<Object> Foods = new ArrayList<>();
	
	
	public final static String PATH_PREFIX = "res/images/";
	Pacman Pacman1;
	Food Food1;
	Obstacles Obstacles1;
	
	static String closeMouth = "images.png";

	static String openMouth = "images.png";
	
	static String pacmanFood = "images.png";
	
	public static String img = closeMouth;
	
	
	
	int level=0;
	
	public PacmanGame() {

		Obstacles1 = new Obstacles(0,0,0,0,null);
		
		Pacman1 = new Pacman(30, 295, 30, 30,img, null);
		Pacman.add(Pacman1);
		
		//Food1 = new Food(400, 400, 15, 15, pacmanFood, null);
		//Foods.add(Food1);
		
		
		loadLevel();
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
		//Foods.add(Food1);
		Obstacles.add(Obstacles1);
	}



	// What do you want to do when a key is hit?
	public void keyHit(String s) {
		img = openMouth;
		
	}

	public void draw(Graphics g) {
		g.drawString(String.valueOf(level), 50, 50);
		for(Object go:Pacman) {
			go.draw(g);
		}
		for(Object go:Foods) {
			go.draw(g);
			
		}
		Obstacles1.draw(g);
		
		for (int i = 0; i < Foods.size(); i++) {
			if (Pacman1.hit(Obstacles1) == true ) {
				System.out.println("barack mobamba ");
				((Food) Foods.get(i)).remove();
			}
		}
		
		
		
	}
	
	
}
