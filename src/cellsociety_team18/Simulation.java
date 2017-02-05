package cellsociety_team18;

public class Simulation {
	private Game[] games = { new WatorGame(), new SegregationGame(), new FireGame(), new GameOfLifeGame() };
	private Grid[] grids = { new RectGrid() };
	private Grid grid;
	private Game game;

	public Simulation(int gameType, int gridType) {
		game = games[gameType];
		game.setup();
		grid = grids[gridType];
		grid.setup(game.getSize(), game.getLocations());
	}

	public Grid step() {
		for (Cell cell : grid.getCellsAsList())
			cell.updateState();
		return grid;
	}

	public void shuffle() {
		grid.shuffle(game);
	}

}
