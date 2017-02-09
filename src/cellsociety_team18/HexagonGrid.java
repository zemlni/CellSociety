package cellsociety_team18;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class HexagonGrid extends Grid {

	@Override
	public List<Cell> getNeighbors(Point center) {
		List<Cell> neighbors = new ArrayList<Cell>();
		int x = (int) center.getX();
		int y = (int) center.getY();
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y; j <= y + 1; j++) {
				Point temp = new Point(i, j);
				if (!temp.equals(center))
					neighbors.add(getCell(temp));
			}
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
	/**Might need to fix, need to check what this returns. 
	 * Dimensions might be bigger than we need
	 * */
	public List<Point> getVertices(Point center) {
		List<Point> vertices = new ArrayList<Point>();
		int x = (int) center.getX();
		int y = (int) center.getY();
		for (int i = 0; i < 6; i++) {
			double angle = Math.PI * 60 * i / 180;
			Point temp = new Point(x + Math.cos(angle), y + Math.sin(angle));
			vertices.add(temp);
		}
		return vertices;
	}

}
