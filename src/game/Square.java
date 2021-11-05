package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;

public class Square {

	private Line2D.Float[][] square = new Line2D.Float[2][2];
	private int cornerX, cornerY, size;
	
	public Square(int cornerX, int cornerY, int size, boolean north, boolean south, boolean east, boolean west)
	{
		this.cornerX = cornerX;
		this.cornerY = cornerY;
		this.size = size;
		if(north)
			square[0][0] = new Line2D.Float(cornerX-size,cornerY,cornerX,cornerY);
		if(east)
			square[0][1] = new Line2D.Float(cornerX,cornerY-size,cornerX,cornerY);
		if(south)
			square[1][1] = new Line2D.Float(cornerX-size,cornerY-size,cornerX,cornerY-size);
		if(west)
			square[1][0] = new Line2D.Float(cornerX-size,cornerY-size,cornerX-size,cornerY);
	}

	public Line2D.Float[][] getSquare() {
		return square;
	}
	
	public void drawSquare(Graphics g) {
		Graphics2D g2D = (Graphics2D) g.create();
		if(square[0][0]!=null)
			g2D.draw(square[0][0]);
		if(square[0][1]!=null)
			g2D.draw(square[0][1]);
		if(square[1][1]!=null)
			g2D.draw(square[1][1]);
		if(square[1][0]!=null)
			g2D.draw(square[1][0]);
		g2D.dispose();
	}
	
	public Point getCenter() {
		return new Point(cornerX-size/2,cornerY-size/2);
	}
	
	public int getSize() {
		return size;
	}
	
	public Point getOrigin() {
		return new Point(cornerX,cornerY);
	}
}
