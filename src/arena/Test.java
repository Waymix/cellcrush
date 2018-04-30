package arena;
import API.Action;
import API.Cellable;
import API.Environnement;

public class Test implements Cellable{

	public String toString() {
		// TODO Auto-generated method stub
		return "DESTRUCTOR";
	}

	public Action decide_action(int ticks, Environnement env, int energy) {
		// TODO Auto-generated method stub
		return new Action(REST);
	}



}
