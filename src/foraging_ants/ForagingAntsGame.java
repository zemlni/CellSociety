package foraging_ants;

import cellsociety_team18.Cell;
import cellsociety_team18.Game;
import cellsociety_team18.Point;
import cellsociety_team18.State;
import grids.Grid;

public class ForagingAntsGame extends Game {

	@Override
	public State getRandomState() {
		double rand = Math.random();
		return rand < getDoubleParameter("percentAnts") ? new AntState() : new EmptyState();
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setup() {
		

	}

	@Override
	public void setStates() {
		// TODO Auto-generated method stub

	}
	@Override
	public Cell makeNewCell(Grid grid, Point p){
		return new ForagingAntsCell(grid, p);
	}

}
