package wator;

import java.util.Collections;
import java.util.List;

import cellsociety_team18.Cell;
import javafx.scene.paint.Color;

/**
 * @author elliott
 * This class represents a Fish in Wator.
 */
public class FishState extends AgentState {
	
	/**
	 * @param cell The cell that owns the state.
	 * @param reproductionTime The number of iterations before the fish reproduces.
	 * Fish states are green.
	 */
	public FishState(Cell cell, int reproductionTime) {
		super(cell, reproductionTime);
		setColor(Color.LAWNGREEN);
	}

	/**
	 * Move to a random Empty neighbor.
	 */
	@Override
	public void chooseState() {
		// Get neighbors. Remove non-empty ones.
		// Move to a non-empty one if there is one. Reproduce if I can.
		//move(getOptions());
		setSurvivalTime(getSurvivalTime() + 1);
		List<Cell> neighbors = getCell().getNeighbors();
		Collections.shuffle(neighbors);
		if (neighbors.size() > 0) {
			Cell neighbor = neighbors.get(0);
			if (neighbor.getNextState() instanceof EmptyState) {
				if (getSurvivalTime() >= getReproductionTime()) {
					setSurvivalTime(0);
					neighbor.setNextState(new FishState(neighbor, getReproductionTime()));
					return;
				}
				getCell().setNextState(new EmptyState(getCell()));
				neighbor.setNextState(new FishState(neighbor, getReproductionTime()));
			}
		}
	}

}
