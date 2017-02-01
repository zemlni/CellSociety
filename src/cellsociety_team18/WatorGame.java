package cellsociety_team18;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class WatorGame extends Game{
	private static final String NAME = "wator";
	private HashMap<String, ArrayList<Point>> locations;
	
	//lots of similar code with method in game. will need to extract method from there.
	public void setup(){
		File xmlFile = new File(getClass().getClassLoader().getResource(NAME + ".xml").getPath());
		HashMap<String, String> basicInfo = setupBasicInfo(xmlFile);

		locations = new HashMap<String, ArrayList<Point>>();
		Element root = getRootElement(xmlFile);
		NodeList nList = root.getElementsByTagName("locations");
		Node locs = nList.item(0);
		nList = locs.getChildNodes();
		for (int i = 0; i < nList.getLength(); i++){
			Node temp = nList.item(i);
			if (temp.getNodeType() == Node.ELEMENT_NODE){
				if (locations.get(temp.getNodeName()) == null)
					locations.put(temp.getNodeName(), new ArrayList<Point>());
					
				String[] text = ((Element) temp).getTextContent().split(",");
				
				Point p = new Point(Integer.parseInt(text[0].trim()), Integer.parseInt(text[1].trim()));
				locations.get(temp.getNodeName()).add(p);
			}
		}
		
	}
	public static void main(String[] args) {
		WatorGame game = new WatorGame();
		game.setup();
	}
}
