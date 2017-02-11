package wator;

import cellsociety_team18.Game;
import cellsociety_team18.State;

/**
 * @author Nikita Zemlevskiy This class is the implementation of the Wator game.
 *         It contains setup related to Wator and method to get a random Wator
 *         state.
 */
public class WatorGame extends Game {

	@Override
	public void setup() {
		setParameters("percentFish", "percentSharks", "sharkReprodTime", "sharkStartEnergy", "sharkEnergyPerFish", "fishReprodTime");
	}
	
	@Override
	public void setStates() {
		getStates().put("Fish", new FishState(getIntParameter("fishReprodTime")));
		getStates().put("Shark", new SharkState(getIntParameter("sharkStartEnergy"), getIntParameter("sharkEnergyPerFish"), getIntParameter("sharkReprodTime")));
		getStates().put("Empty", new EmptyState());
	}
	
	/**
	 * Get a random Wator state.
	 * @return new random state.
	 */
	@Override
	public State getRandomState() {
		double rand = Math.random();
		if (rand < getDoubleParameter("percentFish"))
			return new FishState(getIntParameter("fishReprodTime"));
		else if (rand >= getDoubleParameter("percentFish") && rand < (getDoubleParameter("percentFish") + getDoubleParameter("percentSharks")))
			return new SharkState(getIntParameter("sharkStartEnergy"), getIntParameter("sharkEnergyPerFish"), getIntParameter("sharkReprodTime"));
		return new EmptyState();
	}

}
