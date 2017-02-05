package wator;

import java.util.Collections;
import java.util.List;

import cellsociety_team18.Cell;
import cellsociety_team18.State;
import javafx.scene.paint.Color;

/**
 * @author elliott
 * This class represents a Shark in Wator.
 */
public class SharkState extends AgentState {
	
	private int energy;
	private int energyEarned;

	/**
	 * @param cell The cell that owns the state.
	 * @param energy The shark's original energy.
	 * Shark states are black.
	 */
	public SharkState(Cell cell, int originalEnergy, int energyEarned, int reproductionTime) {
		super(cell, reproductionTime);
		setColor(Color.BLACK);
		energy = originalEnergy;
		this.energyEarned = energyEarned;
	}

	/**
	 * Move to a random neighbor that is not a Shark.
	 * If shark eats fish, gain energy.
	 * If shark has no more energy, die.
	 */
	@Override
	public void chooseState() {
		energy--;
		if (energy <= 0) {
			getCell().setNextState(new EmptyState(getCell()));
			return;
		}
		//State replacedState = move(getOptions());
		setSurvivalTime(getSurvivalTime() + 1);
		List<Cell> neighbors = getCell().getNeighbors();
		Collections.shuffle(neighbors);
		if (neighbors.size() > 0) {
			Cell neighbor = neighbors.get(0);
			if (!(neighbor.getNextState() instanceof SharkState)) {
				if (neighbor.getNextState() instanceof FishState) {
					energy += energyEarned;
				}
				if (getSurvivalTime() >= getReproductionTime()) {
					setSurvivalTime(0);
					neighbor.setNextState(new SharkState(neighbor, energy, energyEarned, getReproductionTime()));
					return;
				}
				getCell().setNextState(new EmptyState(getCell()));
				neighbor.setNextState(new SharkState(neighbor, energy, energyEarned, getReproductionTime()));
			}
		}
	}

}
