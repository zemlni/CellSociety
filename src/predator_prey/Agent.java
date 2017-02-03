package predator_prey;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import cellsociety_team18.Cell;
import cellsociety_team18.State;
import segregation.Empty;

/**
 * @author elliott
 * This class represents an Agent in the Predator/Prey game.
 */
public abstract class Agent extends State {
	
	private int reproductionTime;
	private int survivalTime = 0;

	/**
	 * @param cell The cell that owns this state.
	 * @param reproductionTime The time elapsed before reproduction takes place.
	 */
	public Agent(Cell cell, int reproductionTime) {
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
		   if (this instanceof Fish && !(cell.getState() instanceof Empty)
				   || this instanceof Shark && cell.getState() instanceof Shark) {
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
			getCell().setNextState(new Empty(getCell()));
			return cell.getState();
		}
		return null;
	}

	@Override
	public abstract void chooseState();

}
