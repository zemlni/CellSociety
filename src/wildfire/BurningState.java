package wildfire;

import cellsociety_team18.Cell;
import cellsociety_team18.State;
import javafx.scene.paint.Color;

/**
 * @author elliott
 * This cell represents a Burning state in the Wildfire game.
 */
public class BurningState extends State {

	/**
	 * @param cell The cell that owns this state.
	 * A Burning state is orange.
	 */
	public BurningState(Cell cell) {
		super(cell);
		setColor(Color.ORANGERED);
	}

	/**
	 * A Burning state becomes an Empty one after one time step.
	 */
	@Override
	public void chooseState() {
		getCell().setNextState(new EmptyState(getCell()));
	}

}
