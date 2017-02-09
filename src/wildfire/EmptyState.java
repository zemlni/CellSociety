package wildfire;

import cellsociety_team18.State;
import javafx.scene.paint.Color;

/**
 * @author elliott
 * This cell represents an Empty state in the Wildfire game.
 */
public class EmptyState extends State {

	/**
	 * An Empty state is yellow.
	 */
	public EmptyState() {
		setColor(Color.LIGHTYELLOW);
	}

	/**
	 * An Empty state does not have rules to follow.
	 */
	@Override
	public void chooseState() {}

}
