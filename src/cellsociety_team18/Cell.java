package cellsociety_team18;

import java.util.ArrayList;

import javafx.scene.paint.Color;

/**
 * @author elliott
 * This class represents one cell.
 * The cell has a State and a Color.
 *
 */
public abstract class Cell {
	
	private Grid grid;
	private State state;
	
	private int x;
	private int y;

	/**
	 * @param grid The grid that owns the cell.
	 */
	public Cell(Grid grid, int x, int y) {
		this.grid = grid;
		this.x = x;
		this.y = y;
	}
	
	public Color getColor() {
		return state.getColor();
	}
	
	public Grid getGrid() {
		return grid;
	}
	
	public State getState() {
		return state;
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
		
	/**
	 * Update the Cell's State.
	 */
	public void updateState() {
		state.applyRules();
	}
	
	public ArrayList<Cell> getNeighbors() {
		return getGrid().getNeighbors(getX(), getY());
	}

}
