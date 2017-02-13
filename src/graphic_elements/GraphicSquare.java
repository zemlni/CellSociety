package graphic_elements;

import cellsociety_team18.Point;
import javafx.scene.paint.Color;

/**
 * @author nikita Class represents a square cell graphically.
 */
public class GraphicSquare extends GraphicPolygon {

	/**
	 * make a new square of given params
	 * 
	 * @param color
	 *            required color
	 * @param gridSizePixels
	 *            grid size in pixels
	 * @param gridSizeCells
	 *            grid size in cells
	 * @param center
	 *            center of the square according to the grid
	 * @param outlined
	 *            if square is outlined or not
	 * @param cellSize
	 *            size of cell
	 */
	public GraphicSquare(Color color, int gridSizePixels, int gridSizeCells, Point center, Boolean outlined,
			int cellSize) {
		super(outlined);
		setSize(cellSize);
		center.setX(center.getX() * getSize());
		center.setY(center.getY() * getSize());
		Double[] vertices = getVertices(center);
		getPoints().addAll(vertices);
		setFill(color);
	}

	/**
	 * get vertices of square at the point
	 * 
	 * @param topLeft
	 *            top left point of square
	 * @return array with vertices of the square
	 */
	@Override
	public Double[] getVertices(Point topLeft) {
		double x = topLeft.getX();
		double y = topLeft.getY();
		Double[] vertices = { x, y, x + getSize(), y, x + getSize(), y + getSize(), x, y + getSize() };
		return vertices;
	}

}
