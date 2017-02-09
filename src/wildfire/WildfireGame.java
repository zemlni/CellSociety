package wildfire;

import cellsociety_team18.Cell;
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
		setName("Wildfire");
		setParameters("fireChance", "percentTree", "percentBurning");
		setupBasicInfo();
	}

	/**
	 * Get a random Wator state.
	 * 
	 * @param cell
	 *            the cell to which the new state will belong.
	 * @return new random state.
	 */
	@Override
	public State getRandomState(Cell cell) {
		double rand = Math.random();
		if (rand < Double.parseDouble(getParameter("percentBurning"))) {
			return new BurningState(cell);
		}
		if (rand >= Double.parseDouble(getParameter("percentBurning")) && rand < (Double.parseDouble(getParameter("percentBurning")) + Double.parseDouble(getParameter("percentTree"))))
			return new TreeState(cell, Double.parseDouble(getParameter("fireChance")));
		return new EmptyState(cell);
	}

}