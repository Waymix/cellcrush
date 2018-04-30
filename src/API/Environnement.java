package API;

import fr.waymix.game.Coordinates;

public class Environnement {

	private Element[] env;
	
	/*
		env[0] = UP
		env[1] = RIGHT
		env[2] = DOWN
		env[3] = LEFT
	 */
	public Environnement(Element...elements ) {
		env = new Element[4];
		for (int i = 0 ; i < 4 ; i++) {
			this.env[i] = elements[i];
		}
	}
	
	public Element getUp() {
		return env[0];
	}
	
	public Element getDown() {
		return env[1];
	}

	public Element getRight() {
		return env[2];
	}
	
	public Element getLeft() {
		return env[3];
	}
	
	public Element get(Direction d) {
		if (d == Direction.UP) {
			return getUp();
		}
		
		if (d == Direction.RIGHT) {
			return getRight();
		}
		
		if (d == Direction.DOWN) {
			return getDown();
		}
		
		return getLeft();
	}
	
	public Coordinates getCoordsFrom(Direction d, int x, int y) {
		Coordinates out = null;
		if (d == Direction.UP) {
			out = new Coordinates(x, y - 1);
		}
		
		if (d == Direction.RIGHT) {
			out = new Coordinates(x + 1, y);
		}
		
		if (d == Direction.DOWN) {
			out = new Coordinates(x, y + 1);
		}
		
		if (d == Direction.LEFT ) {
			out = new Coordinates(x - 1, y);
		}
		
		return out;
	}
}
