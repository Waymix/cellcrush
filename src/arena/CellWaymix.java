package arena;

import API.Action;
import API.Cellable;
import API.Environnement;

public class CellWaymix implements Cellable {
	
	public String toString() {
		return "T-REX Programm";
	}
	
	public Action decide_action(int memory, Environnement env, int energy) {
		
		if (env.getUp() == ENEMY) {
			return new Action(EAT, UP);
		}
		
		if (energy > 5) {
			return new Action(SPLIT, DOWN);
		} else {
			return new Action(REST);
		}

	}
}

