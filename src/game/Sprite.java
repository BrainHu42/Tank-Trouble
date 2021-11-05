package game;

import java.awt.Image;

public class Sprite {
	private Image image;

	public Sprite(Image image) {
		this.image = image;
	}

	public int getWidth() {
		return image.getWidth(null);
	}

	public int getHeight() {
		return image.getHeight(null);
	}
	
	public Image getImage() {
		return image;
	}
	
	public void resizeTo(int x, int y) {
		image = image.getScaledInstance(x, y, Image.SCALE_DEFAULT);
	}
}
