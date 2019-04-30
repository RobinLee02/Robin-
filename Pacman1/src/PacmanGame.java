import java.awt.Graphics;
import java.util.*;

public class PacmanGame {
	
	// what are the things you see in the game?
	List<Object> Pacman = new ArrayList<>();
	
	// list of autos should come from loadLevel
	List<Object> Ghosts = new ArrayList<>();
	
	
	
	Pacman Pacman1;
	
	
	int level=0;
	public PacmanGame() {

		
		
		Pacman1 = new Pacman(40, 140, 50, 50, null);
		Pacman.add(Pacman1);
		
		level++;
		
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
	}



	// What do you want to do when a key is hit?
	public void keyHit(String s) {
		
	}

	public void draw(Graphics g) {

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
