import java.awt.Image;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;

public class PlayerControlledObject extends Object {
	
	ActionMap map ;
	public PlayerControlledObject(int x, int y, int w, int h, String str, ActionMap am) {
		super(x, y, w, h, str);
		// TODO Auto-generated constructor stub
	}
	
	

	public void putAction(Object command, AbstractAction act) {
		map.put(command, act);
	}
}
