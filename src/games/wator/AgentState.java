package games.wator;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import cellsociety_team18.Cell;
import cellsociety_team18.Game;
import cellsociety_team18.State;
import games.EmptyState;

/**
 * @author elliott This class represents an Agent in the Wator game.
 */
public abstract class AgentState extends State {

	private Game game;
	private int reproductionTime;
	private int survivalTime = 0;
	
	public AgentState(Game game) {
		this.game = game;
	}
	
	public void setReproductionTime(int time) {
		reproductionTime = time;
	}

	public int getSurvivalTime() {
		return survivalTime;
	}
	
	public void setSurvivalTime(int survivalTime) {
		this.survivalTime = survivalTime;
	}
	
	/**
	 * @return A random cell that the agent can move to.
	 */
	private Cell getPossibleMove() {
		List<Cell> options = getCell().getNeighbors();
		Iterator<Cell> i = options.iterator();
		while (i.hasNext()) {
			Cell cell = i.next();
			if (this instanceof FishState && !(cell.getNextState() instanceof EmptyState) 
					|| this instanceof SharkState && cell.getNextState() instanceof SharkState) {
				i.remove();
			}
		}
		Collections.shuffle(options);
		if (options.size() == 0) 
			return null;
		return options.get(0);
	}
	
	/**
	 * Move to a specific spot on the grid.
	 * @param state The state we are creating.
	 */
	public void moveTo(AgentState state) {
		survivalTime++;
		Cell destination = getPossibleMove();
		if (destination != null) {
			if (survivalTime >= reproductionTime) {
				survivalTime = 0;
				getCell().setNextState(this);
				replace(destination, state);
				return;
			}
			if (!(this instanceof FishState && getCell().getNextState() instanceof SharkState)) {
				getCell().setNextState(new EmptyState(game));
			}
			replace(destination, this);
		}
	}
	
	/**
	 * Where the replacing is actually done.
	 */
	private void replace(Cell neighbor, AgentState state) {
		state.setCell(neighbor);
		State replaced = neighbor.getNextState();
		if (this instanceof SharkState && replaced instanceof FishState) {
			
			((SharkState) state).incrementEnergy();
		}
		neighbor.setNextState(state);
	}

	@Override
	public abstract void chooseState();

}
