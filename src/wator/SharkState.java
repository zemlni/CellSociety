package wator;

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
	 * @param energy The shark's original energy.
	 * Shark states are black.
	 */
	public SharkState( int energy, int energyEarned, int reproductionTime) {
		super(reproductionTime);
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
			getCell().setNextState(new EmptyState());
			return;
		}
		moveTo(new SharkState(originalEnergy, energyEarned, reproductionTime));
	}
	
	/**
	 * Add the energy earned by eating a fish to our energy.
	 */
	public void incrementEnergy() {
		energy += energyEarned;
	}

}
