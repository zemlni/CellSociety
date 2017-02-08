package user_interface;

import java.util.HashMap;

import cellsociety_team18.Grid;
import cellsociety_team18.Point;
import javafx.scene.Group;
import javafx.scene.paint.Color;

/**
 * @author elliott
 * This class represents a visual grid.
 */
public class DisplayGrid extends Group {
	
	private int sizeInPixels;
	private int sizeInCells = 40;
	
	private HashMap<Point, GraphicCell> cells = new HashMap<Point, GraphicCell>();

	public DisplayGrid(int sizeInPixels) {
		this.sizeInPixels = sizeInPixels;
		createCells();
	}
	
	public int getSizeInCells() {
		return sizeInCells;
	}
	
	/**
	 * Originally create the graphic cells.
	 */
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
	
	/**
	 * Update the size of the grid in cells.
	 */
	public void changeSizeInCells(int newSize) {
		sizeInCells = newSize;
		createCells();
	}
	
	/**
	 * Update the colors of the visual grid.
	 */
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
