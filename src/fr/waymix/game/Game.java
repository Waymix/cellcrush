package fr.waymix.game;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import API.Action;
import API.Cellable;
import API.Direction;
import API.Element;
import API.Environnement;
import API.Move;

public class Game {
	
	private ArrayList<Cellable> players;
	int memory;
	Map map;
	int refresh_speed = 200;
	
	public Game(ArrayList<Cellable> players) {
		this.players = players;
		this.map = new Map(10, 10);
		this.memory = 0;
	}
	
	public void launch() {
		
		System.out.println("Starting the party, users are :");
		for (Cellable c : players) {
			System.out.println(c);
		}
		
		UniqueCell j1 = new UniqueCell( players.get(0), 1, new Action(Move.REST), new RGB(0, 0, 255));
		UniqueCell j2 = new UniqueCell( players.get(1), 1, new Action(Move.REST), new RGB(0, 200, 0));
		
		map.set(1, 1, j1);
		map.set(8, 8, j2);
		
		System.out.println("\nStarting the game...");
		
		main();
	}
	
	public void main() {
		
		System.out.println(map);
		map.savePNG("/tmp/map.bmp");
		
		operate();
		
		System.out.println("Memory=" + memory);
		
		new Timer().schedule( 
	        new TimerTask() {
	            @Override
	            public void run() {
	            	main();
	            }
	        }, 
	        this.refresh_speed 
		);
	}
	
	public void operate() {
		for (int i = 0 ; i < map.getSizeX() ; i++) {
			for (int j = 0 ; j < map.getSizeY() ; j++) {
				
				if (map.get(i, j) != null) {
					
					UniqueCell cell = map.get(i, j);
					
					System.out.println(" looped " + cell);
					
					// Heal the RESTING cells
					if (cell.getEnergy() < 10 && cell.getAction().getMove() == Move.REST)
						cell.setEnergy( cell.getEnergy() + 1 );
					
					// Kill the cells that want to be killed
					if (cell.getAction().getMove() == Move.DIE)
						map.set(i, j, null);
					
					// Declaring the elements around the cell
					Element up, right, down, left;
					
					System.out.println("A");
					if (j < 1) {
						 up = Element.ROCK;
					} else if (map.get(i, j - 1) == null) {
						up = Element.FREE;
					} else {
						 if (cell.getOwner().equals(map.get(i, j - 1).getOwner())) {
							 up = Element.ALLY;
						 } else {
							 up = Element.ENEMY;
						 }
					}
					
					if (i >= map.getSizeX() - 1) {
						 right = Element.ROCK;
					} else if (map.get(i + 1, j) == null) {
						right = Element.FREE;
					} else {
						 if (cell.getOwner().equals(map.get(i + 1, j).getOwner())) {
							 right = Element.ALLY;
						 } else {
							 right = Element.ENEMY;
						 }
					}
					
					if (j >= map.getSizeY() - 1) {
						 down = Element.ROCK;
					} else if (map.get(i, j + 1) == null) {
						down = Element.FREE;
					} else {
						 if (cell.getOwner().equals(map.get(i, j + 1).getOwner())) {
							 down = Element.ALLY;
						 } else {
							 down = Element.ENEMY;
						 }
					}
					
					if (i < 1) {
						 left = Element.ROCK;
					} else if (map.get(i - 1, j) == null) {
						left = Element.FREE;
					} else {
						 if (cell.getOwner().equals(map.get(i - 1, j).getOwner())) {
							 left = Element.ALLY;
						 } else {
							 left = Element.ENEMY;
						 }
					}
					
					// Declaring the UniqueCell's environment : where are enemies, where are allies, where is the rock ?
					Environnement env = new Environnement(up, right, down, left);
					
					// If the cells has chosen to eat something 
					if (cell.getAction().getMove() == Move.EAT) {
						Direction target = cell.getAction().getTarget();
						
						// à tester
						Coordinates coords = env.getCoordsFrom(target, i, j);
						UniqueCell target_cell = map.get(coords.getX(), coords.getY());
						
						// if the cells isn't FREE, AND she's ENEMY OR ALLY, in both case she looses 1 of energy
						if (target_cell != null && (env.get(target) == Element.ENEMY || env.get(target) == Element.ALLY)) {
							target_cell.setEnergy(target_cell.getEnergy() - 1);
							
							// This enemy cell has been killed
							if (target_cell.getEnergy() <= 0) {
								map.set(i, j, null);
							}
						}
					}
					
					// If the cells has chosen to split somewhere
					if (cell.getAction().getMove() == Move.SPLIT) {
						
						Direction direction = cell.getAction().getTarget();
						
						System.out.println(" SPLIT dir=" + direction);
						
						if (map.isFreeCell(direction, i, j) && cell.getEnergy() > 5) {
							
							// declaring a new cell with her energy divided by 2 from it's parent
							UniqueCell newCell = new UniqueCell(cell.getOwner(), cell.getEnergy() / 2, new Action(Move.REST), cell.getRGB());
							
							// setting this new cell on the map
							map.newCell(direction, i, j, newCell);
							
							// dividing by 2 the energy of the parent cell
							cell.setEnergy( cell.getEnergy() / 2);
						}
					}
					
					// If the cells wants to move
					if (cell.getAction().getMove() == Move.MOVE) {
						
						Direction direction = cell.getAction().getTarget();
						
						if (map.isFreeCell(direction, i, j) && cell.getEnergy() > 5) {
							// moving the cell from i, j a direction
							map.moveCell(direction, i, j, cell);
							
							// the cell looses 1 of energy
							cell.setEnergy( cell.getEnergy() - 1);
						}
					}
					
					/* executing the cell's script */
					cell.setState( cell.getOwner().decide_action(memory, env, cell.getEnergy()) );
					
				}
				
			}
		}
		
		memory++;
	}
	
	public void setRefreshSpeed(int a) {
		this.refresh_speed = a;
	}
}
