package segregation;

import java.io.File;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import cellsociety_team18.Cell;
import cellsociety_team18.Game;
import cellsociety_team18.State;
import segregation.*;

/**
 * @author Nikita Zemlevskiy This class is the implementation of Schelling's
 *         model of segregation game. It contains setup related to segregation
 *         and method to get a random segregation state.
 */
public class SegregationGame extends Game {
	private double satisfaction;
	private double percentRed;
	private double percentBlue;

	@Override
	public void setup() {
		setName("Segregation");
		setupBasicInfo();
		satisfaction = Double.parseDouble(getSpecialInfo().get("satisfaction"));
		percentRed = Double.parseDouble(getSpecialInfo().get("percentRed"));
		percentBlue = Double.parseDouble(getSpecialInfo().get("percentBlue"));

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
		if (rand < percentRed)
			return new RedState(cell, satisfaction);
		if (rand >= percentRed && rand < (percentRed + percentBlue))
			return new BlueState(cell, satisfaction);
		return new EmptyState(cell);
	}

}
