package games.wildfire;

import java.util.ArrayList;
import java.util.Arrays;

import cellsociety_team18.Game;
import cellsociety_team18.State;
import games.EmptyState;

/**
 * @author Nikita Zemlevskiy This class is the implementation of wildfire game.
 *         It contains setup related to wildfire and method to get a random
 *         wildfire state.
 */
public class WildfireGame extends Game {

	@Override
	public void setup() {
		setParameters("fireChance", "percentTree", "percentBurning", "burningColor", "treeColor", "emptyColor");
	}
	
	@Override
	public void setStates() {
		getStates().put("Burning",  new BurningState(this));
		getStates().put("Tree", new TreeState(this));
		getStates().put("Empty", new EmptyState(this));
	}

	/**
	 * @return a new state probabilistically.
	 */
	@Override
	public State getStateProbabilistically() {
		double rand = Math.random();
		if (rand < getSettings().getDoubleParameter("percentBurning")) {
			return new BurningState(this);
		}
		if (rand >= getSettings().getDoubleParameter("percentBurning") && rand < (getSettings().getDoubleParameter("percentBurning") + getSettings().getDoubleParameter("percentTree")))
			return new TreeState(this);
		return new EmptyState(this);
	}

	/**
	 * @return a new state randomly.
	 */
	@Override
	public State getStateRandomly() {
		return getStateRandomly(new ArrayList<>(Arrays.asList(new EmptyState(this), new TreeState(this), new BurningState(this))));
	}

}