package games.game_of_life;

import java.util.ArrayList;
import java.util.Arrays;

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
	 * @return a new state probabilistically.
	 */
	@Override
	public State getStateProbabilistically() {
		return Math.random() < getSettings().getDoubleParameter("percentLiving") ? new LiveState(this) : new DeadState(this);
	}
	
	/**
	 * @return a new state randomly.
	 */
	@Override
	public State getStateRandomly() {
		return getStateRandomly(new ArrayList<>(Arrays.asList(new LiveState(this), new DeadState(this))));
	}

}
