package API;

import java.util.ArrayList;

import compiler.Compiler;
import game.Game;
import game.Initializer;

public class Main {

	public static void main(String args[]) {
		Compiler compiler = new Compiler();
		
		compiler.analyse();; // the folder that contains .java of the users
		ArrayList<String> player_scripts = compiler.compile();
		ArrayList<Cellable> players = new ArrayList<Cellable>();
		
		Initializer initializer = new Initializer(player_scripts);
		players = initializer.init();
		
		Game game = new Game(players);
		
		game.launch();
		
		//utiliser une base redis avec publisher subscribers
	}
}
