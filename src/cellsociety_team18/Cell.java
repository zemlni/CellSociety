package cellsociety_team18;

import java.util.List;

import grids.Grid;
import javafx.scene.paint.Color;

/**
 * @author elliott
 * This class represents one cell.
 * The cell has a state, a next state, and a Point coordinate object.
 * The cell also has a reference to the grid that owns it.
 */
public class Cell {
	
	private Grid grid;
	private Point point;
	private State state;
	private State nextState;

	/**
	 * @param grid The grid that owns the cell.
	 * @param point The (X, Y) coordinates of the cell.
	 * @param state The cell's state.
	 */
	public Cell(Grid grid, Point point, State state) {
		this(grid, point);
		state.setCell(this);
		this.state = this.nextState = state;
	}
	
	/**
	 * @param grid The grid that owns the cell.
	 * @param point The (X, Y) coordinates of the cell.
	 */
	public Cell(Grid grid, Point point) {
		this.grid = grid;
		this.point = point;
	}
	
	public Color getColor() {
		return state.getColor();
	}
	
	public Grid getGrid() {
		return grid;
	}
	
	public Point getPoint() {
		return point;
	}
	
	/**
	 * @return A List of the cell's NSEW neighbors.
	 */
	public List<Cell> getNeighbors() {
		return getGrid().getNeighbors(point);
	}
	
	public State getState() {
		return state;
	}
	
	public void setState(State state) {
		state.setCell(this);
		this.state = state;
	}
	
	public State getNextState() {
		return nextState;
	}
	
	public void setNextState(State nextState) {
		nextState.setCell(this);
		this.nextState = nextState;
	}
	
	/**
	 * Choose the Cell's next state.
	 */
	public void chooseState() {
		state.chooseState();
	}
	
	/**
	 * Set the Cell's state to its next State.
	 */
	public void updateState() {
		state = nextState;
	}	

}
