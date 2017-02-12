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
 * This class represents a population in Segregation.
 */
public class Population extends State {

	private Game game;
	private double satisfactionThreshold;
	private int populationID;
	
	public Population(Game game, String color, int populationID) {
		this.satisfactionThreshold = game.getDoubleParameter("satisfaction");
		this.game = game;
		this.populationID = populationID;
		setColor(Color.web(color.toUpperCase()));
	}
	
	public int getPopulationID() {
		return populationID;
	}
	
	/**
	 * @return The percentage of neighbors that are similar to the Agent.
	 */
	private double percentageOfSimilarNeighbors() {
		int neighbors = 0;
		int similar = 0;
		for (Cell cell: getCell().getNeighborsDiagonal()) {
			if (!(cell.getState() instanceof EmptyState)) {
				neighbors++;
				/*if (cell.getState().getClass().equals(this.getClass())) {
					similar++;
				}*/
				if (populationID == ((Population) cell.getState()).getPopulationID()) {
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
