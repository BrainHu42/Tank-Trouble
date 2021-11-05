package game;

import java.awt.Graphics;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class Bullet extends GameObject{

	private float timeFired;
	private Tank tank;
	private float[] vector;
	
	public Tank getTank() {return tank;}
	
	public Bullet(String ref, Tank t, float timeFired, float x, float y, float velocity, float[] vec) {
		super(ref, x, y, velocity);
		myID = ID.Bullet;
		tank = t;
		this.timeFired = timeFired;
		vector = vec;
		vector[0] *= velocity;
		vector[1] *= velocity;
		sprite.resizeTo(Game.scaleFactor/5, Game.scaleFactor/5);
		locX -= sprite.getWidth()/2;
		locY -= sprite.getHeight()/2;
		hitBox = new Rectangle(locX,locY,locX+sprite.getWidth(),locY,locX+sprite.getWidth(),locY+sprite.getHeight(),locX,locY+sprite.getHeight());
	}

	@Override
	public void tick() {
		if((System.nanoTime()-timeFired)/100>50000000){
			Game.toRemove.add(this);
			return;
		}
		locX += vector[0];
		locY += vector[1];
		hitBox.translate(vector[0], vector[1]);
		
		Square sqr;
		Line2D.Float line;
		for(int rr=0; rr<Maze.maze.length; rr++)
			for(int cc=0; cc<Maze.maze[0].length; cc++) {
				sqr = Maze.maze[rr][cc];
				for(int r=0; r<2; r++)
					for(int c=0; c<2; c++) {
						line = sqr.getSquare()[r][c];
/*						if(line!=null && (hitBox.contains(line.getP1()) || hitBox.contains(line.getP2()))) {
							System.out.println("test");
							if(line.x1==line.x2)
								vector[1] = -vector[1];
							if(line.y1==line.y2)
								vector[0] = -vector[0];
							return;
						}
						
						else */if(line!=null && hitBox.contains(line)) {
							ArrayList<Line2D.Float> intersection = hitBox.getIntersection();
//							if(hitBox.getIntersect()==hitBox.getL1())
//							System.out.println("L1");
//						if(hitBox.getIntersect()==hitBox.getL2())
//							System.out.println("L2");
//						if(hitBox.getIntersect()==hitBox.getL3())
//							System.out.println("L3");
//						if(hitBox.getIntersect()==hitBox.getL4())
//							System.out.println("L4");
//							for(Line2D.Float l : hitBox.getIntersection())
//							{
//								if(l==hitBox.getL1())
//									System.out.print("L1 ");
//								if(l==hitBox.getL2())
//									System.out.print("L2 ");
//								if(l==hitBox.getL3())
//									System.out.print("L3 ");
//								if(l==hitBox.getL4())
//									System.out.print("L4 ");
//							}
//							System.out.println();
//						if(hitBox.contains(line.getP1()) || hitBox.contains(line.getP2())) {
//							if((r==0 && c==0) || (r==1 && c==1) && vector[0]>0 && Maze.maze[rr][cc-1].getSquare()[])
//								vector[0] = -vector[0];
//							else if((r==0 && c==1) || (r==1 && c==0) && ((Maze.maze[rr][cc-1].getSquare()[0][0]==null && Maze.maze[rr][cc+1].getSquare()[0][0]==null)
//									|| ((Maze.maze[rr][cc-1].getSquare()[1][1]==null && Maze.maze[rr][cc+1].getSquare()[1][1]==null))))
//								vector[1] = -vector[1];
//							System.out.println("test");
//						}
						if(intersection.size()==1 && (hitBox.contains(line.getP1()) || hitBox.contains(line.getP2()))) {
							System.out.println(vector[0]+" , "+vector[1]);
								if(r==0 && c==0 && ((vector[0]>0 && Maze.maze[rr][cc+1].getSquare()[0][0]==null) || (vector[0]<0 && Maze.maze[rr][cc-1].getSquare()[0][0]==null)))
									vector[0] = -vector[0];
								else if(r==1 && c==1 && ((vector[0]>0 && Maze.maze[rr][cc+1].getSquare()[1][1]==null) || (vector[0]<0 && Maze.maze[rr][cc-1].getSquare()[1][1]==null))) {
									vector[0] = -vector[0];
									
								}
								else if(r==0 && c==1 && ((vector[1]>0 && Maze.maze[rr+1][cc].getSquare()[0][1]==null) || (vector[1]<0 && Maze.maze[rr-1][cc].getSquare()[0][1]==null)))
									vector[1] = -vector[1];
								else if(r==1 && c==0 && ((vector[1]>0 && Maze.maze[rr+1][cc].getSquare()[1][0]==null) || (vector[1]<0 && Maze.maze[rr-1][cc].getSquare()[1][0]==null)))
									vector[1] = -vector[1];
						}
//						else if((intersection.contains(hitBox.getL1()) && intersection.contains(hitBox.getL2())) || (intersection.contains(hitBox.getL2()) && intersection.contains(hitBox.getL3()))
//							|| intersection.contains(hitBox.getL3()) && intersection.contains(hitBox.getL4()) || (intersection.contains(hitBox.getL4()) && intersection.contains(hitBox.getL1()))) {
//							vector[0] = -vector[0];
//							vector[1] = -vector[1];
//						}
						else {
							if((r==0 && c==0) || (r==1 && c==1))
								vector[1] = -vector[1];
							else if((r==0 && c==1) || (r==1 && c==0))
								vector[0] = -vector[0];
						}
				//			System.out.println(line.getX1()+" , "+line.getY1());
//						
//							if(line.x1==line.x2 && hitBox.getIntersectPoints()==1)
//								vector[1] = -vector[1];
//							else if(line.y1==line.y2 && hitBox.getIntersectPoints()==1)
//								vector[0] = -vector[0];
//							if(line.y1==line.y2 && hitBox.getX2()==hitBox.getX3())
//								vector[0] = -vector[0];
//							else if(line.x1==line.x2 && hitBox.getX2()==hitBox.getX3())
//								vector[0] = -vector[0];
//							
//							if(line.x1==line.x2 && hitBox.getY3()==hitBox.getY4())
//								vector[1] = -vector[1];
//							else if(line.y1==line.y2 && hitBox.getY1()==hitBox.getY2())
//								vector[1] = -vector[1];
//							if(((r==0 && c==0) || (r==1 && c==1))) {
//								if(line.y1-line.y2==hitBox.getY1()-hitBox.getY2())	
//									vector[1] = -vector[1];
//								else
//									vector[0] = -vector[0];
//							}
//							else if(((r==0 && c==1) || (r==1 && c==0))) {
//								if(line.x1-line.x2==hitBox.getY1()-hitBox.getY2())	
//									vector[0] = -vector[0];
//								else {
//									vector[1] = -vector[1];
//									System.out.println("test");
//								}
//							}
//							else if(line.y1-line.y2==0 && hitBox.getX2()-hitBox.getX3()==0) {
//								vector[0] = -vector[0];
//								System.out.println("test");
//							}
//							else if(line.x1-line.x2==0 && hitBox.getY1()-hitBox.getY2()==0)
//								vector[1] = -vector[1];
							return;
						}
					}
			}
	}

	@Override
	public void render(Graphics g) {
		hitBox.draw(g);
		g.drawImage(sprite.getImage(), (int)locX, (int)locY, sprite.getWidth(), sprite.getHeight(), null);
	}

	@Override
	public boolean intersects(GameObject obj) {
		if(hitBox.contains(obj.hitBox))
			return true;
		return false;
	}

	@Override
	public void collidesWith(GameObject obj) {
		if(obj.myID==ID.Tank && obj!=this.tank)
			Game.toRemove.add(this);
	}
}
