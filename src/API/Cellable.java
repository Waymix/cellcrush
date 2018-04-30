package API;

public interface Cellable {

	Direction UP = Direction.UP;
	Direction RIGHT = Direction.RIGHT;
	Direction DOWN = Direction.DOWN;
	Direction LEFT = Direction.LEFT;
		
	Element ROCK = Element.ROCK;
	Element ALLY = Element.ALLY;
	Element ENEMY = Element.ENEMY;
	Element FREE = Element.FREE;
	
	Move REST = Move.REST;
	Move DIE = Move.DIE;
	Move EAT = Move.EAT;
	Move MOVE = Move.MOVE;
	Move SPLIT = Move.SPLIT;
	
	String toString();
	Action decide_action(int ticks, Environnement env, int energy);
}
