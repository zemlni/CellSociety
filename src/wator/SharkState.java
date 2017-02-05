package wator;

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
	private int reproductionTime;

	/**
	 * @param cell The cell that owns the state.
	 * @param energy The shark's original energy.
	 * Shark states are black.
	 */
	public SharkState(Cell cell, int energy, int energyEarned, int reproductionTime) {
		super(cell, reproductionTime);
		setColor(Color.BLACK);
		this.energy = energy;
		this.energyEarned = energyEarned;
		this.reproductionTime = reproductionTime;
	}

	/**
	 * Move to a random neighbor that is not a Shark.
	 * If shark eats fish, gain energy.
	 * If shark has no more energy, die.
	 */
	@Override
	public void chooseState() {
		State replaced = moveTo(new SharkState(null, energy, energyEarned, reproductionTime));
		if (replaced instanceof FishState) {
			energy += energyEarned;
		}
		energy--;
		if (energy <= 0) {
			getCell().setNextState(new EmptyState(getCell()));
		}
	}

}
