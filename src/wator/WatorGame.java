package wator;

import java.io.File;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import cellsociety_team18.Cell;
import cellsociety_team18.Game;
import cellsociety_team18.State;

/**
 * @author Nikita Zemlevskiy This class is the implementation of the Wator game.
 *         It contains setup related to Wator and method to get a random Wator
 *         state.
 */
public class WatorGame extends Game {

	private double percentFish;
	private double percentSharks;
	private int sharkReprodTime;
	private int sharkStartEnergy;
	private int sharkEnergyPerFish;
	private int fishReprodTime;

	@Override
	public void setup() {
		setName("Wator");
		setupBasicInfo();
		percentFish = Double.parseDouble(getSpecialInfo().get("percentFish"));
		percentSharks = Double.parseDouble(getSpecialInfo().get("percentSharks"));
		sharkReprodTime = Integer.parseInt(getSpecialInfo().get("sharkReprodTime"));
		sharkStartEnergy = Integer.parseInt(getSpecialInfo().get("sharkStartEnergy"));
		sharkEnergyPerFish = Integer.parseInt(getSpecialInfo().get("sharkEnergyPerFish"));
		fishReprodTime = Integer.parseInt(getSpecialInfo().get("fishReprodTime"));
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
		if (rand < percentFish)
			return new FishState(cell, fishReprodTime);
		else if (rand >= percentFish && rand < (percentFish + percentSharks))
			return new SharkState(cell, sharkStartEnergy, sharkEnergyPerFish, sharkReprodTime);
		return new EmptyState(cell);
	}

}
