package fr.waymix.game;

import java.util.ArrayList;

import API.Cellable;

public class Initializer {
	
	ArrayList<String> players;
	
	public Initializer(ArrayList<String> players) {
		this.players = players;
	}

	public ArrayList<Cellable> init() {
		ArrayList<Cellable> list = new ArrayList<Cellable>();
		
		for (String player : players) {
			System.out.println("PLAYER=" + player);
			
			Cellable cell = null;
			
			try {
				Class<?> c = Class.forName("arena." + player);
				cell = (Cellable) c.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			list.add(cell);
		}
		
		return list;
	}
}
