package games.game_of_life;

import cellsociety_team18.Game;
import javafx.scene.paint.Color;

/**
 * @author elliott
 * This class represents a Dead state in Game of Life.
 */
public class DeadState extends GameOfLifeState {

	private Game game;
	
	/**
	 * @param game The state's game.
	 */
	public DeadState(Game game) {
		this.game = game;
		setColor(Color.web(game.getSettings().getParameter("deadColor").toUpperCase()));
	}
	/**
	 * If a dead cell has three live neighbors, it becomes a live cell.
	 */
	@Override
	public void chooseState() {
		if (getLiveNeighbors() == 3) {
			getCell().setNextState(new LiveState(game));
		}
	}

}
