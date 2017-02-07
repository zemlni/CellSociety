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

/**
 * @author Nikita Zemlevskiy This is the superclass for all types of games. This
 *         class handles XML parsing as well as storing variables parsed in from
 *         XML.
 */
public abstract class Game {
	private static final DocumentBuilder DOCUMENT_BUILDER = getDocumentBuilder();
	private Map<String, String> basicInfo;
	private Map<String, String> specialInfo;
	private String name;
	private Element root;

	/**
	 * Setup the basic info of the game, such as its name and description and
	 * other variables. Also populates the maps of special information pertinent
	 * to each game subclass.
	 */
	public void setupBasicInfo() {
		File xmlFile = new File(getClass().getClassLoader().getResource(getName() + ".xml").getPath());
		root = getRootElement(xmlFile);
		basicInfo = new HashMap<String, String>();
		specialInfo = new HashMap<String, String>();

		populateMap(basicInfo, "info");
		populateMap(specialInfo, "special");
	}

	public Map<String, String> getSpecialInfo() {
		return specialInfo;
	}

	private void populateMap(Map<String, String> map, String section) {
		NodeList nList = navigateTo(root, section);
		for (int i = 0; i < nList.getLength(); i++) {
			Node temp = nList.item(i);
			if (temp.getNodeType() == Node.ELEMENT_NODE)
				map.put(temp.getNodeName(), ((Element) temp).getTextContent());
		}
	}

	private NodeList navigateTo(Element root, String elemName) {
		NodeList nList = root.getElementsByTagName(elemName);
		Node info = nList.item(0);
		nList = info.getChildNodes();
		return nList;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	/**
	 * Get a random state of this type of game.
	 * 
	 * @param cell
	 *            the cell which that state will belong to
	 * @return the random state requested
	 */
	public abstract State getRandomState(Cell cell);

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
	 * Game specific setup happens in here
	 */
	public abstract void setup();

	/**
	 * Return the description for this kind of game to be displayed to the user
	 * 
	 * @return description.
	 */
	public String getDescription() {
		return basicInfo.get("description");
	}

	public String getTitle() {
		return basicInfo.get("title");
	}
}
