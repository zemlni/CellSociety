package games.foraging_ants;

import cellsociety_team18.Cell;
import cellsociety_team18.Game;
import cellsociety_team18.Point;
import cellsociety_team18.State;
import games.EmptyState;
import grids.Grid;

public class ForagingAntsGame extends Game {

	@Override
	public State getStateProbabilistically() {
		double rand = Math.random();
		return rand < getDoubleParameter("percentAnts") ? new AntState() : new EmptyState(game);
	}

	@Override
	public void setup() {
		

	}

	@Override
	public void setStates() {
		// TODO Auto-generated method stub

	}

}
