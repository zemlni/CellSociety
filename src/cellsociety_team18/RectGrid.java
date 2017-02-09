package cellsociety_team18;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author Nikita Zemlevskiy This class is the implementation of a rectangular
 *         grid. It contains methods for accessing cells adjacent to a requested
 *         cell as well as setup that is specific to a rectangular grid.
 */
public class RectGrid extends Grid {


	/**
	 * Get list of neighbors, including diagonal ones.
	 * 
	 * @param center
	 *            point of cell of which neighbors are to be returned.
	 * @return list of all neighbors, including diagonal ones.
	 */
	public List<Cell> getNeighborsDiagonal(Point center) {
		List<Cell> neighbors = new ArrayList<Cell>();
		int x = (int)center.getX();
		int y = (int)center.getY();
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if (!(i == x && j == y))
					neighbors.add(getCell(new Point(i, j)));

			}
		}
		neighbors.removeAll(Collections.singleton(null));
		return neighbors;
	}

	/**
	 * Get list of neighbors not including diagonal ones.
	 * 
	 * @param center
	 *            point of cell of which neighbors are to be returned
	 * @return list of neighbors not including diagonal ones
	 */
	@Override
	public List<Cell> getNeighbors(Point center) {
		List<Cell> neighbors = new ArrayList<Cell>();
		int x = (int)center.getX();
		int y = (int)center.getY();
		neighbors.add(getCell(new Point(x - 1, y)));
		neighbors.add(getCell(new Point(x + 1, y)));
		neighbors.add(getCell(new Point(x, y - 1)));
		neighbors.add(getCell(new Point(x, y + 1)));
		neighbors.removeAll(Collections.singleton(null));
		return neighbors;
	}

}
