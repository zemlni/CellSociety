package wator;

import cellsociety_team18.Cell;
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
		setName("Wator");
		setParameters("percentFish", "percentSharks", "sharkReprodTime", "sharkStartEnergy", "sharkEnergyPerFish", "fishReprodTime");
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
		if (rand < Double.parseDouble(getParameter("percentFish")))
			return new FishState(cell, Integer.parseInt(getParameter("fishReprodTime")));
		else if (rand >= Double.parseDouble(getParameter("percentFish")) && rand < (Double.parseDouble(getParameter("percentFish")) + Double.parseDouble(getParameter("percentSharks"))))
			return new SharkState(cell, Integer.parseInt(getParameter("sharkStartEnergy")), Integer.parseInt(getParameter("sharkEnergyPerFish")), Integer.parseInt(getParameter("sharkReprodTime")));
		return new EmptyState(cell);
	}

}
