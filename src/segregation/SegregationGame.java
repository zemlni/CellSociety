package segregation;

import cellsociety_team18.Cell;
import cellsociety_team18.Game;
import cellsociety_team18.State;

/**
 * @author Nikita Zemlevskiy This class is the implementation of Schelling's
 *         model of segregation game. It contains setup related to segregation
 *         and method to get a random segregation state.
 */
public class SegregationGame extends Game {

	@Override
	public void setup() {
		setName("Segregation");
		setParameters("satisfaction", "percentRed", "percentBlue");
		setupBasicInfo();
	}
	
	/**
	 * Get a random segregation state.
	 * 
	 * @param cell
	 *            the cell to which the new state will belong.
	 * @return new random state.
	 */
	@Override
	public State getRandomState(Cell cell) {
		double rand = Math.random();
		if (rand < Double.parseDouble(getParameter("percentRed")))
			return new RedState(cell, Double.parseDouble(getParameter("satisfaction")));
		if (rand >= Double.parseDouble(getParameter("percentRed")) && rand < (Double.parseDouble(getParameter("percentRed")) + Double.parseDouble(getParameter("percentBlue"))))
			return new BlueState(cell, Double.parseDouble(getParameter("satisfaction")));
		return new EmptyState(cell);
	}

}
