package graphic_elements;

import cellsociety_team18.Point;
import javafx.scene.paint.Color;

public class GraphicHexagon extends GraphicPolygon {

	public GraphicHexagon(Color color, int gridSizePixels, int gridSizeCells, Point center) {
		setSize(gridSizePixels/gridSizeCells);
		double width = getSize();
		double horizontalDistance = width * 3/4;
		double height = Math.sqrt(3)/2 * width;
		if (!(Math.floorMod(((int) center.getX()), 2) == 0)) {
			center.setY(center.getY() + 0.5);
		}
		center.setX(center.getX() * horizontalDistance);
		center.setY(center.getY() * height);

		Double[] vertices = getVertices(center);
		getPoints().addAll(vertices);

		setFill(color);
	}

	public Double[] getVertices(Point center){
		Double[] vertices = new Double[12];
		int x = (int) center.getX();
		int y = (int) center.getY();
		for (int i = 0; i < 6; i++) {
			double angle = Math.PI * 60 * i / 180;
			vertices[2 * i] = x + getSize()/2 * Math.cos(angle);
			vertices[2 * i + 1] = y + getSize()/2 * Math.sin(angle);
		}
		return vertices;
	}
}
