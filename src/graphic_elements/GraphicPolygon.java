package graphic_elements;

import java.util.Map;
import java.util.Map.Entry;

import cellsociety_team18.Cell;
import cellsociety_team18.Point;
import cellsociety_team18.State;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * @author nikita Class represents a graphic polygon to be used for displaying
 *         different grids.
 */
public abstract class GraphicPolygon extends Polygon {

	private Cell data;
	private double size;

	/**
	 * make a graphic polygon
	 * 
	 * @param outlined
	 *            if polygon will be outlined or not
	 */
	public GraphicPolygon(Boolean outlined) {
		if (outlined) {
			setStyle("-fx-stroke: black; -fx-stroke-width: 1;");
		}
	}

	public void setSize(double size) {
		this.size = size;
	}

	public double getSize() {
		return size;
	}

	public void setData(Cell cell) {
		data = cell;
		setColor(cell.getColor());
	}

	public Cell getData() {
		return data;
	}

	public void setColor(Color color) {
		setFill(color);
	}

	/**
	 * get vertices of the polygon
	 * 
	 * @center center point of polygon
	 * @return array of locations of vertices of the polygon
	 */
	public abstract Double[] getVertices(Point center);

	/**
	 * return name of state occupying the cell.
	 * 
	 * @param states
	 *            possible states
	 * @param cell
	 *            cell being represented by this polygon
	 * @return name of state in cell
	 */
	public static String getStateName(Map<String, State> states, GraphicPolygon cell) {
		String currentState = "";
		for (Entry<String, State> entry : states.entrySet()) {
			if (entry.getValue().getClass().equals(cell.getData().getState().getClass())) {
				currentState = entry.getKey();
				break;
			}
		}
		return currentState;
	}

	/**
	 * update graphic rendering based on given state
	 * 
	 * @state state given
	 */
	public void update(State state) {
		state.setCell(data);
		data.setNextState(state);
		data.updateState();
	}

}
