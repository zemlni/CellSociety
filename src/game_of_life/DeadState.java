package game_of_life;

import cellsociety_team18.Cell;
import javafx.scene.paint.Color;

/**
 * @author elliott
 * This class represents a Dead state in Game of Life.
 */
public class DeadState extends GameOfLifeState {

	/**
	 * @param cell The owner of this state.
	 * Dead cells are white.
	 */
	public DeadState(Cell cell) {
		super(cell);
		setColor(Color.WHITE);
	}

	/**
	 * If a dead cell has three live neighbors, it becomes a live cell.
	 */
	@Override
	public void chooseState() {
		if (getLiveNeighbors() == 3) {
			getCell().setNextState(new LiveState(getCell()));
		}
	}

}
