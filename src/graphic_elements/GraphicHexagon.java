package graphic_elements;

import cellsociety_team18.Point;
import javafx.scene.paint.Color;

/**
 * @author nikita Class represents a hexagon graphically
 */
public class GraphicHexagon extends GraphicPolygon {
	/**
	 * make a new hexagon of given params
	 * 
	 * @param color
	 *            required color
	 * @param gridSizePixels
	 *            grid size in pixels
	 * @param gridSizeCells
	 *            grid size in cells
	 * @param center
	 *            center of the hexagon according to the grid
	 * @param outlined
	 *            if hexagon is outlined or not
	 * @param cellSize
	 *            size of cell
	 */
	public GraphicHexagon(Color color, int gridSizePixels, int gridSizeCells, Point center, Boolean outlined,
			int cellSize) {
		super(outlined);
		setSize(cellSize);
		double width = getSize() * 1.5;
		double horizontalDistance = width * 3 / 4;
		double height = Math.sqrt(3) / 2 * width;
		if (!(Math.floorMod(((int) center.getX()), 2) == 0)) {
			center.setY(center.getY() + 0.5);
		}
		center.setX(center.getX() * horizontalDistance);
		center.setY(center.getY() * height);

		Double[] vertices = getVertices(center);
		getPoints().addAll(vertices);

		setFill(color);
	}

	/**
	 * get vertices of hexagon at the point.
	 * 
	 * @param center
	 *            the center of the polygon
	 * @return array of points of vertices of hexagon
	 */
	public Double[] getVertices(Point center) {
		Double[] vertices = new Double[12];
		int x = (int) center.getX();
		int y = (int) center.getY();
		for (int i = 0; i < 6; i++) {
			double angle = Math.PI * 60 * i / 180;
			vertices[2 * i] = x + getSize() / 2 * Math.cos(angle);
			vertices[2 * i + 1] = y + getSize() / 2 * Math.sin(angle);
		}
		return vertices;
	}
}
