package cellsociety_team18;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;



public class RectGrid extends Grid {
	private int size;
	@Override
	public void setup(int n, Map<String, List<Point>> locations) {
		size = n;

		for (String state: locations.keySet()){
			ArrayList<Point> points = locations.get(state);
			for (Point p : points){
				cells.put(p, new RectCell(this, p, state));
			}
		}
	}
	
	/* Contains diagonal neighbors
	 * */
	public List<Cell> getNeighborsDiagonal(Point center) {
		List<Cell> neighbors = new ArrayList<Cell>();
		int x = center.getX();
		int y = center.getY();
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= j + 1; j++) {
				neighbors.append(getCell(new Point(i, j)));
			}
		}
		neighbors.removeAll(Collections.singleton(null));
		return neighbors;
	}
	/* Does not contain diagonal neighbors
	 * */
	@Override
	public List<Cell> getNeighbors(Point center){
		List<Cell> neighbors = new ArrayList<Cell>();
		int x = center.getX();
		int y = center.getY();
		neighbors.add(getCell(new Point(x - 1, y)));
		neighbors.add(getCell(new Point(x + 1, y)));
		neighbors.add(getCell(new Point(x, y - 1)));
		neighbors.add(getCell(new Point(x, y + 1)));
		neighbors.removeAll(Collections.singleton(null));
		return neighbors;
	}
}
