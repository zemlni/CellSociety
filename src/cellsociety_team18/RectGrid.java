package cellsociety_team18;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author Nikita Zemlevskiy This class is the implementation of a rectangular
 *         grid. It contains methods for accessing cells adjacent to a requested
 *         cell as well as setup that is specific to a rectangular grid.
 */
public class RectGrid extends Grid {

	/**
	 * Setup related to rectangular grid.
	 * 
	 * @param game
	 *            the game to be played in this grid
	 * @param size
	 *            the size of the grid in one dimension
	 */
	@Override
	public void setup(Game game, int size) {
		setCells(new HashMap<Point, Cell>());
		setSize(size);
		setGame(game);
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Point p = new Point(i, j);
				Cell cell = new Cell(this, p, null);
				cell.setNextState(game.getRandomState(cell));
				cell.updateState();
				getCells().put(p, cell);
			}
		}
	}

	/**
	 * Get list of neighbors, including diagonal ones.
	 * 
	 * @param center
	 *            point of cell of which neighbors are to be returned.
	 * @return list of all neighbors, including diagonal ones.
	 */
	public List<Cell> getNeighborsDiagonal(Point center) {
		List<Cell> neighbors = new ArrayList<Cell>();
		int x = (int)center.getX();
		int y = (int)center.getY();
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if (!(i == x && j == y))
					neighbors.add(getCell(new Point(i, j)));

			}
		}
		neighbors.removeAll(Collections.singleton(null));
		return neighbors;
	}

	/**
	 * Get list of neighbors not including diagonal ones.
	 * 
	 * @param center
	 *            point of cell of which neighbors are to be returned
	 * @return list of neighbors not including diagonal ones
	 */
	@Override
	public List<Cell> getNeighbors(Point center) {
		List<Cell> neighbors = new ArrayList<Cell>();
		int x = (int)center.getX();
		int y = (int)center.getY();
		neighbors.add(getCell(new Point(x - 1, y)));
		neighbors.add(getCell(new Point(x + 1, y)));
		neighbors.add(getCell(new Point(x, y - 1)));
		neighbors.add(getCell(new Point(x, y + 1)));
		neighbors.removeAll(Collections.singleton(null));
		return neighbors;
	}

}
