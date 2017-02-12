package games.game_of_life;

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
		setParameters("percentLiving", "deadColor", "liveColor");
	}
	
	@Override
	public void setStates() {
		getStates().put("Live", new LiveState(this));
		getStates().put("Dead", new DeadState(this));
	}

	/**
	 * Get a random GOF state.
	 * @return new random state.
	 */
	@Override
	public State getRandomState() {
		double rand = Math.random();
		return rand < getDoubleParameter("percentLiving") ? new LiveState(this) : new DeadState(this);
	}

}
