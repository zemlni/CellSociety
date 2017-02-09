package user_interface;

import java.util.List;

import cellsociety_team18.Point;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class GraphicPolygon extends Polygon {

	/**
	 * Make different shapes with list of vertices. Not sure how to do it here.
	 */

	public GraphicPolygon(Color color, int gridSizePixels, int gridSizeCells, List<Point> vertices) {
		Double[] verts = new Double[vertices.size() * 2];
		for (int i = 0; i < vertices.size(); i++) {
			verts[i] = vertices.get(i).getX();
			verts[i + 1] = vertices.get(i).getY();
		}
		getPoints().addAll(verts);

		// need to check if this works
		setScaleX(gridSizePixels / gridSizeCells);
		setScaleY(gridSizePixels / gridSizeCells);
		setFill(color);
	}

	public void setColor(Color color) {
		setFill(color);
	}
}
