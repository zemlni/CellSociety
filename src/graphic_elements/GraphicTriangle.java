package graphic_elements;

import cellsociety_team18.Point;
import javafx.scene.paint.Color;

public class GraphicTriangle extends GraphicPolygon {
	
	private boolean down;

	public GraphicTriangle(Color color, int gridSizePixels, int gridSizeCells, Point center, Boolean outlined, int cellSize) {
		super(outlined);
		setSize(cellSize);
		down = ((int) center.getX()) % 2 == ((int) center.getY() % 2);
		
		double width = getSize() * 1.5;
		double horizontalDistance = width * Math.sqrt(3)/2;
		double height = width * 1.5;
		
		center.setX(center.getX() * horizontalDistance);
		center.setY(center.getY() * height);
		if (down)
			center.setY(center.getY() - (Math.sqrt(3) / 6) * horizontalDistance);
		else
			center.setY(center.getY() + (Math.sqrt(3) / 6) * horizontalDistance);

		Double[] vertices = getVertices(center);
		getPoints().addAll(vertices);
		setFill(color);
	}

	@Override
	public Double[] getVertices(Point center) {

		Double[] vertices = new Double[6];
		int x = (int) center.getX();
		int y = (int) center.getY();

		for (int i = 0; i < 3; i++) {
			double angle = 120 * i + 90;
			if (!down)
				angle += 180;

			angle = angle * Math.PI / 180;
			vertices[2 * i] = x + getSize() * Math.cos(angle);
			vertices[2 * i + 1] = y + getSize() * Math.sin(angle);
		}

		return vertices;

	}

}
