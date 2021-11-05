package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

public class Tank extends GameObject{

	private double theta, dTheta, oldTheta;
	private float fireInterval;
	private long lastFire, timeDead;
	private Player user;
	private int shotsFired;
	private boolean dead;
	private float prevX, prevY, prevX1, prevY1, prevX2, prevY2, prevX3, prevY3, prevX4, prevY4;
	
	public long getTimeDead() {return timeDead;}
	public boolean isDead() {return dead;}
	public Player getUser() {return user;}
	
	public Tank(String ref, Player p, float x, float y, float vel, float interval) {
		super(ref, x, y, vel);
		myID = ID.Tank;
		user = p;
		fireInterval = interval;
		sprite.resizeTo((int)(2.0/3.0*Game.scaleFactor), (int)(14.0/30.0*Game.scaleFactor));
		locX -= sprite.getWidth()/2;
		locY -= sprite.getHeight()/2;
		hitBox = new Rectangle(locX-Game.scaleFactor/27,locY,locX+sprite.getWidth()+Game.scaleFactor/18,locY,locX+sprite.getWidth()+Game.scaleFactor/18,locY+sprite.getHeight(),locX-Game.scaleFactor/27,locY+sprite.getHeight());
	}

	@Override
	public void tick() {
/*Mouse Capabilities
		if(Window.mousePoint!=null)
		{
			double deltaX, deltaY;
			deltaX = Window.mousePoint.getX()-this.getX();
			deltaY = Window.mousePoint.getY()-this.getY();
			locX += (int)(deltaX*velX)/(Math.sqrt(deltaX*deltaX+deltaY*deltaY));
			locY += (int)(deltaY*velY)/(Math.sqrt(deltaX*deltaX+deltaY*deltaY));
		}*/
		
		double dX=maxSpeed*Math.cos(Math.toRadians(theta)), dY=maxSpeed*Math.sin(Math.toRadians(theta));
		prevX = locX; prevY= locY; prevX1=hitBox.getX1(); prevY1=hitBox.getY1(); prevX2=hitBox.getX2(); prevY2=hitBox.getY2(); prevX3=hitBox.getX3(); prevY3=hitBox.getY3(); prevX4=hitBox.getX4(); prevY4=hitBox.getY4();
		oldTheta = theta;
		dTheta=0;
		
		if(user==Game.player1)
		{
			if(Window.leftPressed) {
				theta = (theta-6)%360;
				dTheta=-6;
			}
			if(Window.rightPressed) {
				theta = (theta+6)%360;
				dTheta=6;
			}
			if(Window.upPressed) {
				locX += dX;
				locY += dY;
				hitBox.translate((float)dX, (float)dY);
			}
			if(Window.downPressed) {
				locX -= dX;
				locY -= dY;
				hitBox.translate((float)-dX, (float)-dY);
			}
		}
		else if(user==Game.player2)
		{
			if(Window.sPressed) {
				theta = (theta-6)%360;
				dTheta=-6;
			}
			if(Window.fPressed) {
				theta = (theta+6)%360;
				dTheta=6;
			}
			if(Window.ePressed) {
				locX += dX;
				locY += dY;
				hitBox.translate((float)dX,(float)dY);
			}
			if(Window.dPressed) {
				locX -= dX;
				locY -= dY;
				hitBox.translate((float)-dX, (float)-dY);
			}
		}
		if((theta>178 && theta<182) || (theta<-178 && theta>-182)) {
			hitBox = new Rectangle(locX+sprite.getWidth()+Game.scaleFactor/18,locY+sprite.getHeight(),locX-Game.scaleFactor/27,locY+sprite.getHeight(),locX-Game.scaleFactor/27,locY,locX+sprite.getWidth()+Game.scaleFactor/18,locY);
		}
		else if(theta<2 && theta>-2) {
			hitBox = new Rectangle(locX-Game.scaleFactor/27,locY,locX+sprite.getWidth()+Game.scaleFactor/18,locY,locX+sprite.getWidth()+Game.scaleFactor/18,locY+sprite.getHeight(),locX-Game.scaleFactor/27,locY+sprite.getHeight());
		}
		else
			hitBox.rotate(Math.toRadians(dTheta));
		
		for(GameObject obj : Handler.objects) {
			if(obj!=this && obj.intersects(this)) {
				this.collidesWith(obj);
				obj.collidesWith(this);
				return;
			}
		}
		
		for(Square[] s : Maze.maze)
			for(Square sqr : s)
				for(Line2D.Float[] arry : sqr.getSquare())
					for(Line2D.Float line : arry)
						if(line!=null && hitBox.contains(line)) {
							theta = oldTheta;
							locX = prevX;
							locY = prevY;
							hitBox.setX1(prevX1);
							hitBox.setY1(prevY1);
							hitBox.setX2(prevX2);
							hitBox.setY2(prevY2);
							hitBox.setX3(prevX3);
							hitBox.setY3(prevY3);
							hitBox.setX4(prevX4);
							hitBox.setY4(prevY4);
							hitBox.setCenterX((prevX1+prevX3)/2);
							hitBox.setCenterY((prevY2+prevY4)/2);
							return;
						}
	}
	@Override
	public void render(Graphics g) {
	//	System.out.println(hitBox.getCenter().getX()+" , "+hitBox.getCenter().getY());
		if(dead) {
			sprite.resizeTo((int)(4.0/5.0*Game.scaleFactor), (int)(2.0/3.0*Game.scaleFactor));
			g.drawImage(sprite.getImage(),(int)locX,(int)locY-sprite.getHeight()/5, sprite.getWidth(), sprite.getHeight(), null);
		}
		else {
			Graphics2D g2D = (Graphics2D) g.create();
			AffineTransform at = AffineTransform.getTranslateInstance(locX, locY);
			at.rotate(Math.toRadians(theta),hitBox.getCenterX()-locX,hitBox.getCenterY()-locY);
			g2D.drawImage(sprite.getImage(), at, null);
			hitBox.draw(g);
			g2D.dispose();
		}
	}
	
