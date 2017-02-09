package cellsociety_team18;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TriangleGrid extends Grid {

	@Override
	public List<Cell> getNeighbors(Point center) {
		List<Cell> neighbors = new ArrayList<Cell>();
		int x = (int)center.getX();
		int y = (int)center.getY();
		for (int i = x - 2; i <= x + 2; i++){
			for (int j = y - 1; j <= y + 1; j++){
				Point temp = new Point(i, j);
				if (!temp.equals(center))
					neighbors.add(getCell(temp));
			}
		}
		if (downDecider(center)){
			neighbors.remove(getCell(new Point(x - 2, y + 1)));
			neighbors.remove(getCell(new Point(x + 2, y + 1)));
		}
		else {
			neighbors.remove(getCell(new Point(x - 2, y - 1)));
			neighbors.remove(getCell(new Point(x + 2, y - 1)));
		}
		neighbors.removeAll(Collections.singleton(null));
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

	/**
	 * Need to check what this returns, Dimensions might be bigger than we need
	 */
	public List<Point> getVertices(Point center) {
		List<Point> vertices = new ArrayList<Point>();
		int x = (int) center.getX();
		int y = (int) center.getY();

		for (int i = 0; i < 3; i++) {
			double angle = 120 * i;
			if (downDecider(center))
				angle += 120;
			angle = angle * Math.PI / 180;
			Point temp = new Point(x + Math.cos(angle), y + Math.sin(angle));
			vertices.add(temp);
		}

		return vertices;

	}
}
