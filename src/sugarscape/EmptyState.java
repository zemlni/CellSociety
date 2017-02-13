package sugarscape;

import cellsociety_team18.State;
import javafx.scene.paint.Color;

public class EmptyState extends State {
	private int curSugar;
	private int maxSugar;
	private int curTicks;
	private int interval;

	/**
	 * Empty state is white and is transparent.
	 */
	public EmptyState(int curSugar, int maxSugar, int interval) {
		this.curSugar = maxSugar;
		this.maxSugar = maxSugar;
		this.interval = interval;
		updateColor();
	}

	public int getCurSugar() {
		return curSugar;
	}

	public void resetCurSugar() {
		curSugar = 0;
	}

	/**
	 * An Empty state has no rules to obey.
	 */
	@Override
	public void chooseState() {
		curTicks++;
		if (curTicks >= interval) {
			curTicks = 0;
			if (curSugar < maxSugar)
				curSugar++;
		}
		updateColor();

	}

	private void updateColor() {
		Color color = Color.ORANGE;
		int temp = maxSugar;
		while (temp >= curSugar) {
			color = color.brighter();
			temp--;
			if (temp <= 0) {
				color = Color.WHITE;
				break;
			}
		}
		setColor(color);
	}

}
