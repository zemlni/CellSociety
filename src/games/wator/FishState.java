package games.wator;

import cellsociety_team18.Game;
import javafx.scene.paint.Color;

/**
 * @author elliott
 * This class represents a Fish in Wator.
 */
public class FishState extends AgentState {
	
	private Game game;
	
	/**
	 * @param game The state's owner.
	 */
	public FishState(Game game) {
		super(game);
		this.game = game;
		setColor(Color.web(game.getParameter("fishColor").toUpperCase()));
		setReproductionTime(game.getIntParameter("fishReprodTime"));
	}

	/**
	 * Move to a random Empty neighbor.
	 */
	@Override
	public void chooseState() {
		moveTo(new FishState(game));
	}

}
