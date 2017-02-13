package games.wildfire;

import java.util.ArrayList;
import java.util.Arrays;

import cellsociety_team18.Game;
import cellsociety_team18.State;
import games.EmptyState;
import games.wator.FishState;
import games.wator.SharkState;

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
	 * Get a random Wator state.
	 * @return new random state.
	 */
	@Override
	public State getStateProbabilistically() {
		double rand = Math.random();
		if (rand < getDoubleParameter("percentBurning")) {
			return new BurningState(this);
		}
		if (rand >= getDoubleParameter("percentBurning") && rand < (getDoubleParameter("percentBurning") + getDoubleParameter("percentTree")))
			return new TreeState(this);
		return new EmptyState(this);
	}

	@Override
	public State getStateRandomly() {
		return getStateRandomly(new ArrayList<>(Arrays.asList(new EmptyState(this), new TreeState(this), new BurningState(this))));
	}

}