package game;

public class Player {

	public int score;
	public Sprite icon;
	
	public Player(String ref) {
		icon = SpriteStore.get().getSprite(ref);
	}
}
