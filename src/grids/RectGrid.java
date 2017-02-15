package grids;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cellsociety_team18.Cell;
import cellsociety_team18.Point;

/**
 * @author Nikita Zemlevskiy This class is the implementation of a rectangular
 *         grid. It contains methods for accessing cells adjacent to a requested
 *         cell as well as setup that is specific to a rectangular grid.
 */
public class RectGrid extends Grid {

	public RectGrid(String type) {
		super(type);
	}

	/**
	 * Get list of neighbors, including diagonal ones, possibly toroidally
	 * 
	 * @param center
	 *            point of cell of which neighbors are to be returned.
	 * @return list of all neighbors, including diagonal ones.
	 */
	@Override
	public List<Cell> getNeighbors(Point center) {
		List<Cell> neighbors = new ArrayList<Cell>();
		int x = (int) center.getX();
		int y = (int) center.getY();
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if (!(i == x && j == y)) {
					if (getToroidal())
						neighbors.add(getCell(new Point(Math.floorMod(i, getSize()), Math.floorMod(j, getSize()))));
					else
						neighbors.add(getCell(new Point(i, j)));
				}
			}
		}
		neighbors.removeAll(Collections.singleton(null));
		return neighbors;
	}

}