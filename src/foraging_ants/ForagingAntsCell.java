package foraging_ants;

import java.util.List;

import cellsociety_team18.Cell;
import cellsociety_team18.Point;
import cellsociety_team18.State;
import grids.Grid;

public class ForagingAntsCell extends Cell {
	List<State> states;
	public ForagingAntsCell(Grid grid, Point point){
		super(grid, point);
	}
	public List<State> getStates(){
		return states;
	}
	@Override
	public void chooseState(){
		for (State state: states)
			state.chooseState();
	}
}
