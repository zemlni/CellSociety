package segregation;

import java.util.Collections;
import java.util.List;

import cellsociety_team18.Cell;
import cellsociety_team18.State;

/**
 * @author elliott
 * This abstract class represents an Agent in Segregation.
 * It calculates the percentage of similar neighbors and determines the movement of agents.
 */
public abstract class AgentState extends State {
	
	private double satisfactionThreshold;

	/**
	 * @param cell The cell that owns the state.
	 * @param satisfactionThreshold The percentage of neighbors that must be the same as the Agent in question.
	 */
	public AgentState(Cell cell, double satisfactionThreshold) {
		super(cell);
		this.satisfactionThreshold = satisfactionThreshold;
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
				if (cell.getState().getClass().equals(this.getClass())) {
					similar++;
				}
			}
		}
		if (neighbors == 0) {
			return 0;
		}
		return ((double) similar) / neighbors;
	}

	/**
	 * @return Whether the Agent is satisfied or not.
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
			List<Cell> cells = getCell().getGrid();
			Collections.shuffle(cells);
			for (Cell cell: cells) {
				if (cell.getNextState() instanceof EmptyState) {
					cell.setNextState(this);
					getCell().setNextState(new EmptyState(getCell()));
					return;
				}
			}
		}
	}

}
