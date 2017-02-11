package cellsociety_team18;

import java.util.ArrayList;
import java.util.Map;

public class SimulationController {
	
	ArrayList<Simulation> simulations = new ArrayList<Simulation>();
	
	public Simulation create(String game) {
		return new Simulation(game);
	}
	
	public void add(Simulation simulation) {
		simulation.clearProportions();
		simulations.add(simulation);
	}
	
	public void remove(Simulation simulation) {
		simulations.remove(simulation);
	}
	
	public void step() {
		for (Simulation simulation: simulations) {
			if (simulation != null) {
				simulation.step();
			}
		}
	}
	
	public void shuffle() {
		for (Simulation simulation: simulations) {
			if (simulation != null) {
				simulation.shuffle();
			}
		}
	}
	
	public ArrayList<ArrayList<Map<String, Double>>> getProportions() {
		ArrayList<ArrayList<Map<String, Double>>> proportions = new ArrayList<ArrayList<Map<String, Double>>>();
		for (Simulation simulation: simulations) {
			proportions.add(simulation.getProportions());
		}
		return proportions;
	}

}
