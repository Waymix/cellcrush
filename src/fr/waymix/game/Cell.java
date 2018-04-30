package fr.waymix.game;
// TODO: set the package to arena when send to the users
// package arena;

import API.*;

public class Cell implements Cellable {
	
	public String toString() {
		// TODO: Return here the name of your script
		return null;
	}
	
	public Action decide_action(int ticks, Environnement env, int energy) {
		
		// TODO: your code is here
		
		if (ticks > 5) {
			return new Action(Move.SPLIT, Direction.DOWN);
		}
		
		return new Action(Move.REST);
	}
}
