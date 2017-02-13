package games.slime;

import java.util.List;

import cellsociety_team18.Cell;
import cellsociety_team18.Game;
import cellsociety_team18.State;
import javafx.scene.paint.Color;
import games.EmptyState;

/**
 * @author nikita Class represents a chemical state in the slime simulation.
 */
public class ChemicalState extends SlimeGameState {
	private int time;
	private double diffusionChance;
	private Game game;
	private static final int STATE_VALUE = 1;

	public ChemicalState(Game game) {
		this.game = game;
		time = game.getSettings().getIntParameter("evaporationTime");
		this.diffusionChance = game.getSettings().getDoubleParameter("diffusionChance");
		setColor(Color.web(game.getSettings().getParameter("chemicalColor").toUpperCase()));
		setStateValue(STATE_VALUE);
	}

	/**
	 * initialize a new chemical state
	 * 
	 * @param time
	 *            the time this chemical has left to stay before it evaporates
	 * @param game
	 *            the game it is part of
	 */
	public ChemicalState(int time, Game game) {
		this(game);
		this.time = time;
	}

	/**
	 * choose the next state
	 */
	@Override
	public void chooseState() {
		if (time < 0) {
			if (!(getCell().getNextState() instanceof SlimeState)) {
				getCell().setNextState(new EmptyState(game));
			}
			return;
		}
		setColor(getColor().darker());
		time--;
		List<Cell> neighbors = getCell().getNeighbors();
		for (Cell neighbor : neighbors) {
			double rand = Math.random();
			if (rand < diffusionChance && !(neighbor.getNextState() instanceof SlimeState)
					&& !(neighbor.getState() instanceof SlimeState)) {
				neighbor.setNextState(new ChemicalState(time - 1, game));
			}
		}
	}

	public int getSlimeContent() {
		return time;
	}
}
