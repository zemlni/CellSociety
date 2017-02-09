package cellsociety_team18;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Nikita Zemlevskiy This class is the superclass for grids. It contains
 *         all the cells in the grid as well as methods to access them and
 *         methods to get adjacent cells based on a cell's location.
 */
public abstract class Grid {

	private Map<Point, Cell> cells;
	private Game game;
	private int size;

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

	/**
	 * This is get neighbors. Whether diagonal ones are included in
	 * implementation is said in implementation May make more sense to return
	 * all neighbors in this method and make separate one for non diagonal,
	 * since that is specific to rectgrid.
	 * 
	 * @param center
	 *            point where cell is located
	 * @return list with all adjacent neighbors
	 */
	public abstract List<Cell> getNeighbors(Point center);

	/**
	 * same as above but get neighbors including diagonal ones
	 * 
	 * @param center
	 *            point where cell is located
	 * @return list with all adjacent neighbors
	 */
	public abstract List<Cell> getNeighborsDiagonal(Point center);
	/**TODO need to fix duplicated code across all getNeighbors type methods.
	 * */
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
	
	public int getSize(){
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
	 * Randomize states of all cells in the grid
	 * 
	 * @param game
	 *            the game that is being played in the grid.
	 */
	public void shuffle(Game game) {
		for (Cell cell : cells.values()) {
			cell.setNextState(game.getRandomState());
			cell.updateState();
		}
	}
	
	public Map<String, Double> getProportions(Map<String, State> states) {
		HashMap<String, Double> result = new HashMap<String, Double>();
		for (Entry<String, State> entry : states.entrySet()) {
			result.put(entry.getKey(), getProportion(entry.getValue()));
		}
		return result;
	}
	
	private Double getProportion(State state) {
		int count = 0;
		for (Cell cell: cells.values()) {
			if (cell.getState().getClass().equals(state.getClass())) {
				count++;
			}
		}
		return ((double) count) / cells.size();
	}
	
}
