package game_of_life;

import java.io.File;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import cellsociety_team18.Cell;
import cellsociety_team18.Game;
import cellsociety_team18.State;

public class GameOfLifeGame extends Game {

	@Override
	public void setup() {
		setName("GameOfLife");
		setupBasicInfo();
	}

	@Override
	public State getRandomState(Cell cell) {
		double rand = Math.random();
		return rand < Double.parseDouble(getSpecialInfo().get("percentLiving")) ? new LiveState(cell)
				: new DeadState(cell);
	}

}
