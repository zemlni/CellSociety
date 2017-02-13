package grids;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cellsociety_team18.Cell;
import cellsociety_team18.Point;

public class HexagonGrid extends Grid {
	
	public HexagonGrid(String type) {
		super(type);
	}

	@Override
	public List<Cell> getNeighborsBounded(Point center) {
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
		while (neighbors.size() > getNumNeighbors())
			neighbors.remove(neighbors.size() - 1);
		return neighbors;
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
		while (neighbors.size() > getNumNeighbors())
			neighbors.remove(neighbors.size() - 1);
		return neighbors;
	}

}
