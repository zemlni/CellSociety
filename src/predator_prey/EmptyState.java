/**
 * 
 */
package predator_prey;

import cellsociety_team18.Cell;
import cellsociety_team18.State;
import javafx.scene.paint.Color;

/**
 * @author elliott
 * This class represents an empty state in Predator/Prey.
 */
public class EmptyState extends State {

	/**
	 * @param cell The cell that owns the state.
	 * An Empty state is blue in this game.
	 */
	public EmptyState(Cell cell) {
		super(cell);
		setColor(Color.DEEPSKYBLUE);
	}

	/**
	 * The empty state has no rules to obey in this game.
	 */
	@Override
	public void chooseState() {}

}
