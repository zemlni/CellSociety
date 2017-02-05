package wator;

import cellsociety_team18.Cell;
import javafx.scene.paint.Color;

/**
 * @author elliott
 * This class represents a Fish in Wator.
 */
public class FishState extends AgentState {
	
	private int reproductionTime;
	
	/**
	 * @param cell The cell that owns the state.
	 * @param reproductionTime The number of iterations before the fish reproduces.
	 * Fish states are green.
	 */
	public FishState(Cell cell, int reproductionTime) {
		super(cell, reproductionTime);
		this.reproductionTime = reproductionTime;
		setColor(Color.LAWNGREEN);
	}

	/**
	 * Move to a random Empty neighbor.
	 */
	@Override
	public void chooseState() {
		moveTo(new FishState(null, reproductionTime));
	}

}
