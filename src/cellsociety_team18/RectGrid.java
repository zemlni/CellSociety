package cellsociety_team18;

import java.util.ArrayList;
import java.util.Collections;

import javafx.scene.control.Cell;

public class RectGrid extends Grid {
	private int size;

	public void setupGrid(int n) {
		size = n;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Point p = new Point(i, j);
				this.cells.put(p, new RectCell());
			}
		}
	}

	/* Contains diagonal neighbors
	 * */
	public ArrayList<Cell> getNeighborsDiagonal(Point center) {
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
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
	@Override
	public ArrayList<Cell> getNeighbors(Point center){
		ArrayList<Cell<T>> neighbors = new ArrayList<Cell>();
		int x = center.getX();
		int y = center.getY();
		neighbors.add(getCell(new Point(x - 1, y)));
		neighbors.add(getCell(new Point(x + 1, y)));
		neighbors.add(getCell(new Point(x, y - 1)));
		neighbors.add(getCell(new Point(x, y + 1)));
		neighbors.removeAll(Collections.singleton(null));
	}
}
