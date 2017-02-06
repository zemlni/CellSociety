package cellsociety_team18;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Grid {

	Map<Point, Cell> cells;
	Game game;

	/*
	 * This is get neighbors. Whether diagonal ones are included in
	 * implementation is said in implementation May make more sense to return
	 * all neighbors in this method and make separate one for non diagonal,
	 * since that is specific to rectgrid.
	 */
	public abstract List<Cell> getNeighbors(Point center);

	public abstract List<Cell> getNeighborsDiagonal(Point center);

	public List<Cell> getCellsAsList() {
		return new ArrayList<Cell>(cells.values());
	}

	public Cell getCell(Point center) {
		return cells.get(center);
	}

	public abstract void setup(Game game);

	public void shuffle(Game game) {
		for (Cell cell : cells.values()) {
			cell.setNextState(game.getRandomState(cell));
			cell.updateState();
		}
	}
}
