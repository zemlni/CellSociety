package wildfire;

import cellsociety_team18.Game;
import cellsociety_team18.State;

/**
 * @author Nikita Zemlevskiy This class is the implementation of wildfire game.
 *         It contains setup related to wildfire and method to get a random
 *         wildfire state.
 */
public class WildfireGame extends Game {

	@Override
	public void setup() {
		setParameters("fireChance", "percentTree", "percentBurning");
	}
	
	@Override
	public void setStates() {
		getStates().put("Burning",  new BurningState());
		getStates().put("Tree", new TreeState(getDoubleParameter("fireChance")));
		getStates().put("Empty", new EmptyState());
	}

	/**
	 * Get a random Wator state.
	 * @return new random state.
	 */
	@Override
	public State getRandomState() {
		double rand = Math.random();
		if (rand < getDoubleParameter("percentBurning")) {
			return new BurningState();
		}
		if (rand >= getDoubleParameter("percentBurning") && rand < (getDoubleParameter("percentBurning") + getDoubleParameter("percentTree")))
			return new TreeState(getDoubleParameter("fireChance"));
		return new EmptyState();
	}

}