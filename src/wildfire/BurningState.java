package wildfire;

import cellsociety_team18.State;
import javafx.scene.paint.Color;

/**
 * @author elliott
 * This cell represents a Burning state in the Wildfire game.
 */
public class BurningState extends State {

	/**
	 * A Burning state is red.
	 */
	public BurningState() {
		setColor(Color.RED);
	}

	/**
	 * A Burning state becomes an Empty one after one time step.
	 */
	@Override
	public void chooseState() {
		getCell().setNextState(new EmptyState());
	}

}
