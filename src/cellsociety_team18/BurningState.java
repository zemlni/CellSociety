package cellsociety_team18;

import javafx.scene.paint.Color;

public class BurningState extends State {

	public BurningState(Cell cell) {
		super(cell);
		setColor(Color.ORANGERED);
	}

	@Override
	public void applyRules() {
		getCell().setState(new EmptyState(getCell()));
	}

}
