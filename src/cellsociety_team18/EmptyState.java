package cellsociety_team18;

import javafx.scene.paint.Color;

public class EmptyState extends State {

	public EmptyState(Cell cell) {
		super(cell);
		setColor(Color.LIGHTYELLOW);
	}

	@Override
	public void applyRules() {}

}
