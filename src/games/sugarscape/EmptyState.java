package games.sugarscape;

import cellsociety_team18.State;
import javafx.scene.paint.Color;

public class EmptyState extends State {

	/**
	 * Empty state is white and is transparent.
	 */
	public EmptyState() {
		updateColor();
	}

	/**
	 * An Empty state has no rules to obey.
	 */
	@Override
	public void chooseState() {
		updateColor();
	}

	private void updateColor() {
		setColor(((SugarScapeCell) getCell()).getCellColor());
	}

}
