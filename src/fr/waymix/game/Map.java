package fr.waymix.game;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import API.Direction;

public class Map {
	
	private UniqueCell map[][];
	private int size_x, size_y;
	
	public Map(int size_x, int size_y) {
		this.size_x = size_x;
		this.size_y = size_x;
		map = new UniqueCell[size_x][size_y];
	}

	public void set(int x, int y, UniqueCell cell) {
		if (x >= size_x || y >= size_y || x < 0 || y < 0)
			System.out.println("A set in the map is out of limit.");
		map[x][y] = cell;
	}
	
	public UniqueCell get(int x, int y) {
		if (x < 0 || y < 0 || x >= size_x || y >= size_y) {
			System.out.println("Borne de map dépassée : x=" + x + " y=" + y);
			return null;
		}
		else
			return map[x][y];
	}
	
	//public UniqueCell[][] getMap() { return this.map; }

	public int getSizeX() { return this.size_x; }
	public int getSizeY() { return this.size_y; }
	
	public UniqueCell getUniqueCellAt(int x, int y) {
		return map[x][y];
	}
	
	public boolean isFreeCell(Direction direction, int x, int y) {
		
		if (direction == Direction.UP) {
			if (y < 1)
				return false;
			else
				return map[x][y - 1] == null;
		} else if (direction == Direction.RIGHT) {
			if (x >= size_x - 1)
				return false;
			else
				return map[x + 1][y] == null;
		} else if (direction == Direction.DOWN) {
			if (y >= size_y - 1)
				return false;
			else
				return map[x][y + 1] == null;
		} else {
			if (x < 1)
				return false;
			else
				return map[x - 1][y] == null;
		}
		
	}

	public void newCell(Direction direction, int x, int y, UniqueCell newCell) {
		if (direction == Direction.UP) {
			map[x][y - 1] = newCell;
		} else if (direction == Direction.RIGHT) {
			map[x + 1][y] = newCell;
		} else if (direction == Direction.DOWN) {
			map[x][y + 1] = newCell;
		} else if (direction == Direction.LEFT) {
			map[x - 1][y] = newCell;
		}
	}

	public void moveCell(Direction direction, int x, int y, UniqueCell cell) {
		if (direction == Direction.UP) {
			map[x][y - 1] = cell;
		} else if (direction == Direction.RIGHT) {
			map[x + 1][y] = cell;
		} else if (direction == Direction.DOWN) {
			map[x][y + 1] = cell;
		} else if (direction == Direction.LEFT) {
			map[x - 1][y] = cell;
		}
		
		map[x][y] = null;
	}
	
	public String toString() {
		String output = "";
		ArrayList<String> players = new ArrayList<String>();
		
		for (int i = 0 ; i < size_x ; i++) {
			for (int j = 0 ; j < size_y ; j++) {
				if (map[i][j] == null) {
					output += "- ";
				} else {
					output += map[i][j].toString().charAt(0) + " ";
					players.add(map[i][j].toString().charAt(0) + "=" + map[i][j].toString());
				}
			}
			output += "\n";
		}
		
		output += "Players:\n";
		for (String a : players) {
			output += a + "\n";
		}
		output += "\n";
		
		return output;
	}
	
	public void savePNG(String path) {
		try {
            RenderedImage rendImage = generate_bufferedImage();
            ImageIO.write(rendImage, "bmp", new File(path));
            //ImageIO.write(rendImage, "PNG", new File(path));
            //ImageIO.write(rendImage, "jpeg", new File(path));
        } catch ( IOException e) {
            e.printStackTrace();
        }
	}
	
	private BufferedImage generate_bufferedImage(){
	    final BufferedImage res = new BufferedImage( size_x, size_y, BufferedImage.TYPE_INT_RGB );
	    
	    for (int x = 0; x < size_x; x++){
	        for (int y = 0; y < size_y; y++){
	        	
	        	if (map[x][y] == null) {
	        		res.setRGB(x, y, Color.BLACK.getRGB() );
	        	} else {
	        		UniqueCell cell = map[x][y];
	        		res.setRGB(x, y, cell.getColor().getRGB());
	        	}
	        }
	    }
	    return res;
	}

}
