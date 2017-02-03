package segregation;

import cellsociety_team18.Cell;
import javafx.scene.paint.Color;

/**
 * @author elliott
 * This class represents a Red population in Segregation.
 */
public class RedPopulation extends Agent {

	/**
	 * @param cell The cell that owns the state.
	 * @param satisfactionThreshold The percentage of neighbors that must be the same as the Agent in question.
	 */
	public RedPopulation(Cell cell, double satisfactionThreshold) {
		super(cell, satisfactionThreshold);
		setColor(Color.RED);
	}
	
}
