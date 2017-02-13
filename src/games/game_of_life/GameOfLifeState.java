package games.game_of_life;

import cellsociety_team18.Cell;
import cellsociety_team18.State;

/**
 * @author elliott
 * This class represents a general Game of Life state.
 * Its main use is the obtaining of live neighbors.
 */
public abstract class GameOfLifeState extends State {

	/**
	 * @return The number of live neighbors of the state's cell.
	 */
	public int getLiveNeighbors() {
		int total = 0;
		for (Cell cell: getCell().getNeighbors()) {
			if (cell.getState() instanceof LiveState) {
				total++;
			}
		}
		return total;
	}

	@Override
	public abstract void chooseState();

}
