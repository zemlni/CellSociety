package cellsociety_team18;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Game {
	private static final DocumentBuilder DOCUMENT_BUILDER = getDocumentBuilder();
	private Map<String, String> basicInfo;
	private Map<State, List<Point>> locations;
	private State[] states;
	private String name;

	/*
	 * TODO: fix duplicated code in here and in subclass
	 */
	public Map<State, List<Point>> getLocations() {
		return locations;
	}

	public void setupBasicInfo(File xmlFile) {
		Element root = getRootElement(xmlFile);
		basicInfo = new HashMap<String, String>();

		NodeList nList = navigateTo(root, "info");
		for (int i = 0; i < nList.getLength(); i++) {
			Node temp = nList.item(i);
			if (temp.getNodeType() == Node.ELEMENT_NODE)
				basicInfo.put(temp.getNodeName(), ((Element) temp).getTextContent());
		}
		locations = new HashMap<State, List<Point>>();

	}

	public NodeList navigateTo(Element root, String elemName) {
		NodeList nList = root.getElementsByTagName("info");
		Node info = nList.item(0);
		nList = info.getChildNodes();
		return nList;
	}

	public int getSize() {
		return Integer.parseInt(basicInfo.get("size"));
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setStates(State[] states) {
		this.states = states;
	}

	public abstract State getRandomState(Cell cell);

	private static DocumentBuilder getDocumentBuilder() {
		try {
			return DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new XMLException(e);
		}
	}

	public Element getRootElement(File xmlFile) {
		try {
			DOCUMENT_BUILDER.reset();
			Document xmlDocument = DOCUMENT_BUILDER.parse(xmlFile);
			return xmlDocument.getDocumentElement();
		} catch (SAXException | IOException e) {
			throw new XMLException(e);
		}
	}

	public abstract void setup();
}
