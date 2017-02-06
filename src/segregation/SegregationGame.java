package segregation;

import java.io.File;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import cellsociety_team18.Cell;
import cellsociety_team18.Game;
import cellsociety_team18.State;
import segregation.*;

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
