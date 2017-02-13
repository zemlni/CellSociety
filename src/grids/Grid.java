package grids;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cellsociety_team18.Cell;
import cellsociety_team18.Game;
import cellsociety_team18.Point;
import cellsociety_team18.State;

/**
 * @author Nikita Zemlevskiy This class is the superclass for grids. It contains
 *         all the cells in the grid as well as methods to access them and
 *         methods to get adjacent cells based on a cell's location.
 */
public abstract class Grid {

	private Map<Point, Cell> cells;
	private Game game;
	private String type;
	private int size;
	private int numNeighbors;

	public Grid(String type) {
		this.type = type;
	}

	public void setNumNeighbors(int num) {
		this.numNeighbors = num;
	}

	public int getNumNeighbors() {
		return numNeighbors;
	}

	public Map<Point, Cell> getCells() {
		return cells;
	}

	public void setCells(Map<Point, Cell> cells) {
		this.cells = cells;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Game getGame() {
		return game;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<Cell> getNeighbors(Point center) {
		if (type.equals("Bounded")) {
			return getNeighborsBounded(center);
		} else {
			return getNeighborsToroidal(center);
		}
	}

	/**
	 * get neighbors not toroidal
	 * 
	 * @param center
	 *            point of cell of which to get neighbors
	 * @return list of neighboring cells
	 */
	public abstract List<Cell> getNeighborsBounded(Point center);

	/**
	 * get neighbors toroidal
	 * 
	 * @param center
	 *            point of cell of which to get neighbors
	 * @return list of neighboring cells
	 */
	public abstract List<Cell> getNeighborsToroidal(Point center);

	/**
	 * Return list of all cells.
	 * 
	 * @return list with all cells in grid
	 */
	public List<Cell> getCellsAsList() {
		return new ArrayList<Cell>(cells.values());
	}

	/**
	 * Get cell at point
	 * 
	 * @return cell at requested point
	 */
	public Cell getCell(Point center) {
		return cells.get(center);
	}

	public int getSize() {
		return size;
	}

	/**
	 * Grid specific setups
	 * 
	 * @param game
	 *            the game that will be played in this grid
	 * @param size
	 *            size of the grid
	 */
	public void setup(Game game, int size, int numNeighbors) {
		setCells(new HashMap<Point, Cell>());
		setSize(size);
		setGame(game);
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Point p = new Point(i, j);
				Cell cell = new Cell(this, p);
				randomize(game, cell);
				getCells().put(p, cell);
			}
		}
		this.numNeighbors = numNeighbors;
	}

	public void randomize(Game game, Cell cell) {
		cell.setNextState(game.getState());
		cell.updateState();
		cell.getState().setCell(cell);
	}

	/**
	 * Randomize states of all cells in the grid
	 * 
	 * @param game
	 *            the game that is being played in the grid.
	 */
	public void shuffle(Game game) {
		for (Cell cell : cells.values()) {
			randomize(game, cell);
		}
	}

	public Map<String, Number> getProportions(Map<String, State> states) {
		HashMap<String, Number> result = new HashMap<String, Number>();
		for (Entry<String, State> entry : states.entrySet()) {
			result.put(entry.getKey(), getProportion(entry.getKey(), entry.getValue()));
		}
		return result;
	}

	private int getProportion(String key, State state) {
		int count = 0;
		for (Cell cell : cells.values()) {
			if (cell.getState().getClass().equals(state.getClass())) {
				count++;
			}
		}
		return count;
	}

	public void setup() {
		// TODO Auto-generated method stub

	}

}
