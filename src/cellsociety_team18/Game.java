package cellsociety_team18;

import grids.Grid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import games.EmptyState;

/**
 * @author Nikita Zemlevskiy This is the superclass for all types of games. This
 *         class handles XML parsing as well as storing variables parsed in from
 *         XML.
 */
public abstract class Game {

	private Map<String, String> data;
	private HashMap<String, State> states = new HashMap<String, State>();
	private ArrayList<String> parameters = new ArrayList<String>();
	private String cellDistribution = "";

	/**
	 * Setup the basic info of the game, such as its name and description and
	 * other variables. Also populates the maps of special information pertinent
	 * to each game subclass.
	 */
	public void parseXML(String gameName) {
		data = XMLParser.parse(gameName);
	}
	
	public String getCellDistribution() {
		return cellDistribution;
	}
	
	public void setCellDistribution(String cellDistribution) {
		this.cellDistribution = cellDistribution;
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

	/**
	 * Get a random state of this type of game.
	 * @return the random state requested
	 */
	public State getState() {
		if (cellDistribution.equals("Probabilistic")) {
			return getStateProbabilistically();
		}
		/*else if (cellDistribution.equals("From List")) {
			return getStateFromList();
		}*/
		else {
			return getStateRandomly();
		}
	}
	
	public abstract State getStateRandomly();
	
	public State getStateRandomly(List<State> options) {
		return options.get(((int) (Math.random() * options.size())));
	}
	
	public abstract State getStateProbabilistically();
	
	public State getStateFromPercentages(List<Double> percentages, List<State> states) {
		double rand = Math.random();
		double total = 0;
		for (double percentage: percentages) {
			total += percentage;
			if (rand <= total) {
				return states.get(percentages.indexOf(percentage));
			}
		}
		return states.get(states.size() - 1);
	}
	
	//public abstract State getStateFromList();

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
