package games.segregation;

import java.util.Collections;
import java.util.List;

import cellsociety_team18.Cell;
import cellsociety_team18.Game;
import cellsociety_team18.State;
import games.EmptyState;
import javafx.scene.paint.Color;

/**
 * @author elliott
 * This abstract class represents an Agent in Segregation.
 * It calculates the percentage of similar neighbors and determines the movement of agents.
 */
public abstract class AgentState extends State {
	
	private Game game;
	private double satisfactionThreshold;

	/**
	 */
	public AgentState(Game game, String color) {
		this.satisfactionThreshold = game.getDoubleParameter("satisfaction");
		this.game = game;
		setColor(Color.web(color.toUpperCase()));
	}
	
	/**
	 * @return The percentage of neighbors that are similar to the Agent.
	 */
	private double percentageOfSimilarNeighbors() {
		int neighbors = 0;
		int similar = 0;
		for (Cell cell: getCell().getNeighbors()) {
			if (!(cell.getState() instanceof EmptyState)) {
				neighbors++;
				if (cell.getState().getClass().equals(this.getClass())) {
					similar++;
				}
			}
		}
		return ((double) similar) / neighbors;
	}

	/**
	 * @return Whether the agent is satisfied or not.
	 */
	private boolean isSatisfied() {
		return satisfactionThreshold <= percentageOfSimilarNeighbors();
	}

	/**
	 * Determines, if necessary, a new spot for the Agent.
	 */
	@Override
	public void chooseState() {
		if (!isSatisfied()) {
			List<Cell> cells = getCell().getGrid().getCellsAsList();
			Collections.shuffle(cells);
			for (Cell cell: cells) {
				if (cell.getNextState() instanceof EmptyState && !cell.equals(getCell())) {
					getCell().setNextState(new EmptyState(game));
					cell.setNextState(this);
					return;
				}
			}
		}
	}

}
