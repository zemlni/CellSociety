package cellsociety_team18;

import java.io.File;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import wildfire.*;

public class FireGame extends Game {
	private double fireChance;

	@Override
	public void setup() {
		setName("fire");
		File xmlFile = new File(getClass().getClassLoader().getResource(getName() + ".xml").getPath());
		setupBasicInfo(xmlFile);
		Element root = getRootElement(xmlFile);
		NodeList nList = navigateTo(root, "special");
		for (int i = 0; i < nList.getLength(); i++) {
			Node temp = nList.item(i);
			if (temp.getNodeType() == Node.ELEMENT_NODE && temp.getNodeName().equals("fireChance"))
				fireChance = Double.parseDouble(((Element) temp).getTextContent());

		}
	}

	@Override
	public State getRandomState(Cell cell) {
		int rand = (int)Math.random()*3;
		if (rand == 0)
			return new BurningState(cell);
		if (rand == 1)
			return new TreeState(cell, fireChance);
		return new EmptyState(cell);
	}

}