package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Line2D;

public class Circle {

	private int radius;
	private Point center;
	
	public Point getCenter() {return center;}
	public int getRadius() {return radius;}
	
	public Circle(Point c, int r)
	{
		center = c;
		radius = r;
	}
	
	public void draw(Graphics g, Color c) {
		g.setColor(c);
		g.fillArc((int)center.getX()-radius, (int)center.getY()-radius, radius*2, radius*2, 0, 360);
	}
	
	public boolean contains(Rectangle rect) {
		if(center.distance(rect.getX1(), rect.getY1())<=radius || center.distance(rect.getX2(), rect.getY2())<=radius
				|| center.distance(rect.getX3(),rect.getY3())<=radius || center.distance(rect.getX4(), rect.getY4())<=radius)
			return true;
		return false;
	}
	
	public boolean contains(Line2D.Float line) {
	//	if(line.ptLineDist(center.getX(), center.getY()))<radius /*&& Math.abs(line.ptLineDist(center.getX(), center.getY()))>radius-5*/)
		if((line.x1==line.x2 && Math.abs(center.getX()-line.x1)<=radius) || (line.y1==line.y2 && Math.abs(center.getY()-line.y1)<=radius))
			return true;
		return false;
	}
	
	public boolean intersects(Line2D.Float line) {
		int delta = Math.round((float)line.ptLineDist(center.getX(), center.getY()));
		if(delta == radius || delta == radius-1 || delta == radius+1 || delta == radius +2 || delta == radius-2)
			return true;
		return false;
	}
	
	public boolean sameDistance(Line2D.Float line1, Line2D.Float line2) {
		if(line1!=null && line2!=null && Math.round((float)line1.ptLineDist(center.getX(), center.getY()))==Math.round((float)line1.ptLineDist(center.getX(),center.getY())))
			return true;
		return false;
	}
}
