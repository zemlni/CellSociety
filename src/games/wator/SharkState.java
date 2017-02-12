package games.wator;

import cellsociety_team18.Game;
import games.EmptyState;
import javafx.scene.paint.Color;

/**
 * @author elliott
 * This class represents a Shark in Wator.
 */
public class SharkState extends AgentState {
	
	private Game game;
	private int energy;
	private int energyEarned;

	/**
	 * @param game The state's owner.
	 */
	public SharkState(Game game) {
		super(game);
		this.game = game;
		setColor(Color.web(game.getParameter("sharkColor").toUpperCase()));
		setReproductionTime(game.getIntParameter("sharkReprodTime"));
		this.energy = game.getIntParameter("sharkStartEnergy");
		this.energyEarned = game.getIntParameter("sharkEnergyPerFish");
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
			getCell().setNextState(new EmptyState(game));
			return;
		}
		moveTo(new SharkState(game));
	}
	
	/**
	 * Add the energy earned by eating a fish to our energy.
	 */
	public void incrementEnergy() {
		energy += energyEarned;
	}

}
