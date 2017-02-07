package wator;

import cellsociety_team18.Cell;
import cellsociety_team18.State;
import javafx.scene.paint.Color;

/**
 * @author elliott
 * This class represents a Shark in Wator.
 */
public class SharkState extends AgentState {
	
	private int originalEnergy;
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
		this.originalEnergy = energy;
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
		energy--;
		if (energy <= 0) {
			//System.out.println("died");
			getCell().setNextState(new EmptyState(getCell()));
			return;
		}
		moveTo(new SharkState(null, originalEnergy, energyEarned, reproductionTime));
	}
	
	/**
	 * Add the energy earned by eating a fish to our energy.
	 */
	public void incrementEnergy() {
		energy += energyEarned;
	}

}
