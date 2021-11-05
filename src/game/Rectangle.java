package game;

import java.awt.Graphics;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Rectangle {
	private float cx, cy;
	private float x1, y1, x2, y2, x3, y3, x4, y4;
	private Line2D.Float L1,L2,L3,L4;
	private ArrayList<Line2D.Float> intersection = new ArrayList<Line2D.Float>();
	
	public Rectangle(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4)
	{
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.x3 = x3;
		this.y3 = y3;
		this.x4 = x4;
		this.y4 = y4;
		cx = (x1+x3)/2;
		cy = (y2+y4)/2;
		L1 = new Line2D.Float(x1,y1,x2,y2);
		L2 = new Line2D.Float(x2,y2,x3,y3);
		L3 = new Line2D.Float(x3,y3,x4,y4);
		L4 = new Line2D.Float(x4,y4,x1,y1);
	}
	
	public Line2D.Float getL1() {return L1;}
	public Line2D.Float getL2() {return L2;}
	public Line2D.Float getL3() {return L3;}
	public Line2D.Float getL4() {return L4;}

	public float getCenterX() {return cx;}
	public void setCenterX(float c) {cx = c;}
	
	public float getCenterY() {return cy;}
	public void setCenterY(float c) {cy = c;}
	
	public void rotate(double angle) {
			float x = (float) (cx+(x1-cx)*Math.cos(angle)-(y1-cy)*Math.sin(angle));
			float y = (float) (cy+(y1-cy)*Math.cos(angle)+(x1-cx)*Math.sin(angle));
			x1=x;
			y1=y;
			x = (float) (cx+(x2-cx)*Math.cos(angle)-(y2-cy)*Math.sin(angle));
			y = (float) (cy+(y2-cy)*Math.cos(angle)+(x2-cx)*Math.sin(angle));
			x2=x;
			y2=y;
			x = (float) (cx+(x3-cx)*Math.cos(angle)-(y3-cy)*Math.sin(angle));
			y = (float) (cy+(y3-cy)*Math.cos(angle)+(x3-cx)*Math.sin(angle));
			x3=x;
			y3=y;
			x = (float) (cx+(x4-cx)*Math.cos(angle)-(y4-cy)*Math.sin(angle));
			y = (float) (cy+(y4-cy)*Math.cos(angle)+(x4-cx)*Math.sin(angle));
			x4=x;
			y4=y;
	}
	
	public float getX1() {return x1;}
	public void setX1(float x1) {this.x1 = x1;}
	
	public float getY1() {return y1;}
	public void setY1(float y1) {this.y1 = y1;}
	
	public float getX2() {return x2;}
	public void setX2(float x2) {this.x2 = x2;}
	
	public float getY2() {return y2;}
	public void setY2(float y2) {this.y2 = y2;}
	
	public float getX3() {return x3;}
	public void setX3(float x3) {this.x3 = x3;}
	
	public float getY3() {return y3;}
	public void setY3(float y3) {this.y3 = y3;}
	
	public float getX4() {return x4;}
	public void setX4(float x4) {this.x4 = x4;}
	
	public float getY4() {return y4;}
	public void setY4(float y4) {this.y4 = y4;}
	
	public ArrayList<Line2D.Float> getIntersection() {return intersection;}
	

	public void translate(float x, float y) {
		x1+=x;
		x2+=x;
		x3+=x;
		x4+=x;
		y1+=y;
		y2+=y;
		y3+=y;
		y4+=y;
		cx = (x1+x3)/2;
		cy = (y2+y4)/2;
	}
	
	public boolean contains(Rectangle rect) {
		if(this.contains(rect.L1) || this.contains(rect.L2) || this.contains(rect.L3) || this.contains(rect.L4))
			return true;
		return false;
	}
	
	public boolean contains(Line2D.Float line) {
		intersection.clear();
		if(line.intersectsLine(x1,y1,x2,y2))
			{intersection.add(L1);}
		if(line.intersectsLine(x2,y2,x3,y3))
			{intersection.add(L2);}
		if(line.intersectsLine(x3,y3,x4,y4))
			{intersection.add(L3);}
		if(line.intersectsLine(x4,y4,x1,y1))
			{intersection.add(L4);}
		
		if(line.intersectsLine(x1, y1, x2, y2) || line.intersectsLine(x2, y2, x3, y3) || line.intersectsLine(x3, y3, x4, y4) || line.intersectsLine(x4, y4, x1, y1))
			return true;
		return false;
	}
	
	public boolean contains(Point2D pt) {
		if(pt.getX()>=x1 && pt.getX()<=x2 && pt.getY()>=y1 && pt.getY()<=y4)
			return true;
		return false;
	}
	
	public void draw(Graphics g) {
	      g.drawLine((int)x1,(int)y1,(int)x2,(int)y2);
	      g.drawLine((int)x2,(int)y2,(int)x3,(int)y3);
	      g.drawLine((int)x3,(int)y3,(int)x4,(int)y4);
	      g.drawLine((int)x4,(int)y4,(int)x1,(int)y1);
	}
}