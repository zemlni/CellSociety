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
	private Map<String, String> specialInfo;
	private String name;
	private Element root;

	public void setupBasicInfo() {
		File xmlFile = new File(getClass().getClassLoader().getResource(getName() + ".xml").getPath());
		root = getRootElement(xmlFile);
		basicInfo = new HashMap<String, String>();
		specialInfo = new HashMap<String, String>();
		
		populateMap(basicInfo, "info");
		populateMap(specialInfo, "special");
	}
	
	public Map<String, String> getSpecialInfo(){
		return specialInfo;
	}
	public void populateMap(Map<String, String> map, String section){
		NodeList nList = navigateTo(root, section);
		for (int i = 0; i < nList.getLength(); i++) {
			Node temp = nList.item(i);
			if (temp.getNodeType() == Node.ELEMENT_NODE)
				map.put(temp.getNodeName(), ((Element) temp).getTextContent());
		}
	}

	public NodeList navigateTo(Element root, String elemName) {
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

	public String getDescription() {
		return basicInfo.get("description");
	}
	public String getTitle(){
		return basicInfo.get("title");
	}
}
