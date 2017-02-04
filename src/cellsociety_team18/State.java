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
	
	public void setCell(Cell cell) {
		this.cell = cell;
	}
	
	/**
	 * Pick a state according to the rules for the current state.
	 */
	public abstract void chooseState();
	
}
