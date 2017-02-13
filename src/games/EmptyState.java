/**
 * 
 */
package games;

import cellsociety_team18.Game;
import cellsociety_team18.State;
import javafx.scene.paint.Color;

/**
 * @author elliott
 * This class represents an empty state - in every game.
 */
public class EmptyState extends State {

	/**
	 * @return An empty state.
	 */
	public EmptyState(Game game) {
		setColor(Color.web(game.getSettings().getParameter("emptyColor").toUpperCase()));
	}

	/**
	 * The empty state has no rules to obey in these games.
	 */
	@Override
	public void chooseState() {}

}
