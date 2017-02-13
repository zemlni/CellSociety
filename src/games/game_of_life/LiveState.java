package games.game_of_life;

import cellsociety_team18.Game;
import javafx.scene.paint.Color;

/**
 * @author elliott
 * A class representing a Live state in the Game of Life.
 */
public class LiveState extends GameOfLifeState {
	
	private Game game;

	/**
	 * @param game The state's game.
	 */
	public LiveState(Game game) {
		this.game = game;
		setColor(Color.web(game.getParameter("liveColor").toUpperCase()));
	}

	/**
	 * A Live cell becomes Dead if it has less than 2 neighbors, or more than 3.
	 */
	@Override
	public void chooseState() {
		if (getLiveNeighbors() < 2 || getLiveNeighbors() > 3) {
			getCell().setNextState(new DeadState(game));
		}
	}
	
}
