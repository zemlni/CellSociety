package Wildfire;

import cellsociety_team18.Cell;
import cellsociety_team18.State;
import javafx.scene.paint.Color;

/**
 * @author elliott This cell represents a Tree state in the Wildfire game.
 */
public class TreeState extends State {

	private double probCatch;

	/**
	 * @param cell The cell that owns this state. A Tree state is green.
	 */
	public TreeState(Cell cell, double probCatch) {
		super(cell);
		this.probCatch = probCatch;
		setColor(Color.FORESTGREEN);
	}

	/**
	 * A Tree state becomes a Burning state with probability probCatch if one of
	 * its neighbors is burning.
	 */
	@Override
	public void chooseState() {
		for (Cell cell : getCell().getNeighbors()) {
			if (cell.getState() instanceof BurningState && Math.random() <= probCatch) {
				getCell().setNextState(new BurningState(getCell()));
				return;
			}
		}
	}

}
