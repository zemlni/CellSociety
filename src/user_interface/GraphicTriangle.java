package user_interface;

import cellsociety_team18.Point;
import javafx.scene.paint.Color;

public class GraphicTriangle extends GraphicPolygon {

	public GraphicTriangle(Color color, int gridSizePixels, int gridSizeCells, Point center) {
		setSize(gridSizePixels / gridSizeCells);
		Double[] vertices = getVertices(center);
		getPoints().addAll(vertices);
		setFill(color);
	}

	/**
	 * remove exact duplicated method from triangleGrid. try an import
	 */
	private boolean downDecider(Point center) {
		return ((int) center.getX()) % 2 == ((int) center.getY() % 2);
	}

	@Override
	public Double[] getVertices(Point center) {

		Double[] vertices = new Double[6];
		int x = (int) center.getX();
		int y = (int) center.getY();

		for (int i = 0; i < 3; i++) {
			double angle = 120 * i;
			if (downDecider(center))
				angle += 120;
			angle = angle * Math.PI / 180;
			vertices[2 * i] = x + Math.cos(angle);
			vertices[2 * i + 1] = y + Math.sin(angle);
		}

		return vertices;

	}

}
