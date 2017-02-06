package cellsociety_team18;

import game_of_life.GameOfLifeGame;
import segregation.SegregationGame;
import wator.WatorGame;
import wildfire.WildfireGame;

public class Simulation {
	private Game[] games = { new WatorGame(), new SegregationGame(), new WildfireGame(), new GameOfLifeGame() };
	private Grid[] grids = { new RectGrid() };
	private Grid grid;
	private Game game;

	public Simulation(int gameType, int gridType) {
		game = games[gameType];
		game.setup();
		grid = grids[gridType];
		grid.setup(game);
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
