package user_interface;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * @author elliott
 * This class represents a visual, square cell.
 */
public class GraphicCell extends Rectangle {

	/**
	 * @param color The color of the cell.
	 * @param gridSizePixels The width/height of the grid in pixels.
	 * @param gridSizeCells The width/height of the grid in cells.
	 */
	public GraphicCell(Color color, int gridSizePixels, int gridSizeCells) {
		setHeight(gridSizePixels / gridSizeCells);
		setWidth(gridSizePixels / gridSizeCells);
		setFill(color);
	}
	
	public void setColor(Color color) {
		setFill(color);
	}
	
}
