package cellsociety_team18;

import javafx.scene.paint.Color;

public class LiveState extends GameOfLifeState {

	public LiveState(Cell cell) {
		super(cell);
		setColor(Color.BLACK);
	}

	@Override
	public void applyRules() {
		if (getLiveNeighbors() < 2 || getLiveNeighbors() > 3) {
			getCell().setState(new DeadState(getCell()));
		}
	}
	
}
