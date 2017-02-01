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
import java.util.HashMap;

public abstract class Game {
	private static final DocumentBuilder DOCUMENT_BUILDER = getDocumentBuilder();
	private HashMap<String, String> basicInfo;

	public HashMap<String, String> setupBasicInfo(File xmlFile) {
		Element root = getRootElement(xmlFile);
		basicInfo = new HashMap<String, String>();
		NodeList nList = root.getElementsByTagName("info");
		Node info = nList.item(0);
		nList = info.getChildNodes();
		for (int i = 0; i < nList.getLength(); i++) {
			Node temp = nList.item(i);
			if (temp.getNodeType() == Node.ELEMENT_NODE)
				basicInfo.put(temp.getNodeName(), ((Element) temp).getTextContent());
		}
		return basicInfo;
	}
	
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

	private String getTextValue(Element e, String tagName) {
		NodeList nodeList = e.getElementsByTagName(tagName);
		if (nodeList != null && nodeList.getLength() > 0) {
			return nodeList.item(0).getTextContent();
		} else {
			// FIXME: empty string or null, is it an error to not find the text
			// value?
			return "";
		}
	}

	public abstract void setup();
}
