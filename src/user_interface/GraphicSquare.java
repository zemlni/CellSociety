package user_interface;

import cellsociety_team18.Point;
import javafx.scene.paint.Color;

public class GraphicSquare extends GraphicPolygon {
	public GraphicSquare(Color color, int gridSizePixels, int gridSizeCells, Point center) {
		setSize(gridSizePixels / gridSizeCells);
		center.setX(center.getX() * getSize());
		center.setY(center.getY() * getSize());
		Double[] vertices = getVertices(center);
		getPoints().addAll(vertices);
		setFill(color);
	}

	@Override
	public Double[] getVertices(Point topLeft) {
		double x = topLeft.getX();
		double y = topLeft.getY();
		Double[] vertices = { x, y, x + getSize(), y, x + getSize(), y + getSize(), x, y + getSize() };
		return vertices;
	}

}
