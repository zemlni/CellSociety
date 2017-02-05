package cellsociety_team18;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RectGrid extends Grid {

	private int size;

	@Override
	public void setup(int size, Map<State, List<Point>> locations, Game game) {
		this.cells = new HashMap<Point, Cell>();
		this.size = size;
		this.game = game;
		int sqrt = (int)Math.sqrt(size);
		for (int i = 0; i < sqrt; i++){
			for (int j = 0; j < sqrt; j++){
				Point p = new Point(i, j);
				Cell cell = new SquareCell(this, p, null);
				cell.setNextState(game.getRandomState(cell));
				cell.updateState();
				cells.put(p, cell);
			}
		}
	}

	/*
	 * Contains diagonal neighbors
	 */
	public List<Cell> getNeighborsDiagonal(Point center) {
		List<Cell> neighbors = new ArrayList<Cell>();
		int x = center.getX();
		int y = center.getY();
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if (!(i == x && j == y))
					neighbors.add(getCell(new Point(i, j)));
				
			}
		}
		neighbors.removeAll(Collections.singleton(null));
		return neighbors;
	}

	/*
	 * Does not contain diagonal neighbors
	 */
	@Override
	public List<Cell> getNeighbors(Point center) {
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
