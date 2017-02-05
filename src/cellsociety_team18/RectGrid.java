package cellsociety_team18;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RectGrid extends Grid {

	private int size;

	@Override
	public void setup(int size, Map<State, List<Point>> locations, Game game) {
		this.cells = new HashMap<Point, Cell>();
		this.size = size;
		this.game = game;
		int sqrt = (int)Math.sqrt(size);
		for (int i = 0; i < sqrt; i++){
			for (int j = 0; j < sqrt; j++){
				Point p = new Point(i, j);
				Cell cell = new SquareCell(this, p, null);
				cell.setNextState(game.getRandomState(cell));
				cell.updateState();
				cells.put(p, cell);
			}
		}
		/*int sqrt = (int)Math.sqrt(size);
		for (int i = 0; i < sqrt; i++){
			for (int j = 0; j < sqrt; j++){
				Point p = new Point(i, j);
				Cell cell = new SquareCell(this, p, null);
				cell.setNextState(new DeadState(cell));
				cell.updateState();
				cells.put(p, cell);
			}
		}
		for (int j = 0; j < 3; j++){
			Point p = new Point(15, j + 15);
			Cell cell = getCell(p);
			System.out.println(cell);
			cell.setNextState(new LiveState(cell));
			cell.updateState();
			cell.getState().setColor(Color.RED);
			cells.put(p, cell);
			System.out.println(cell.getColor());
			System.out.println(cell.getPoint().getX() + " " + cell.getPoint().getY());
		}*/
		/*int sqrt = (int) Math.sqrt(size);
		for (int i = 0; i < 2; i++){
			for (int j = 0; j < sqrt; j++){
				Point p = new Point(j, i);
				Cell cell = new SquareCell(this, p, null);
				cell.setNextState(new DeadState(cell));
				cell.updateState();
				cells.put(p, cell);
			}
		}
		Point p1 = new Point(0, 2);
		Cell cell1 = new SquareCell(this, p1, null);
		cell1.setNextState(new DeadState(cell1));
		cell1.updateState();
		cells.put(p1, cell1);
		
		for (int i = 0; i < 3; i++){
			Point p = new Point(i + 1, 2);
			Cell cell = new SquareCell(this, p, null);
			cell.setNextState(new LiveState(cell));
			cell.updateState();
			cells.put(p, cell);
		}
		
		Point p2 = new Point(4, 2);
		Cell cell2 = new SquareCell(this, p2, null);
		cell2.setNextState(new DeadState(cell2));
		cell2.updateState();
		cells.put(p2, cell2);
		for (int i = 0; i < 2; i++){
			for (int j = 0; j < sqrt; j++){
				Point p = new Point(j + 3, i);
				Cell cell = new SquareCell(this, p, null);
				cell.setNextState(new DeadState(cell));
				cell.updateState();
				cells.put(p, cell);
			}
		}
		
		System.out.println(cells.size());*/
	}

	/*
	 * Contains diagonal neighbors
	 */
	public List<Cell> getNeighborsDiagonal(Point center) {
		List<Cell> neighbors = new ArrayList<Cell>();
		int x = center.getX();
		int y = center.getY();
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if (!(i == x && j == y))
					neighbors.add(getCell(new Point(i, j)));
				
			}
		}
		neighbors.removeAll(Collections.singleton(null));
		return neighbors;
	}

	/*
	 * Does not contain diagonal neighbors
	 */
	@Override
	public List<Cell> getNeighbors(Point center) {
		List<Cell> neighbors = new ArrayList<Cell>();
		int x = center.getX();
		int y = center.getY();
		neighbors.add(getCell(new Point(x - 1, y)));
		neighbors.add(getCell(new Point(x + 1, y)));
		neighbors.add(getCell(new Point(x, y - 1)));
		neighbors.add(getCell(new Point(x, y + 1)));
		neighbors.removeAll(Collections.singleton(null));
		return neighbors;
	}

}
