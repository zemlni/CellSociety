package game_of_life;

import cellsociety_team18.Cell;
import cellsociety_team18.Game;
import cellsociety_team18.State;

/**
 * @author Nikita Zemlevskiy This class is the implementation of the Game of
 *         life game. It contains setup related to game of life and method to
 *         get a random game of life state.
 */
public class GameOfLifeGame extends Game {

	@Override
	public void setup() {
		setName("GameOfLife");
		setParameters("percentLiving");
		setupBasicInfo();
	}

	/**
	 * Get a random GOF state.
	 * 
	 * @param cell
	 *            the cell to which the new state will belong.
	 * @return new random state.
	 */
	@Override
	public State getRandomState(Cell cell) {
		double rand = Math.random();
		return rand < Double.parseDouble(getParameter("percentLiving")) ? new LiveState(cell) : new DeadState(cell);
	}

}
