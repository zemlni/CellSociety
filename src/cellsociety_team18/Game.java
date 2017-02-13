package cellsociety_team18;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import grids.Grid;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Nikita Zemlevskiy This is the superclass for all types of games. This
 *         class handles XML parsing as well as storing variables parsed in from
 *         XML.
 */
public abstract class Game {

	private static final DocumentBuilder DOCUMENT_BUILDER = getDocumentBuilder();
	private Map<String, String> data;
	private HashMap<String, State> states = new HashMap<String, State>();
	private ArrayList<String> parameters = new ArrayList<String>();

	/**
	 * Setup the basic info of the game, such as its name and description and
	 * other variables. Also populates the maps of special information pertinent
	 * to each game subclass.
	 */
	public void parseXML(String gameName) {
		File xmlFile = new File(getClass().getClassLoader().getResource(gameName + ".xml").getPath());
		data = populateMap(getRootElement(xmlFile));
	}

	public void setParameter(String parameter, String value) {
		data.put(parameter, value);
	}

	public String getParameter(String parameter) {
		return data.get(parameter);
	}

	public double getDoubleParameter(String parameter) {
		return Double.parseDouble(data.get(parameter));
	}

	public int getIntParameter(String parameter) {
		return Integer.parseInt(data.get(parameter));
	}

	public HashMap<String, String> getParametersAndValues() {
		HashMap<String, String> result = new HashMap<String, String>();
		for (String parameter : parameters) {
			result.put(parameter, getParameter(parameter));
		}
		return result;
	}

	public void setParameters(String... objects) {
		for (String parameter : objects) {
			parameters.add(parameter);
		}
	}

	public Map<String, State> getStates() {
		return states;
	}

	private Map<String, String> populateMap(Element root) {
		HashMap<String, String> map = new HashMap<String, String>();
		NodeList nList = root.getChildNodes();
		for (int i = 0; i < nList.getLength(); i++) {
			Node temp = nList.item(i);
			if (temp.getNodeType() == Node.ELEMENT_NODE)
				map.put(temp.getNodeName(), ((Element) temp).getTextContent());
		}
		return map;
	}

	/**
	 * Get a random state of this type of game.
	 * @return the random state requested
	 */
	public abstract State getRandomState();

	private static DocumentBuilder getDocumentBuilder() {
		try {
			return DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new XMLException(e);
		}
	}

	private Element getRootElement(File xmlFile) {
		try {
			DOCUMENT_BUILDER.reset();
			Document xmlDocument = DOCUMENT_BUILDER.parse(xmlFile);
			return xmlDocument.getDocumentElement();
		} catch (SAXException | IOException e) {
			throw new XMLException(e);
		}
	}

	/**
	 * Game specific setup happens in here.
	 */
	public abstract void setup();

	/**
	 * Create a Map of a game's states and their names.
	 */
	public abstract void setStates();

	/**
	 * Return the description for this kind of game.
	 * 
	 * @return the game's description.
	 */
	public String getDescription() {
		return data.get("description");
	}

	/**
	 * Return the title for this kind of game.
	 * 
	 * @return the game's title.
	 */
	public String getTitle() {
		return data.get("title");
	}
}
