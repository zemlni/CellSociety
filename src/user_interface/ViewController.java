package user_interface;

import java.awt.Dimension;
import java.util.ResourceBundle;

import cellsociety_team18.Game;
import cellsociety_team18.Simulation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author elliott This class controls the user interface.
 */
public class ViewController {

	private final Dimension DEFAULT_SIZE = new Dimension(920, 600);
	private final String DEFAULT_RESOURCE_PACKAGE = "resources";
	private final int gridSize = 600;

	private int delay = 250;

	private Simulation mySimulation;
	private Group root;
	private DisplayGrid myDisplayGrid;
	private Timeline myAnimation;
	private Node placeholder;
	private ResourceBundle myResources;

	public ViewController(Stage stage) {
		stage.setTitle("CellSociety");
		stage.setResizable(false);
		myResources = ResourceBundle.getBundle("UIStrings");
		setupUI(stage);
	}

	public DisplayGrid getGrid() {
		return myDisplayGrid;
	}

	public double getGridSize() {
		return gridSize;
	}

	public int getGridSizeInCells() {
		if (myDisplayGrid == null) {
			return 40;
		}
		return myDisplayGrid.getSizeInCells();
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public String getTitle() {
		return getGame().getTitle();
	}

	public String getDescription() {
		return getGame().getDescription();
	}

	public Game getGame() {
		return mySimulation.getGame();
	}

	/**
	 * Create and start the animation.
	 */
	public void start() {
		KeyFrame frame = new KeyFrame(Duration.millis(delay), e -> step());
		myAnimation = new Timeline();
		myAnimation.setCycleCount(Timeline.INDEFINITE);
		myAnimation.getKeyFrames().add(frame);
		myAnimation.play();
	}

	/**
	 * Pause the animation.
	 */
	public void stop() {
		if (myAnimation != null) {
			myAnimation.pause();
		}
	}

	/**
	 * Created all the UI components.
	 */
	private void setupUI(Stage stage) {
		root = new Group();
		Node controlPanel = new ControlPanel(this, myResources);
		myDisplayGrid = new DisplayGrid(gridSize);
		myDisplayGrid.setLayoutX(DEFAULT_SIZE.width - gridSize);
		placeholder = createPlaceholder();
		root.getChildren().addAll(controlPanel, setupDivider(), myDisplayGrid, placeholder);
		Scene scene = new Scene(root, DEFAULT_SIZE.width, DEFAULT_SIZE.height, Color.WHITE);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * @return A divider for the grid and control panel.
	 */
	private Node setupDivider() {
		Group result = new Group();
		Rectangle separator = new Rectangle(2, DEFAULT_SIZE.height);
		separator.setX(DEFAULT_SIZE.width - gridSize - separator.getWidth());
		separator.setFill(Color.GRAY);
		result.getChildren().add(separator);
		return result;
	}

	/**
	 * @return A placeholder for the grid.
	 */
	private Node createPlaceholder() {
		Group result = new Group();
		Text placeholder = new Text(myResources.getString("PlaceholderGrid"));
		placeholder.setFont(Font.font("Helvetica", FontWeight.BOLD, 80));
		placeholder.setFill(Color.LIGHTGRAY);
		placeholder.setX((gridSize - placeholder.getBoundsInParent().getWidth()) / 2 + DEFAULT_SIZE.width - gridSize);
		placeholder.setY(DEFAULT_SIZE.height / 2 + placeholder.getBoundsInParent().getHeight() / 4);
		result.getChildren().add(placeholder);
		return result;
	}

	/**
	 * Updates the simulation on each time step.
	 */
	public void step() {
		if (mySimulation != null) {
			mySimulation.step();
			updateGrid();
		}
	}

	/**
	 * Initializes the simulation and game.
	 * 
	 * @param game
	 *            A String identifying the game.
	 * @param size
	 *            The size of one side of the grid.
	 */
	public void initializeSimulation(String game) {
		hidePlaceholder();
		mySimulation = new Simulation(game);
	}

	public void displaySimulation(int size) {
		mySimulation.setupGrid(size);
		myDisplayGrid.update(mySimulation.getGrid());
	}

	/**
	 * Shuffles the cells and reloads the current simulation.
	 */
	public void shuffleGrid() {
		if (mySimulation != null) {
			stop();
			mySimulation.shuffle();
			updateGrid();
		}
	}

	/**
	 * Updates the visualization.
	 */
	private void updateGrid() {
		myDisplayGrid.update(mySimulation.getGrid());
	}

	/**
	 * Hides the grid's placeholder.
	 */
	private void hidePlaceholder() {
		root.getChildren().remove(placeholder);
	}

}
