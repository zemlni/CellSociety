package user_interface;

import cellsociety_team18.Cell;
import cellsociety_team18.Grid;
import cellsociety_team18.Point;
import cellsociety_team18.Simulation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

class GraphicCell extends Rectangle {
	public static final int gridSize = 40;
	public static final int actualGridSize = 600;

	public GraphicCell(Color color) {
		setHeight(actualGridSize / gridSize);
		setWidth(actualGridSize / gridSize);
		setFill(color);
	}
}

public class ViewController {
	
	public static final int FRAMES_PER_SECOND = 4;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

	public static final int gridSize = 40;
	public static final int actualGridSize = 600;

	private Simulation simulation = new Simulation(1, 0);
	private Group myDisplayGrid;
	private Timeline myAnimation;

	public ViewController(Stage stage) {
		stage.setTitle("CellSociety");
		myDisplayGrid = createDisplay();
		Scene scene = new Scene(myDisplayGrid, actualGridSize, actualGridSize, Color.BLACK);
		stage.setScene(scene);
		stage.show();
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
		myAnimation = new Timeline();
		myAnimation.setCycleCount(Timeline.INDEFINITE);
		myAnimation.getKeyFrames().add(frame);
	}
	
	public void start() {
		myAnimation.play();
	}
	
	private void step(double elapsedTime) {
		Grid grid = simulation.step();
		updateDisplay(grid);
	}

	private Group createDisplay() {
		Grid grid = simulation.step();
		Group group = new Group();
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				Cell data = grid.getCell(new Point(i, j));
				GraphicCell cell = new GraphicCell(data.getColor());
				cell.setX(j * actualGridSize / gridSize);
				cell.setY(i * actualGridSize / gridSize);
				group.getChildren().add(cell);
			}
		}
		return group;
	}

	private void updateDisplay(Grid grid) {
		myDisplayGrid.getChildren().removeAll(myDisplayGrid.getChildren());
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				Cell data = grid.getCell(new Point(i, j));
				GraphicCell cell = new GraphicCell(data.getColor());
				cell.setX(j * actualGridSize / gridSize);
				cell.setY(i * actualGridSize / gridSize);
				myDisplayGrid.getChildren().add(cell);
			}
		}
	}


}
