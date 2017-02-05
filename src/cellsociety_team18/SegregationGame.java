package cellsociety_team18;

import java.io.File;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import segregation.*;

public class SegregationGame extends Game {
	private double satisfaction;
	private double percentRed;
	private double percentBlue;

	@Override
	public void setup() {
		setName("segregation");
		File xmlFile = new File(getClass().getClassLoader().getResource(getName() + ".xml").getPath());
		setupBasicInfo(xmlFile);
		Element root = getRootElement(xmlFile);
		NodeList nList = navigateTo(root, "special");
		for (int i = 0; i < nList.getLength(); i++) {
			Node temp = nList.item(i);
			if (temp.getNodeType() == Node.ELEMENT_NODE && temp.getNodeName().equals("satisfaction"))
				satisfaction = Double.parseDouble(((Element) temp).getTextContent());
			if (temp.getNodeType() == Node.ELEMENT_NODE && temp.getNodeName().equals("percentRed"))
				percentRed = Double.parseDouble(((Element) temp).getTextContent());
			if (temp.getNodeType() == Node.ELEMENT_NODE && temp.getNodeName().equals("percentBlue"))
				percentBlue = Double.parseDouble(((Element) temp).getTextContent());
		}

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
