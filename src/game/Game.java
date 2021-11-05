package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;
import java.util.Random;

public class Game extends Canvas implements Runnable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//JFrame
	public static int WIDTH=800, HEIGHT=800;
	public static String TITLE = "Tank Trouble";
//Window	
	private Thread thread;
	private boolean isRunning;
	public static int scaleFactor;
//Maze
	private static Maze maze;
	private static int row, column, offSetX, offSetY;
//Player
	public static Player player1, player2;
	private static int p1X, p1Y, p2X, p2Y;
	private static Tank tank1, tank2;
//Instances
	public static Handler handler = new Handler();
	private static Random rand = new Random();
	public static LinkedList<GameObject> toRemove = new LinkedList<GameObject>();
	
	public Game() {
		new Window(WIDTH,HEIGHT,TITLE,this);
		start();
	}
	
	private synchronized void start() {
		if(isRunning) return;
		
		thread = new Thread(this);
		thread.start();
		isRunning=true;
	}
	
	private synchronized void stop() {
		if(!isRunning) return;
		
		try {thread.join();} catch (InterruptedException e) {e.printStackTrace();}
		isRunning=false;
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 30.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(isRunning){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			if(Window.mPressed)
				tank1.tryFire();
			if(Window.qPressed)
				tank2.tryFire();
			
			for(GameObject obj : toRemove) {
				if(obj.myID==ID.Tank && ((Tank)obj).isDead() && System.currentTimeMillis()-((Tank)obj).getTimeDead()<50) {
					
				}
				else {
					handler.removeObject(obj);
				}
			}
			frames++;
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
		stop();
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs==null) {
			this.createBufferStrategy(2);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(Color.black);
		maze.drawMaze(g);

		handler.render(g);
		bs.show();
		g.dispose();
	}

	private void tick() {
		handler.tick();
	}
	
	public static void makeEnvironment()
	{
//Makes Maze
		row = 5+rand.nextInt(5);
		column = 5+rand.nextInt(5);
		if(row>=column) {
			scaleFactor = (HEIGHT-150)/(row+2);
			offSetY = scaleFactor;
			offSetX = (WIDTH - scaleFactor*column)/2;
		}
		if(row<column) {
			scaleFactor = WIDTH/(column+2);
			offSetX = scaleFactor;
			offSetY = (HEIGHT-150-scaleFactor*row)/2;
		}
		maze = new Maze(row,column, scaleFactor, offSetX, offSetY);
		
//Adds Players and Tanks
		player1 = new Player("RedPlayer.jpg");
		player2 = new Player("BluePlayer.jpg");

		Square randSquare = Maze.maze[1+rand.nextInt(column)][1+rand.nextInt(row)];
		p1X = (int) randSquare.getCenter().getX();
		p1Y = (int) randSquare.getCenter().getY();
		
		Square randSquare2;
		do{
			randSquare2 = Maze.maze[1+rand.nextInt(column)][1+rand.nextInt(row)];
		} while(randSquare==randSquare2);
		randSquare = randSquare2;
		
		p2X = (int) randSquare.getCenter().getX();
		p2Y = (int) randSquare.getCenter().getY();

		tank1 = new Tank("BlueNormal.png",player1,p1X,p1Y,3,1000);
		handler.addObject(tank1);
		tank2 = new Tank("RedNormal.png",player2,p2X,p2Y,3,1000);
		handler.addObject(tank2);
	}

	public static void main(String[] args) {
		makeEnvironment();
		Game obj = new Game();
	}
}
