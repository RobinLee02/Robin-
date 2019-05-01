import java.awt.Color;
import java.awt.Graphics;
import java.util.*;

public class PacmanGame {
	
	// what are the things you see in the game?
	List<Object> Pacman = new ArrayList<>();
	
	// list of autos should come from loadLevel
	List<Object> Ghosts = new ArrayList<>();
	
	List<Object> Foods = new ArrayList<>();
	
	
	
	Pacman Pacman1;
	Food Food1;
	
	static String closeMouth = "PacmanC.png";

	static String openMouth = "PacmanO.png";
	
	public static String img = closeMouth;
	
	
	
	
	int level=0;
	
	public PacmanGame() {

		
		
		Pacman1 = new Pacman(500, 500, 30, 30,img, null);
		Pacman.add(Pacman1);
		
		Food1 = new Food(400, 400, 30, 30, img, null);
		Pacman.add(Food1);
		
		level++;
		
		loadLevel();
	}
	private void makeMap(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(20, 20, 960, 570);
		
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
	}



	// What do you want to do when a key is hit?
	public void keyHit(String s) {
		img = openMouth;
		
	}

	public void draw(Graphics g) {
		makeMap(g);
		for(Object go:Pacman) {
			go.draw(g);
			
			for (int i = 0; i < Pacman.size(); i++) {
				if ((Pacman.get(i))!= Pacman1 && (Pacman.get(i)).hit(Pacman1) == true ) {
					System.out.println("barack mobamba ");
					
				}
			}
			
		}
		
		
		
	}
	
	
}
