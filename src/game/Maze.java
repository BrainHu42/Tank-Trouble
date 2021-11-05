package game;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.Random;


public class Maze {
	private Random randy = new Random();
	public static Square[][] maze;
//Initializes each square's side
	private int row, column, scaleFactor, offSetX, offSetY;
	private boolean[][] northWall;
	private boolean[][] southWall;
	private boolean[][] eastWall;
	private boolean[][] westWall;

//Keeps Track of Visited Squares
	private boolean[][] visited;
	
	public Maze(int row,int column,int scaleFactor,int offSetX,int offSetY)
	{
		this.row = row;
		this.column = column;
		this.scaleFactor=scaleFactor;
		this.offSetX=offSetX;
		this.offSetY=offSetY;
		initialize();
		generateMaze(1,1);
		if(row>column)
			deleteWalls(column);
		if(column>row)
			deleteWalls(row);
		createMazeObject();
//		System.out.println();
//		print2DArray(eastWall);
//		System.out.println();
//		print2DArray(southWall);
//		System.out.println();
//		print2DArray(westWall);
	}
	
    private void initialize() {
        // initializes border squares as already visited
        visited = new boolean[column+2][row+2];
        for (int x = 0; x < column+2; x++) {
            visited[x][0] = true;
            visited[x][row+1] = true;
        }
        for (int y = 0; y < row+2; y++) {
            visited[0][y] = true;
            visited[column+1][y] = true;
        }

        // initialzes all walls as present
        northWall = new boolean[column+2][row+2];
        eastWall  = new boolean[column+2][row+2];
        southWall = new boolean[column+2][row+2];
        westWall  = new boolean[column+2][row+2];
        for (int x = 0; x < column+2; x++) {
            for (int y = 0; y < row+2; y++) {
                northWall[x][y] = true;
                eastWall[x][y]  = true;
                southWall[x][y] = true;
                westWall[x][y]  = true;
            }
        }
    }

//Implementation of Aldous-Broder Algorithm (Recursively)
	private void generateMaze(int x, int y)
	{
		visited[x][y] = true;

        // while there is an unvisited neighbor
        while (!visited[x][y+1] || !visited[x+1][y] || !visited[x][y-1] || !visited[x-1][y]) {

            while (true) {
                double r = randy.nextInt(4);
                if (r == 0 && !visited[x][y+1]) {
                    northWall[x][y] = false;
                    southWall[x][y+1] = false;
                    generateMaze(x, y + 1);
                    break;
                }
                else if (r == 1 && !visited[x+1][y]) {
                    eastWall[x][y] = false;
                    westWall[x+1][y] = false;
                    generateMaze(x+1, y);
                    break;
                }
                else if (r == 2 && !visited[x][y-1]) {
                    southWall[x][y] = false;
                    northWall[x][y-1] = false;
                    generateMaze(x, y-1);
                    break;
                }
                else if (r == 3 && !visited[x-1][y]) {
                    westWall[x][y] = false;
                    eastWall[x-1][y] = false;
                    generateMaze(x-1, y);
                    break;
                }
            }
        }
	}
	
	private void deleteWalls(int n) {
//        for (int i = 0; i < n; i++) {
//            int x = 1 + randy.nextInt(n-1);
//            int y = 1 + randy.nextInt(n-1);
//            northWall[x][y] = southWall[x][y+1] = false;
//        }
	}
	
//	private void print2DArray(boolean[][] arry) {
//		for(int i=row; i>=1; i--) {
//		for(int j=1; j<=column; j++) {
//			if(arry[j][i])
//				System.out.print("true"+"\t");
//			else
//				System.out.print("false"+"\t");
//		}
//		System.out.println();
//		}
//		for(int x=1; x<=row; x++) {
//			for(int y=1; y<=column; y++) {
//				for(int i=0; i<4; i++) {
//					if(maze[x][y].getSquare()[i]!=null)
//						System.out.print("false"+"\t");
//					else
//						System.out.print("true"+"\t");
//				}
//				System.out.println();
//			}
//		}
//	}
	
	private void createMazeObject() {
		
		maze = new Square[column+2][row+2];
		for(int x = 0; x<column+2; x++) {
			for(int y = 0; y<row+2; y++) {
				maze[x][y] = new Square(scaleFactor*x+offSetX,scaleFactor*y+offSetY,scaleFactor,northWall[x][y],southWall[x][y],eastWall[x][y],westWall[x][y]);
			}
		}
	}
	
	public void drawMaze(Graphics g)
	{
		Graphics2D g2D = (Graphics2D) g;
		g2D.setStroke(new BasicStroke(1));
		for(int x=1; x<=column; x++) {
			for(int y=1; y<=row; y++) {
				maze[x][y].drawSquare(g);
			}
		}
	}
}
