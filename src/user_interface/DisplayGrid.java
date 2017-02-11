package user_interface;

import java.util.HashMap;

import cellsociety_team18.Grid;
import cellsociety_team18.Point;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.paint.Color;

/**
 * @author elliott This class represents a visual grid.
 */
public class DisplayGrid extends Group {

	private int sizeInPixels;
	private int sizeInCells = 40;
	private boolean responsive = false;

	private ViewController viewController;
	// private HashMap<Point, GraphicCell> cells = new HashMap<Point,
	// GraphicCell>();
	private HashMap<Point, GraphicPolygon> cells = new HashMap<Point, GraphicPolygon>();

	public DisplayGrid(ViewController viewController, int sizeInPixels) {
		this.sizeInPixels = sizeInPixels;
		this.viewController = viewController;
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
				/*
				 * GraphicCell cell = new GraphicCell(Color.WHITE, sizeInPixels,
				 * sizeInCells);
				 */
				GraphicPolygon cell = new GraphicTriangle(Color.WHITE, sizeInPixels, sizeInCells, new Point(i, j));
				//System.out.println(cell);
				/*
				 * cell.setX(j * sizeInPixels / sizeInCells); cell.setY(i *
				 * sizeInPixels / sizeInCells);
				 */
				cell.setOnMouseClicked((event) -> {
					if (responsive) {
						viewController.cellClicked(cell);
					}
				});
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
		responsive = true;
		for (int i = 0; i < sizeInCells; i++) {
			for (int j = 0; j < sizeInCells; j++) {
				Point point = new Point(i, j);
				// GraphicCell cell = cells.get(point);
				GraphicPolygon cell = cells.get(point);
				cell.setData(grid.getCell(point));
				cells.put(point, cell);
			}
		}
	}

}
