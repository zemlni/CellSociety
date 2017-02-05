package wator;

import java.util.List;

import cellsociety_team18.Cell;
import cellsociety_team18.State;
import javafx.scene.paint.Color;
import wator.EmptyState;

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
		}
		List<Cell> options = getOptions();
		State replacedState = move(options);
		/*if (replacedState instanceof FishState) {
			energy += energyEarned;
		}*/
	}

}
