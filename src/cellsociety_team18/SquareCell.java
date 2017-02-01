package cellsociety_team18;

import java.util.ArrayList;

/**
 * @author elliott
 *
 */
public class SquareCell extends Cell {

	/**
	 * @param grid
	 */
	public SquareCell(Grid grid, int x, int y) {
		super(grid, x, y);
	}

	@Override
	public ArrayList<Cell> getNeighbors() {
		return getGrid().getNeighbors(getX(), getY());
	}

}
