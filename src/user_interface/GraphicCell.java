package user_interface;

import java.util.Map;
import java.util.Map.Entry;

import cellsociety_team18.Cell;
import cellsociety_team18.State;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * @author elliott
 * This class represents a visual, square cell.
 */
public class GraphicCell extends Rectangle {
	
	private Cell data;

	/**
	 * @param color The color of the cell.
	 * @param gridSizePixels The width/height of the grid in pixels.
	 * @param gridSizeCells The width/height of the grid in cells.
	 */
	public GraphicCell(Color color, int gridSizePixels, int gridSizeCells) {
		setHeight(gridSizePixels / gridSizeCells);
		setWidth(gridSizePixels / gridSizeCells);
		setFill(color);
	}
	
	private void setColor(Color color) {
		setFill(color);
	}
	
	public void setData(Cell cell) {
		data = cell;
		setColor(cell.getColor());
	}
	
	public Cell getData() {
		return data;
	}
	
	public static String getStateName(Map<String, State> states, GraphicCell graphicCell) {
		String currentState = "";
		for (Entry<String, State> entry : states.entrySet()) {
			if (entry.getValue().getClass().equals(graphicCell.getData().getState().getClass())) {
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
