package cellsociety_team18;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

class GraphicCell extends Rectangle {
	public static final int gridSize = 20;
	public static final int actualGridSize = 600;

	public GraphicCell(Color color) {
		setHeight(actualGridSize / gridSize);
		setWidth(actualGridSize / gridSize);
		setFill(color);
	}
}

public class CellSociety extends Application {

	public static final int FRAMES_PER_SECOND = 1;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

	public static final int gridSize = 20;
	public static final int actualGridSize = 600;

	private Simulation simulation = new Simulation(3, 0);
	private Group myDisplayGrid;

	/**
	 * Start application.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("CellSociety");
		myDisplayGrid = createDisplay();
		Scene scene = new Scene(myDisplayGrid, actualGridSize, actualGridSize, Color.BLACK);
		primaryStage.setScene(scene);
		primaryStage.show();
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
		Timeline myAnimation = new Timeline();
		myAnimation.setCycleCount(Timeline.INDEFINITE);
		myAnimation.getKeyFrames().add(frame);
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

	/**
	 * Start the program.
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
