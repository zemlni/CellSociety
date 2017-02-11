package sugarscape;

import cellsociety_team18.Cell;
import cellsociety_team18.Point;
import grids.Grid;
import javafx.scene.paint.Color;

public class SugarScapeCell extends Cell {
	private int curSugar;
	private int maxSugar;
	private int curTicks;
	private int interval;

	public SugarScapeCell(Grid grid, Point point) {
		super(grid, point);
		// TODO Auto-generated constructor stub
	}

	public void setParameters(int maxSugar, int interval) {
		this.curSugar = maxSugar;
		this.maxSugar = maxSugar;
		this.interval = interval;
	}

	public int getCurSugar() {
		return curSugar;
	}

	public void updateSelf() {
		curTicks++;
		if (curTicks >= interval) {
			curTicks = 0;
			if (curSugar < maxSugar)
				curSugar++;
		}
	}

	public void resetCurSugar() {
		curSugar = 0;
	}

	@Override
	public void chooseState() {
		getState().chooseState();
		updateSelf();
	}

	public Color getCellColor() {
		Color color = Color.ORANGE;
		int temp = maxSugar;
		while (temp <= curSugar){
			color = color.brighter();
		}
		return color;
	}
}
