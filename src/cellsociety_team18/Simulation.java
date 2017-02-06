package cellsociety_team18;

import java.util.HashMap;
import java.util.Map;

import game_of_life.GameOfLifeGame;
import segregation.SegregationGame;
import wator.WatorGame;
import wildfire.WildfireGame;

public class Simulation {
	//private Game[] games = { new WatorGame(), new SegregationGame(), new WildfireGame(), new GameOfLifeGame() };
	private Map<String, Game> games;
	//private Grid[] grids = { new RectGrid() };
	private Map<String, Grid> grids;
	private Grid grid;
	private Game game;

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
		
		//if more than one grid
		//grid = grids[gridType];
		//for now like this.
		grid = grids.get("Rect");
		grid.setup(game, size);
	}

	public void step() {
		for (Cell cell : grid.getCellsAsList()) {
			cell.chooseState();
		}
		for (Cell cell : grid.getCellsAsList()) {
			cell.updateState();
		}
	}

	public void shuffle() {
		grid.shuffle(game);
	}

	public Grid getGrid() {
		return grid;
	}
}
