package cellsociety_team18;

import javafx.scene.paint.Color;

public class TreeState extends State {
	
	private double probCatch;

	public TreeState(Cell cell, double probCatch) {
		super(cell);
		this.probCatch = probCatch;
		setColor(Color.FORESTGREEN);
	}

	@Override
	public void applyRules() {
		for (Cell cell: getCell().getNeighbors()) {
			if (cell.getState() instanceof BurningState && Math.random() <= probCatch) {
				getCell().setState(new BurningState(getCell()));
				return;
			}
		}
	}

}
