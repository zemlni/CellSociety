package cellsociety_team18;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;


public abstract class Grid {
	HashMap<Point, Cell> cells;

	public Collection getCells() {
		return cells.values();
	}

	public abstract ArrayList<Cell> getNeighbors(Point center);
	
	public Cell getCell(Point center){
		return cells.get(center);
	}
}
