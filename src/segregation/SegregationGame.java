package segregation;

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
		parseXML();
	}
	
	@Override
	public void setStates() {
		getStates().put("Red", new RedState(getDoubleParameter("satisfaction")));
		getStates().put("Blue", new BlueState(getDoubleParameter("satisfaction")));
		getStates().put("Empty", new EmptyState());
	}
	
	/**
	 * Get a random segregation state.
	 * @return new random state.
	 */
	@Override
	public State getRandomState() {
		double rand = Math.random();
		if (rand < getDoubleParameter("percentRed"))
			return new RedState(getDoubleParameter("satisfaction"));
		if (rand >= getDoubleParameter("percentRed") && rand < (getDoubleParameter("percentRed") + getDoubleParameter("percentBlue")))
			return new BlueState(getDoubleParameter("satisfaction"));
		return new EmptyState();
	}

}
