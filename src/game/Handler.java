package game;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

	public static LinkedList<GameObject> objects = new LinkedList<GameObject>();
	
	public void tick() {
		for(GameObject obj : objects)
			obj.tick();
	}
	
	public void render(Graphics g) {
		for(GameObject obj : objects)
			obj.render(g);
	}
	
	public void addObject(GameObject obj) {
		objects.add(obj);
	}
	
	public void removeObject(GameObject obj) {
		objects.remove(obj);
	}
}
