package cellsociety_team18;

import javafx.scene.paint.Color;

/**
 * @author elliott
 * This class represents the state of a cell.
 * A State has a color and a reference to the cell that owns it.
 */

public abstract class State {
	
	private Cell cell;
	private Color color;

	/**
	 * @param cell The cell that owns the State object.
	 */
	public State(Cell cell) {
		this.cell = cell;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Cell getCell() {
		return cell;
	}
	
	/**
	 * Obey the rules corresponding to this State.
	 */
	public abstract void applyRules();

}
