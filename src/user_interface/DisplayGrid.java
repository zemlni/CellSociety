package user_interface;

import java.util.HashMap;

import cellsociety_team18.Grid;
import cellsociety_team18.Point;
import cellsociety_team18.Simulation;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.paint.Color;

/**
 * @author elliott
 * This class represents a visual grid.
 */
public class DisplayGrid extends Group {
	
	private int sizeInPixels;
	private int sizeInCells;
	private boolean responsive = false;
	
	private ViewController viewController;
	private Simulation simulation;
	private HashMap<Point, GraphicCell> cells = new HashMap<Point, GraphicCell>();

	public DisplayGrid(ViewController viewController, Simulation simulation, int sizeInPixels) {
		this.viewController = viewController;
		this.simulation = simulation;
		this.sizeInPixels = sizeInPixels;
		this.sizeInCells = simulation.getGrid().getSize();
		createCells();
	}
	
	/**
	 * Originally create the graphic cells.
	 */
	public void createCells() {
		for (int i = 0; i < sizeInCells; i++) {
			for (int j = 0; j < sizeInCells; j++) {
				GraphicCell cell = new GraphicCell(Color.WHITE, sizeInPixels, sizeInCells);
				cell.setX(j * sizeInPixels / sizeInCells);
				cell.setY(i * sizeInPixels / sizeInCells);
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

	/**
	 * Update the colors of the visual grid.
	 */
	public void update(Grid grid) {
		responsive = true;
		for (int i = 0; i < sizeInCells; i++) {
			for (int j = 0; j < sizeInCells; j++) {
				Point point = new Point(i, j);
				GraphicCell cell = cells.get(point);
				cell.setData(grid.getCell(point));
				cells.put(point, cell);
			}
		}
	}

}
