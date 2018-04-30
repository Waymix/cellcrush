package fr.waymix.game;

import java.awt.Color;

import API.Action;
import API.Cellable;

public class UniqueCell {

	private Cellable owner;
	private int energy;
	private Action action;
	private RGB color;
	
	public UniqueCell(Cellable owner, int energy, Action action, RGB color) {
		this.owner = owner;
		this.energy = energy;
		this.action = action;
		this.color = color;
	}
	
	public RGB getRGB() { return color; }
	
	public Color getColor() {
		int red = color.getR() * energy/10;
		int green = color.getG() * energy/10;
		int blue = color.getB() * energy/10;
		System.out.println(red + " " + green + " " + blue);
		return new Color(red, green, blue); 
	}
	
	public Action getAction() { return this.action; }
	
	public Cellable getOwner() { return this.owner; }
	
	public int getEnergy() { return energy; }
	
	public void setEnergy(int energy) { this.energy = energy; }
	
	public boolean equals(Object o) {
		if (o instanceof UniqueCell) {
			UniqueCell u = (UniqueCell) o;
			if (u.getOwner().equals(this.getOwner()) && u.getEnergy() == this.getEnergy())
				return true;
			else
				return false;
		} else {
			return false;
		}
	}
	
	public String toString() {
		return "Cell: energy=" + energy + ", owner=" + owner + ", action=" + action;
	}

	public void setState(Action new_action) {
		this.action = new_action;
	}
}
