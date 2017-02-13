package games.sugarscape;

import cellsociety_team18.Game;
import cellsociety_team18.State;
import javafx.scene.paint.Color;

public class SugarState extends State {
	private int curSugar;
	private int maxSugar;
	private int curTicks;
	private int interval;
	private Game game;

	/**
	 * Empty state is white and is transparent.
	 */
	public SugarState(Game game) {
		this.game = game;
		this.curSugar = game.getSettings().getIntParameter("maxSugar");
		this.maxSugar = game.getSettings().getIntParameter("maxSugar");
		this.interval = game.getSettings().getIntParameter("interval");
		setColor(Color.web(game.getSettings().getParameter("sugarColor").toUpperCase()));
	}

	public SugarState(int curSugar, Game game) {
		this(game);
		this.curSugar = curSugar;
		updateColor();
	}

	public int getCurSugar() {
		return curSugar;
	}

	public void resetCurSugar() {
		curSugar = 0;
	}

	@Override
	public void chooseState() {
		curTicks++;
		if (curTicks >= interval) {
			curTicks = 0;
			if (curSugar < maxSugar) {
				curSugar++;
				setColor(getColor().darker());
			}
		}
	}

	private void updateColor() {
		Color temp = getColor();
		for (int i = curSugar; i < maxSugar; i++) {
			temp = temp.brighter();
		}
		setColor(temp);
	}
}