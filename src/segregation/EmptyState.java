package segregation;

import cellsociety_team18.State;
import javafx.scene.paint.Color;

/**
 * @author elliott
 * This class represents an Empty state in Segregation.
 */
public class EmptyState extends State {

	/**
	 * Empty state is white.
	 */
	public EmptyState() {
		setColor(Color.WHITE);
	}

	/**
	 * An Empty state has no rules to obey.
	 */
	@Override
	public void chooseState() {}

}
