package user_interface;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GraphicCell extends Rectangle {

	public GraphicCell(Color color, int gridSizePixels, int gridSizeCells) {
		setHeight(gridSizePixels / gridSizeCells);
		setWidth(gridSizePixels / gridSizeCells);
		setFill(color);
	}
	
	public void setColor(Color color) {
		setFill(color);
	}
	
}
