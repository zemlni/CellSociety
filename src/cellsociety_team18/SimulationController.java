package cellsociety_team18;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Elliott Bolzan
 * This class controls all the concurrent simulations.
 */
public class SimulationController {
	
	List<Simulation> simulations = new ArrayList<Simulation>();
	
	/**
	 * @param game A string representing the game's name.
	 * @return A simulation.
	 */
	public Simulation create(String game, String configuration) {
		return new Simulation(game, configuration);
	}
	
	/**
	 * Adds a simulation to the simulation controller.
	 * @param simulation The simulation to be added.
	 */
	public void add(Simulation simulation) {
		simulation.clearProportions();
		simulations.add(simulation);
	}
	
	/**
	 * Removes a simulation from the simulation controller.
	 * @param simulation The simulation to be removed.
	 */
	public void remove(Simulation simulation) {
		simulations.remove(simulation);
	}
	
	/**
	 * Advances the simulations by a step.
	 */
	public void step() {
		for (Simulation simulation: simulations) {
			if (simulation != null) {
				simulation.step();
			}
		}
	}
	
	/**
	 * Shuffles the simulations.
	 */
	public void shuffle() {
		for (Simulation simulation: simulations) {
			if (simulation != null) {
				simulation.shuffle();
			}
		}
	}
	
	/**
	 * @return The proportions of the states in each simulation, throughout time.
	 */
	public List<List<Map<String, Number>>> getProportions() {
		List<List<Map<String, Number>>> proportions = new ArrayList<List<Map<String, Number>>>();
		for (Simulation simulation: simulations) {
			proportions.add(simulation.getProportions());
		}
		return proportions;
	}

}
