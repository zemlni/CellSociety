package cellsociety_team18;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import games.wator.*;
import games.segregation.*;
import games.wildfire.*;
import games.game_of_life.*;
import games.sugarscape.*;
import games.slime.*;
import grids.*;

/**
 * @author Nikita Zemlevskiy This class controls the current simulation being
 *         held. Instance variables of the current game, the current grid, and
 *         all possible games and grids are maintained.
 */
public class Simulation {
	
	private Grid grid;
	private Game game;
	private List<Map<String, Number>> proportions = new ArrayList<Map<String, Number>>();
	private Settings data;
	private Map<String, Game> games = new HashMap<String, Game>() {
		{
			put("Wator", new WatorGame());
			put("Segregation", new SegregationGame());
			put("Wildfire", new WildfireGame());
			put("GameOfLife", new GameOfLifeGame());
			put("SugarScape", new SugarScapeGame());
			put("Slime", new SlimeGame());
		}
	};
	private Map<String, Grid> grids;

	/**
	 * Initialize a new simulation.
	 * 
	 * @param gameName
	 *            The name of the game being played
	 * @return new simulation with the specified parameters
	 */
	public Simulation(String gameName, String configurationName) {
		game = games.get(gameName);
		game.parseXML(gameName);
		game.setup();
		data = new Settings(XMLParser.parse(configurationName));
	}
	
	public Settings getSettings() {
		return data;
	}

	/**
	 * Randomize the order of cells stored in the grid.
	 */
	public void shuffle() {
		grid.shuffle(game);
	}

	public Grid getGrid() {
		return grid;
	}

	public Game getGame() {
		return game;
	}

	public List<Map<String, Number>> getProportions() {
		return proportions;
	}
	
	/**
	 * Sets up the grid for the simulation. 
	 * @param size The size of the grid to be created.
	 */
	public void setup() {
		game.setStates();
		game.setCellDistribution(data.getParameter("cellDistribution"));
		grids = new HashMap<String, Grid>() {
			{
				put("Square", new RectGrid(data.getParameter("gridEdge")));
				put("Triangle", new TriangleGrid(data.getParameter("gridEdge")));
				put("Hexagon", new HexagonGrid(data.getParameter("gridEdge")));
			}
		};
		grid = grids.get(data.getParameter("cellType"));
		grid.setup(game, data.getIntParameter("gridSize"), data.getIntParameter("numberOfNeighbors"));
	}
		
	private void recordProportions() {
		proportions.add(grid.getProportions(game.getStates()));
	}

	/**
	 * Reset the count of the proportion of states.
	 */
	public void clearProportions() {
		proportions = new ArrayList<Map<String, Number>>();
	}
	
	/**
	 * Step the simulation.
	 */
	public void step() {
		List<Cell> cells = grid.getCellsAsList();
		Collections.shuffle(cells);
		for (Cell cell : cells) {
			cell.chooseState();
		}
		for (Cell cell : cells) {
			cell.updateState();
		}
		recordProportions();
	}

}
