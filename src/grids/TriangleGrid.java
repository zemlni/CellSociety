package grids;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cellsociety_team18.Cell;
import cellsociety_team18.Point;

public class TriangleGrid extends Grid {

	@Override
	public List<Cell> getNeighbors(Point center) {
		List<Cell> neighbors = new ArrayList<Cell>();
		int x = (int) center.getX();
		int y = (int) center.getY();
		for (int i = x - 2; i <= x + 2; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				Point temp = new Point(i, j);
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

	/**
	 * remove this in the future
	 */
	@Override
	public List<Cell> getNeighborsDiagonal(Point center) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * return true if triangle is facing down
	 */
	private boolean downDecider(Point center) {
		return ((int) center.getX()) % 2 == ((int) center.getY() % 2);
	}

	@Override
	public List<Cell> getNeighborsToroidal(Point center) {
		List<Cell> neighbors = new ArrayList<Cell>();
		int x = (int) center.getX();
		int y = (int) center.getY();
		for (int i = x - 2; i <= x + 2; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				Point temp = new Point(i % getSize(), j % getSize());
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
