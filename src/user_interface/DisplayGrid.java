package user_interface;

import java.util.HashMap;

import cellsociety_team18.Point;
import graphic_elements.GraphicHexagon;
import graphic_elements.GraphicPolygon;
import graphic_elements.GraphicSquare;
import graphic_elements.GraphicTriangle;
import grids.Grid;
import cellsociety_team18.Simulation;
import javafx.scene.Group;
import javafx.scene.paint.Color;

/**
 * @author elliott This class represents a visual grid.
 */
public class DisplayGrid extends Group {

	private int sizeInPixels;
	private int sizeInCells;
	private boolean responsive = false;
	
	private ViewController viewController;
	private Simulation simulation;
	private HashMap<Point, GraphicPolygon> cells = new HashMap<Point, GraphicPolygon>();

	public DisplayGrid(ViewController viewController, Simulation simulation, int sizeInPixels, String gridType) {
		this.viewController = viewController;
		this.simulation = simulation;
		this.sizeInPixels = sizeInPixels;
		this.sizeInCells = simulation.getGrid().getSize();
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
				GraphicPolygon cell = getNewCell(gridType, new Point(i, j));
				cell.setOnMouseClicked((event) -> {
					if (responsive) {
						viewController.cellClicked(cell, simulation);
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
	/*public void changeSizeInCells(int newSize, String gridType) {
		sizeInCells = newSize;
		createCells(gridType);
	}*/

	/**
	 * Update the colors of the visual grid.
	 */
	public void update(Grid grid) {
		responsive = true;
		for (int i = 0; i < sizeInCells; i++) {
			for (int j = 0; j < sizeInCells; j++) {
				Point point = new Point(i, j);
				GraphicPolygon cell = cells.get(point);
				cell.setData(grid.getCell(point));
				cells.put(point, cell);
			}
		}
	}

}
