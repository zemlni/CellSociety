package games.wildfire;

import cellsociety_team18.Game;
import cellsociety_team18.State;
import games.EmptyState;
import javafx.scene.paint.Color;

/**
 * @author elliott
 * This cell represents a Burning state in the Wildfire game.
 */
public class BurningState extends State {
	
	private Game game;

	/**
	 * @return A burning state.
	 */
	public BurningState(Game game) {
		this.game = game;
		setColor(Color.web(game.getParameter("burningColor").toUpperCase()));
	}

	/**
	 * A Burning state becomes an Empty one after one time step.
	 */
	@Override
	public void chooseState() {
		getCell().setNextState(new EmptyState(game));
	}

}
