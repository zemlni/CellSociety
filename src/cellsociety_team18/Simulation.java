package cellsociety_team18;

import java.util.ArrayList;

import javafx.scene.control.Cell;

public class Simulation {
	private Game[] games = {
			new WatorGame(),
			new SegregationGame(),
			new FireGame(),
			new LifeGame()
	};
	private String[] names = {
			"wator",
			"segregation",
			"fire",
			"life"
	};
	private Grid[] grids = {
			new RectGrid()
	};
	private Grid grid;
	private Game game;
	public Simulation(int gameType, int gridSize, int gridType){
		
		game = games[gameType];
		grid.setup();
		grid = grids[gridType];
		
	}
	public void step(){
		for (Object cell: grid.getCells())
			((Cell) cell).update();
	}
}
