package games.wildfire;

import cellsociety_team18.Cell;
import cellsociety_team18.Game;
import cellsociety_team18.State;
import javafx.scene.paint.Color;

/**
 * @author elliott This cell represents a Tree state in the Wildfire game.
 */
public class TreeState extends State {

	private Game game;

	/**
	 * @param game The state's game.
	 */
	public TreeState(Game game) {
		this.game = game;
		setColor(Color.web(game.getParameter("treeColor").toUpperCase()));
	}

	/**
	 * A Tree state becomes a Burning state with probability probCatch if one of
	 * its neighbors is burning.
	 */
	@Override
	public void chooseState() {
		for (Cell cell : getCell().getNeighbors()) {
			if (cell.getState() instanceof BurningState && Math.random() <= game.getDoubleParameter("fireChance")) {
				getCell().setNextState(new BurningState(game));
				return;
			}
		}
	}

}
