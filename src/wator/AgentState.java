package wator;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import cellsociety_team18.Cell;
import cellsociety_team18.State;
import wator.EmptyState;

/**
 * @author elliott
 * This class represents an Agent in the Wator game.
 */
public abstract class AgentState extends State {
	
	private int reproductionTime;
	private int survivalTime = 0;

	/**
	 * @param cell The cell that owns this state.
	 * @param reproductionTime The time elapsed before reproduction takes place.
	 */
	public AgentState(Cell cell, int reproductionTime) {
		super(cell);
		this.reproductionTime = reproductionTime;
	}
	
	/**
	 * @return A randomized list of locations the agent can move to.
	 */
	public List<Cell> getOptions() {
		List<Cell> options = getCell().getNeighbors();
		Iterator<Cell> i = options.iterator();
		while (i.hasNext()) {
			Cell cell = i.next();
		   if (this instanceof FishState && !(cell.getState() instanceof EmptyState)
				   || this instanceof SharkState && cell.getState() instanceof SharkState) {
			   i.remove();
		   }
		}
		Collections.shuffle(options);
		return options;
	}
	
	/**
	 * Move to new spot. Potentially reproduce.
	 * @return The state the agent has replaced.
	 */
	public State move(List<Cell> options) {
		survivalTime++;
		if (options.size() > 0) {
			Cell cell = options.get(0);
			if (survivalTime >= reproductionTime) {
				survivalTime = 0;
				cell.setNextState(this);
				return cell.getState();
			}
			cell.setNextState(this);
			getCell().setNextState(new EmptyState(getCell()));
			return cell.getState();
		}
		return null;
	}

	@Override
	public abstract void chooseState();

}
