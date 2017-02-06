package cellsociety_team18;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RectGrid extends Grid {

	

	@Override
	public void setup(Game game, int size) {
		setCells(new HashMap<Point, Cell>());
		setSize(size);
		setGame(game);
		//int sqrt = (int)Math.sqrt(size);
		for (int i = 0; i < size; i++){
			for (int j = 0; j < size; j++){
				Point p = new Point(i, j);
				Cell cell = new SquareCell(this, p, null);
				cell.setNextState(game.getRandomState(cell));
				cell.updateState();
				getCells().put(p, cell);
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
