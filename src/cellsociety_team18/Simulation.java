package cellsociety_team18;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game_of_life.GameOfLifeGame;
import segregation.SegregationGame;
import wator.WatorGame;
import wildfire.WildfireGame;

/**
 * @author Nikita Zemlevskiy This class controls the current simulation being
 *         held. Instance variables of the current game, the current grid, and
 *         all possible games and grids are maintained.
 */
public class Simulation {
	private Map<String, Game> games;
	private Map<String, Grid> grids;
	private Grid grid;
	private Game game;

	/**
	 * Initialize a new simulation.
	 * 
	 * @param gameName
	 *            The name of the game being played
	 * @param size
	 *            the size in cells across that the simulation should be.
	 * @return new simulation with the specified parameters
	 */
	public Simulation(String gameName, int size) {
		games = new HashMap<String, Game>();
		games.put("Wator", new WatorGame());
		games.put("Segregation", new SegregationGame());
		games.put("Wildfire", new WildfireGame());
		games.put("GameOfLife", new GameOfLifeGame());
		game = games.get(gameName);
		grids = new HashMap<String, Grid>();
		grids.put("Rect", new RectGrid());

		game.setup();

		// if more than one grid
		// grid = grids[gridType];
		// for now like this.
		grid = grids.get("Rect");
		grid.setup(game, size);
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
}
