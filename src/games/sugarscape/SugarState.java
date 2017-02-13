package games.sugarscape;

import cellsociety_team18.Game;
import cellsociety_team18.State;
import javafx.scene.paint.Color;

/**
 * @author nikita Class represents sugar state of sugarscape game and all
 *         relevant intelligence.
 */
public class SugarState extends State {
	private int curSugar;
	private int maxSugar;
	private int curTicks;
	private int interval;
	private Game game;

	/**
	 * Make a new sugar state with the game it is participating in
	 * @param game the game in which it is participating
	 */
	public SugarState(Game game) {
		this.game = game;
		this.curSugar = game.getSettings().getIntParameter("maxSugar");
		this.maxSugar = game.getSettings().getIntParameter("maxSugar");
		this.interval = game.getSettings().getIntParameter("interval");
		setColor(Color.web(game.getSettings().getParameter("sugarColor").toUpperCase()));
	}
	/** Make a new sugar state with preset cursugar other than default
	 * @param curSugar the value for cursugar to set
	 * @param game the game in which the sugar state will be participating
	 * */
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
	/**choose the next state.
	 * */
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