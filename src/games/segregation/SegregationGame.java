package games.segregation;

import cellsociety_team18.Game;
import cellsociety_team18.State;
import games.EmptyState;

/**
 * @author Nikita Zemlevskiy This class is the implementation of Schelling's
 *         model of segregation game. It contains setup related to segregation
 *         and method to get a random segregation state.
 */
public class SegregationGame extends Game {

	@Override
	public void setup() {
		setParameters("satisfaction", "percentPopulation1", "percentPopulation2", "population1Color", "population2Color", "emptyColor");
	}
	
	@Override
	public void setStates() {
		getStates().put("Population 1", new PopulationOne(this));
		getStates().put("Population 2", new PopulationTwo(this));
		getStates().put("Empty", new EmptyState(this));
	}
	
	/**
	 * Get a random segregation state.
	 * @return new random state.
	 */
	@Override
	public State getRandomState() {
		double rand = Math.random();
		if (rand < getDoubleParameter("percentPopulation1"))
			return new PopulationOne(this);
		if (rand >= getDoubleParameter("percentPopulation1") && rand < (getDoubleParameter("percentPopulation1") + getDoubleParameter("percentPopulation2")))
			return new PopulationTwo(this);
		return new EmptyState(this);
	}

}
