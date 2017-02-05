package user_interface;

import java.util.HashMap;

import cellsociety_team18.Grid;
import cellsociety_team18.Point;
import javafx.scene.Group;
import javafx.scene.paint.Color;

public class DisplayGrid extends Group {
	
	private int sizeInPixels;
	private int sizeInCells;
	private HashMap<Point, GraphicCell> cells = new HashMap<Point, GraphicCell>();

	public DisplayGrid(int sizeInPixels, int sizeInCells) {
		this.sizeInPixels = sizeInPixels;
		this.sizeInCells = sizeInCells;
		createCells();
	}
	
	private void createCells() {
		for (int i = 0; i < sizeInCells; i++) {
			for (int j = 0; j < sizeInCells; j++) {
				GraphicCell cell = new GraphicCell(Color.WHITE, sizeInPixels, sizeInCells);
				cell.setX(j * sizeInPixels / sizeInCells);
				cell.setY(i * sizeInPixels / sizeInCells);
				cells.put(new Point(i, j), cell);
				getChildren().add(cell);
			}
		}
	}

	public void update(Grid grid) {
		for (int i = 0; i < sizeInCells; i++) {
			for (int j = 0; j < sizeInCells; j++) {
				Point point = new Point(i, j);
				GraphicCell cell = cells.get(point);
				cell.setColor(grid.getCell(point).getColor());
				cells.put(point, cell);
			}
		}
	}

}
