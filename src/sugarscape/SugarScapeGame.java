package sugarscape;

import cellsociety_team18.Cell;
import cellsociety_team18.Game;
import cellsociety_team18.Point;
import cellsociety_team18.State;
import grids.Grid;

public class SugarScapeGame extends Game {

	@Override
	public State getRandomState() {
		double rand = Math.random();
		return rand < getDoubleParameter("percentAgent")
				? new AgentState(getIntParameter("sugarStart"), getIntParameter("sugarMetabolism")) : new EmptyState();
	}

	@Override
	public void setup() {
		setParameters("percentAgent", "sugarStart", "sugarMetabolism", "maxSugar", "interval");


	}

	@Override
	public void setStates() {
		getStates().put("Agent", new AgentState(getIntParameter("sugarStart"), getIntParameter("sugarMetabolism")));
		getStates().put("Empty", new EmptyState());

	}
	@Override
	public Cell makeNewCell(Grid grid, Point p){
		SugarScapeCell cell = new SugarScapeCell(grid, p);
		cell.setParameters(getIntParameter("maxSugar"), getIntParameter("intergval"));
		return cell;
	}
}
