package wator;

import java.io.File;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import cellsociety_team18.Cell;
import cellsociety_team18.Game;
import cellsociety_team18.State;

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
		File xmlFile = new File(getClass().getClassLoader().getResource(getName() + ".xml").getPath());
		setupBasicInfo(xmlFile);
		Element root = getRootElement(xmlFile);
		NodeList nList = navigateTo(root, "special");
		for (int i = 0; i < nList.getLength(); i++) {
			Node temp = nList.item(i);
			if (temp.getNodeType() == Node.ELEMENT_NODE && temp.getNodeName().equals("percentFish"))
				percentFish = Double.parseDouble(((Element) temp).getTextContent());
			if (temp.getNodeType() == Node.ELEMENT_NODE && temp.getNodeName().equals("percentSharks"))
				percentSharks = Double.parseDouble(((Element) temp).getTextContent());
			if (temp.getNodeType() == Node.ELEMENT_NODE && temp.getNodeName().equals("sharkReprodTime"))
				sharkReprodTime = Integer.parseInt(((Element) temp).getTextContent());
			if (temp.getNodeType() == Node.ELEMENT_NODE && temp.getNodeName().equals("sharkStartEnergy"))
				sharkStartEnergy = Integer.parseInt(((Element) temp).getTextContent());
			if (temp.getNodeType() == Node.ELEMENT_NODE && temp.getNodeName().equals("sharkEnergyPerFish"))
				sharkEnergyPerFish = Integer.parseInt(((Element) temp).getTextContent());
			if (temp.getNodeType() == Node.ELEMENT_NODE && temp.getNodeName().equals("fishReprodTime"))
				fishReprodTime = Integer.parseInt(((Element) temp).getTextContent());
		}
	}

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
