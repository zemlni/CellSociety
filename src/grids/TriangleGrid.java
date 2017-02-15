package grids;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cellsociety_team18.Cell;
import cellsociety_team18.Game;
import cellsociety_team18.Point;

/**
 * @author nikita Class represents a triangular grid
 */
public class TriangleGrid extends Grid {

	/**
	 * make a new triangular grid of grid type (eg toroidal vs not)
	 * 
	 * @param type
	 *            grid type
	 */
	public TriangleGrid(String type) {
		super(type);
	}

	/**
	 * return true if triangle is facing down
	 */
	private boolean downDecider(Point center) {
		return ((int) center.getX()) % 2 == ((int) center.getY() % 2);
	}

	/**
	 * get neighbors of a cell (toroidal or not toroidal)
	 * 
	 * @param center
	 *            point of cell of which to get neighbors
	 * @return list of neighboring cells
	 */
	@Override
	public List<Cell> getNeighbors(Point center) {
		List<Cell> neighbors = new ArrayList<Cell>();
		int x = (int) center.getX();
		int y = (int) center.getY();
		for (int i = x - 2; i <= x + 2; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				Point temp = null;
				if (getToroidal())
					temp = new Point(Math.floorMod(i, getSize()), Math.floorMod(j, getSize()));
				else
					temp = new Point(i, j);
				if (!temp.equals(center))
					neighbors.add(getCell(temp));
			}
		}
		if (downDecider(center)) {
			neighbors.remove(getCell(new Point(x - 2, y + 1)));
			neighbors.remove(getCell(new Point(x + 2, y + 1)));
		} else {
			neighbors.remove(getCell(new Point(x - 2, y - 1)));
			neighbors.remove(getCell(new Point(x + 2, y - 1)));
		}
		neighbors.removeAll(Collections.singleton(null));
		while (neighbors.size() > getNumNeighbors())
			neighbors.remove(neighbors.size() - 1);
		return neighbors;
	}
}
