package user_interface;

import java.util.HashMap;

import cellsociety_team18.Point;
import graphic_elements.GraphicHexagon;
import graphic_elements.GraphicPolygon;
import graphic_elements.GraphicSquare;
import graphic_elements.GraphicTriangle;
import grids.Grid;
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

	public DisplayGrid(ViewController viewController, int sizeInPixels, String gridType) {
		this.sizeInPixels = sizeInPixels;
		this.viewController = viewController;
		createCells(gridType);
	}

	public int getSizeInCells() {
		return sizeInCells;
	}

	/**
	 * Originally create the graphic cells.
	 */
	private void createCells(String gridType) {
		for (int i = 0; i < sizeInCells; i++) {
			for (int j = 0; j < sizeInCells; j++) {
				/*
				 * GraphicCell cell = new GraphicCell(Color.WHITE, sizeInPixels,
				 * sizeInCells);
				 */
				GraphicPolygon cell = getNewCell(gridType, new Point(i, j));
				
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
	private GraphicPolygon getNewCell(String gridType, Point center){
		if (gridType.equals("Square"))
			return new GraphicSquare(Color.WHITE, sizeInPixels, sizeInCells, center);
		if (gridType.equals("Hexagon"))
			return new GraphicHexagon(Color.WHITE, sizeInPixels, sizeInCells, center);
		return new GraphicTriangle(Color.WHITE, sizeInPixels, sizeInCells, center);
	}
	/**
	 * Update the size of the grid in cells.
	 */
	public void changeSizeInCells(int newSize, String gridType) {
		sizeInCells = newSize;
		createCells(gridType);
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
