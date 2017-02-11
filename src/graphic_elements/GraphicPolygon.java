package graphic_elements;

import java.util.Map;
import java.util.Map.Entry;

import cellsociety_team18.Cell;
import cellsociety_team18.Point;
import cellsociety_team18.State;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public abstract class GraphicPolygon extends Polygon {

	/**
	 * Make different shapes with list of vertices. Not sure how to do it here.
	 */

	private Cell data;
	private double size;

	public void setSize(double size){
		this.size = size;
	}
	public double getSize(){
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

	public abstract Double[] getVertices(Point center);

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

	public void update(State state) {
		state.setCell(data);
		data.setNextState(state);
		data.updateState();
	}

}
