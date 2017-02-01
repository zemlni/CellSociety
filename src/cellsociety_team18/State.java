package cellsociety_team18;

import javafx.scene.paint.Color;

/**
 * @author elliott
 * This class represents the state of a cell.
 * A State has a color, a set of rules it obeys, and a reference to the cell that owns it.
 */

public class State {
	
	private Cell cell;
	private Color color;
	private Rules rules;

	/**
	 * @param cell The cell that owns the State object.
	 * @param color The color of the State.
	 * @param rules The rules the State follows.
	 */
	public State(Cell cell, Color color, Rules rules) {
		this.cell = cell;
		this.color = color;
		this.rules = rules;
	}
	
	public Color getColor() {
		return color;
	}
	
	/**
	 * Obey the rules corresponding to this State.
	 */
	public void applyRules() {
		rules.apply(cell);
	}

}
