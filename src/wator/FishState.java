package wator;

import java.util.List;

import cellsociety_team18.Cell;
import javafx.scene.paint.Color;

/**
 * @author elliott
 * This class represents a Fish in Wator.
 */
public class FishState extends AgentState {
	
	/**
	 * @param cell The cell that owns the state.
	 * @param reproductionTime The number of iterations before the fish reproduces.
	 * Fish states are green.
	 */
	public FishState(Cell cell, int reproductionTime) {
		super(cell, reproductionTime);
		setColor(Color.LAWNGREEN);
	}

	/**
	 * Move to a random Empty neighbor.
	 */
	@Override
	public void chooseState() {
		List<Cell> options = getOptions();
		move(options);
	}

}
