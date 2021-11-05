package game;

import java.awt.Graphics;

public abstract class GameObject {

	protected float locX, locY;
	protected float maxSpeed;
	protected Sprite sprite;
	protected ID myID;
	protected Rectangle hitBox;
	
	public GameObject(String ref, float x, float y, float velocity) {
		locX = x;
		locY = y;
		maxSpeed = velocity;
		if(ref!=null)
			sprite = SpriteStore.get().getSprite(ref);
	}
	
	public float getX() {
		return locX;
	}

	public void setX(float locX) {
		this.locX = locX;
	}

	public float getY() {
		return locY;
	}

	public void setY(float locY) {
		this.locY = locY;
	}
	
	public float getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(float maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract boolean intersects(GameObject obj);
	public abstract void collidesWith(GameObject obj);
}
