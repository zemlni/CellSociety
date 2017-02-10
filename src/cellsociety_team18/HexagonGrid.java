package cellsociety_team18;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HexagonGrid extends Grid {

	@Override
	public List<Cell> getNeighbors(Point center) {
		List<Cell> neighbors = new ArrayList<Cell>();
		int x = (int) center.getX();
		int y = (int) center.getY();
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				Point temp = new Point(i, j);
				if (!temp.equals(center))
					neighbors.add(getCell(temp));
			}
		}
		if (Math.floorMod(x, 2) == 0) {
			neighbors.remove(getCell(new Point(x - 1, y - 1)));
			neighbors.remove(getCell(new Point(x + 1, y - 1)));
		} else {
			neighbors.remove(getCell(new Point(x - 1, y + 1)));
			neighbors.remove(getCell(new Point(x + 1, y + 1)));
		}
		neighbors.add(getCell(new Point(center.getX(), center.getY() - 1)));
		neighbors.removeAll(Collections.singleton(null));
		return neighbors;
	}

	/**
	 * Method doesn't make sense anymore, need to remove
	 */
	@Override
	public List<Cell> getNeighborsDiagonal(Point center) {
		// TODO: remove this from abstract class
		return null;
	}

	@Override
	public List<Cell> getNeighborsToroidal(Point center) {
		List<Cell> neighbors = new ArrayList<Cell>();
		int x = (int) center.getX();
		int y = (int) center.getY();
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y; j <= y + 1; j++) {
				Point temp = new Point(i % getSize(), j % getSize());
				if (!temp.equals(center))
					neighbors.add(getCell(temp));
			}
		}
		neighbors.add(getCell(new Point(center.getX(), center.getY() - 1)));
		neighbors.removeAll(Collections.singleton(null));
		return neighbors;
	}

}
