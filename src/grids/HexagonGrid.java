package grids;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cellsociety_team18.Cell;
import cellsociety_team18.Point;

/**
 * @author nikita Class represents a hexagonal grid
 */
public class HexagonGrid extends Grid {
	/**
	 * make a new hexagonal grid
	 * 
	 * @param type
	 *            grid type (toroidal or not)
	 */
	public HexagonGrid(String type) {
		super(type);
	}

	/**
	 * get neighbors not toroidal
	 * 
	 * @param center
	 *            point of cell of which to get neighbors
	 * @return list of neighboring cells
	 */
	@Override
	public List<Cell> getNeighborsBounded(Point center) {
		return getNeighbors(center, false);
	}

	/**
	 * get neighbors toroidal
	 * 
	 * @param center
	 *            point of cell of which to get neighbors
	 * @return list of neighboring cells
	 */
	@Override
	public List<Cell> getNeighborsToroidal(Point center) {
		return getNeighbors(center, true);
	}

	private List<Cell> getNeighbors(Point center, boolean toroidal) {
		List<Cell> neighbors = new ArrayList<Cell>();
		int x = (int) center.getX();
		int y = (int) center.getY();
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y; j <= y + 1; j++) {
				Point temp = null;
				if (toroidal)
					temp = new Point(Math.floorMod(i, getSize()), Math.floorMod(j, getSize()));
				else
					temp = new Point(i, j);

				if (!temp.equals(center))
					neighbors.add(getCell(temp));
			}
		}
		neighbors.add(getCell(new Point(center.getX(), center.getY() - 1)));
		neighbors.removeAll(Collections.singleton(null));
		while (neighbors.size() > getNumNeighbors())
			neighbors.remove(neighbors.size() - 1);
		return neighbors;
	}

}
