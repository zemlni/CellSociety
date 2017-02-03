package GameOfLife;

import cellsociety_team18.Cell;
import javafx.scene.paint.Color;

/**
 * @author elliott
 * A class representing a Live state in the Game of Life.
 */
public class LiveState extends GameOfLifeState {

	public LiveState(Cell cell) {
		super(cell);
		setColor(Color.BLACK);
	}

	/**
	 * A Live cell becomes Dead if it has less than 2 neighbors, or more than 3.
	 */
	@Override
	public void chooseState() {
		if (getLiveNeighbors() < 2 || getLiveNeighbors() > 3) {
			getCell().setNextState(new DeadState(getCell()));
		}
	}
	
}
