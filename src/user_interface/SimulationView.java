package user_interface;

import cellsociety_team18.Simulation;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SimulationView extends Stage {
	
	private DisplayGrid grid;
	private Simulation simulation;

	public SimulationView(Simulation simulation, DisplayGrid grid, int size, int number) {
		this.grid = grid;
		this.simulation = simulation;
        setResizable(false);
        setTitle("Simulation " + Integer.toString(number) + " - " + simulation.getGame().getTitle());
        setScene(new Scene(grid, size, size));
        show();
	}
	
	public Simulation getSimulation() {
		return simulation;
	}
	
	public void update() {
		grid.update(simulation.getGrid());
	}
	
}
