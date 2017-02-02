package cellsociety_team18;

import javafx.scene.paint.Color;

public class DeadState extends GameOfLifeState {

	public DeadState(Cell cell) {
		super(cell);
		setColor(Color.WHITE);
	}

	@Override
	public void applyRules() {
		if (getLiveNeighbors() == 3) {
			getCell().setState(new LiveState(getCell()));
		}
	}

}
