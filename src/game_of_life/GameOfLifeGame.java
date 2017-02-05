package game_of_life;

import java.io.File;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import cellsociety_team18.Cell;
import cellsociety_team18.Game;
import cellsociety_team18.State;

public class GameOfLifeGame extends Game {
	private double percentLiving;

	@Override
	public void setup() {
		setName("GameOfLife");
		File xmlFile = new File(getClass().getClassLoader().getResource(getName() + ".xml").getPath());
		setupBasicInfo(xmlFile);
		Element root = getRootElement(xmlFile);
		NodeList nList = navigateTo(root, "special");
		for (int i = 0; i < nList.getLength(); i++) {
			Node temp = nList.item(i);
			if (temp.getNodeType() == Node.ELEMENT_NODE && temp.getNodeName().equals("percentLiving")) {
				percentLiving = Double.parseDouble(((Element) temp).getTextContent());
			}
		}
	}

	@Override
	public State getRandomState(Cell cell) {
		double rand = Math.random();
		return rand < percentLiving ? new LiveState(cell) : new DeadState(cell);
	}

}
