package GameOfLife;

import cellsociety_team18.Cell;
import cellsociety_team18.State;

/**
 * @author elliott
 * This class represents a general Game of Life state.
 * Its main use is the obtaining of live neighbors.
 */
public abstract class GameOfLifeState extends State {

	/**
	 * @param cell The cell that owns the state.
	 */
	public GameOfLifeState(Cell cell) {
		super(cell);
	}
	
	/**
	 * @return The number of live neighbors of the state's cell..
	 */
	public int getLiveNeighbors() {
		int total = 0;
		for (Cell cell: getCell().getNeighborsDiagonal()) {
			if (cell.getState() instanceof LiveState) {
				total++;
			}
		}
		return total;
	}

	@Override
	public abstract void chooseState();

}
