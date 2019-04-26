import java.awt.Graphics;
import java.util.*;

public class MarioGame {
	
	// what are the things you see in the game?
	List<Obstacle> Mario = new ArrayList<>();
	
	// list of autos should come from loadLevel
	List<Obstacle> Shrooms = new ArrayList<>();
	
	// list of logs
	List<Obstacle> Turtles = new ArrayList<>();
	
	
	Mario Mario1;
	Mushroom Mush1;
	
	
	int level=0;
	public MarioGame() {

		
		
		Mario1 = new Mario(40, 140, 80, 100, null);
		Mario.add(Mario1);
		
		Mush1 = new Mushroom(40, 80, 100, 100, null);
		level++;
		
		loadLevel();
	}
	
	
	
	private void loadLevel() {
		// this is just an idea.  Maybe store the different levels as text files
		List<List<Obstacle>> levelObjects = LevelReader.readInLevel(level);
		Mario.clear();
		if(levelObjects != null) {
			for(List<Obstacle> list: levelObjects) {
				if(list != null) {
					for(Obstacle go: list) {
//						if(go instanceof Log)
//							logs.add(go);
//						if(go instanceof Auto)
//							autos.add(go);
						Mario.add(go);
					}
				}
			}
		}
		Mario.add(Mario1);
	}



	// What do you want to do when a key is hit?
	public void keyHit(String s) {
		System.out.println("In frogger game (keyHit): "+s);
		
	}

	public void draw(Graphics g) {

		for(Obstacle go:Mario) {
			go.draw(g);
			
			for (int i = 0; i < Mario.size(); i++) {
				if ((Mario.get(i))!= Mario1 && (Mario.get(i)).hit(Mario1) == true ) {
					System.out.println("barack mobamba ");
					
				}
			}
			
		}
		
		
		
	}
	
	
}
