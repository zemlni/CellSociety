package wildfire;

import java.io.File;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import cellsociety_team18.Cell;
import cellsociety_team18.Game;
import cellsociety_team18.State;

public class WildfireGame extends Game {
	private double fireChance;
	private double percentBurning;
	private double percentTree;

	@Override
	public void setup() {
		setName("Wildfire");
		setupBasicInfo();
		fireChance = Double.parseDouble(getSpecialInfo().get("fireChance"));
		percentTree = Double.parseDouble(getSpecialInfo().get("percentTree"));
		percentBurning = Double.parseDouble(getSpecialInfo().get("percentBurning"));
	}

	@Override
	public State getRandomState(Cell cell) {
		double rand = Math.random();
		if (rand < percentBurning) {
			return new BurningState(cell);
		}
		if (rand >= percentBurning && rand <(percentBurning + percentTree))
			return new TreeState(cell, fireChance);
		return new EmptyState(cell);
	}

}