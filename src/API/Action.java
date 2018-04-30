package API;

public class Action {
	
	private Move move;
	private Direction target;
	
	public Action(Move move) {
		this(move, null);
	}
	
	public Action(Move move, Direction target) {
		this.move = move;
		this.target = target;
	}

	public Direction getTarget() {
		return target;
	}

	public void setTarget(Direction target) {
		this.target = target;
	}

	public Move getMove() {
		return move;
	}

	public void setMove(Move move) {
		this.move = move;
	}

	public String toString() {
		return move + (target == null ? "" : " " + target);
	}
	
}
