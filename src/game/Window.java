package game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class Window implements KeyListener{
	public static boolean keyPress, leftPressed, rightPressed, upPressed, downPressed, ePressed, sPressed, dPressed, fPressed, mPressed, qPressed;

	public Window(int width, int height, String title, Game game)
	{
		JFrame frame = new JFrame(title);
		frame.setPreferredSize(new Dimension(width,height));
		frame.setMaximumSize(new Dimension(width,height));
		frame.setMinimumSize(new Dimension(width,height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
//		game.addMouseMotionListener(this);
		game.addKeyListener(this);
		game.requestFocus();
		game.setFocusable(true);
		frame.add(game);
		frame.setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			leftPressed = true;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			rightPressed = true;
		if(e.getKeyCode() == KeyEvent.VK_UP)
			upPressed = true;
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
			downPressed = true;
		if(e.getKeyCode() == KeyEvent.VK_M)
			mPressed = true;
		if (e.getKeyCode() == KeyEvent.VK_E)
			ePressed = true;
		if (e.getKeyCode() == KeyEvent.VK_S)
			sPressed = true;
		if(e.getKeyCode() == KeyEvent.VK_D)
			dPressed = true;
		if(e.getKeyCode() == KeyEvent.VK_F)
			fPressed = true;
		if(e.getKeyCode() == KeyEvent.VK_Q)
			qPressed = true;
//		if(leftPressed)
//			System.out.println("leftPressed is true");
//		if(rightPressed)
//			System.out.println("rightPressed is true");
//		if(upPressed)
//			System.out.println("upPressed is true");
//		if(downPressed)
//			System.out.println("downPressed is true");
//		if(wPressed)
//			System.out.println("wPressed is true");
//		if(aPressed)
//			System.out.println("aPressed is true");
//		if(sPressed)
//			System.out.println("sPressed is true");
//		if(dPressed)
//			System.out.println("dPressed is true");
		System.out.println();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			leftPressed = false;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			rightPressed = false;
		if(e.getKeyCode() == KeyEvent.VK_UP)
			upPressed = false;
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
			downPressed = false;
		if(e.getKeyCode() == KeyEvent.VK_M)
			mPressed = false;
		if (e.getKeyCode() == KeyEvent.VK_W)
			ePressed = false;
		if (e.getKeyCode() == KeyEvent.VK_A)
			sPressed = false;
		if(e.getKeyCode() == KeyEvent.VK_S)
			dPressed = false;
		if(e.getKeyCode() == KeyEvent.VK_D)
			fPressed = false;
		if(e.getKeyCode() == KeyEvent.VK_Q)
			qPressed = false;
	}

//	@Override
//	public void mouseDragged(MouseEvent e) {
//		
//	}
//
//	@Override
//	public void mouseMoved(MouseEvent e) {
//		mousePoint = new Point(e.getX(),e.getY());
////		System.out.println("("+mousePoint.getX()+","+mousePoint.getY()+")");
//	}
}
