/**
 * 
 */
package wator;

import cellsociety_team18.State;
import javafx.scene.paint.Color;

/**
 * @author elliott
 * This class represents an empty state in Wator.
 */
public class EmptyState extends State {

	/**
	 * An Empty state is blue in this game.
	 */
	public EmptyState() {
		setColor(Color.DEEPSKYBLUE);
	}

	/**
	 * The empty state has no rules to obey in this game.
	 */
	@Override
	public void chooseState() {}

}