	public void tryFire() {
		if((System.currentTimeMillis()-lastFire<fireInterval) || shotsFired>4)
			return;
		lastFire = System.currentTimeMillis();
		if(user==Game.player1)
			Game.handler.addObject(new Bullet("BlueBullet.png",this,System.nanoTime(),(hitBox.getX2()+hitBox.getX3())/2, (hitBox.getY2()+hitBox.getY3())/2,7, new float[] {(float)Math.cos(Math.toRadians(theta)),(float)Math.sin(Math.toRadians(theta))}));
		if(user==Game.player2)
			Game.handler.addObject(new Bullet("RedBullet.png",this,System.nanoTime(),(hitBox.getX2()+hitBox.getX3())/2, (hitBox.getY2()+hitBox.getY3())/2,7, new float[] {(float)Math.cos(Math.toRadians(theta)),(float)Math.sin(Math.toRadians(theta))}));
		shotsFired++;
	}

	@Override
	public boolean intersects(GameObject obj) {
			if((hitBox).contains(obj.hitBox))
					return true;
		return false;
	}

	@Override
	public void collidesWith(GameObject obj) {
		if(obj.myID==ID.Bullet && this!=((Bullet)obj).getTank()) {
			sprite = SpriteStore.get().getSprite("Explosion.png");
			dead = true;
			timeDead = System.currentTimeMillis();
			Game.toRemove.add(this);
		}
		if(obj.myID==ID.Tank) {
			theta = oldTheta;
			locX = prevX;
			locY = prevY;
			hitBox.setX1(prevX1);
			hitBox.setY1(prevY1);
			hitBox.setX2(prevX2);
			hitBox.setY2(prevY2);
			hitBox.setX3(prevX3);
			hitBox.setY3(prevY3);
			hitBox.setX4(prevX4);
			hitBox.setY4(prevY4);
		}
	}
	
}
