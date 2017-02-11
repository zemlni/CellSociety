package user_interface;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import cellsociety_team18.Game;
import cellsociety_team18.Simulation;
import cellsociety_team18.State;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
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
import javafx.scene.control.ChoiceDialog;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;

/**
 * @author elliott This class controls the user interface.
 */
public class ViewController {

	private final Dimension DEFAULT_SIZE = new Dimension(780, 600);
	private final String DEFAULT_RESOURCE_PACKAGE = "resources";
	private final int gridSize = 460;

	private int delay = 250;

	private Simulation mySimulation;
	private Group center;
	private DisplayGrid myDisplayGrid;
	private PopulationGraph myGraph;
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
		Scene scene = new Scene(createPane(), DEFAULT_SIZE.width, DEFAULT_SIZE.height, Color.WHITE);
		stage.setScene(scene);
		stage.show();
	}

	private BorderPane createPane() {
		BorderPane borderPane = new BorderPane();
		borderPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		ControlPanel controlPanel = new ControlPanel(this, myResources);
		borderPane.setLeft(controlPanel);
		center = new Group();
		// TODO: Make me this constructor pls gridType = string from combo box
		// that you will make for selecting grids
		// myDisplayGrid = new DisplayGrid(this, gridSize, gridType);
		myDisplayGrid = new DisplayGrid(this, gridSize, "Hexagon");
		placeholder = createPlaceholder();
		center.getChildren().addAll(myDisplayGrid, placeholder);
		borderPane.setCenter(center);
		myGraph = new PopulationGraph(DEFAULT_SIZE.width);
		borderPane.setBottom(myGraph);
		return borderPane;
	}

	/**
	 * @return A placeholder for the grid.
	 */
	private Node createPlaceholder() {
		Group result = new Group();
		Text placeholder = new Text(myResources.getString("PlaceholderGrid"));
		placeholder.setFont(Font.font("Helvetica", FontWeight.BOLD, 80));
		placeholder.setFill(Color.LIGHTGRAY);
		placeholder.setX((gridSize - placeholder.getBoundsInParent().getWidth()) / 2);
		placeholder.setY(gridSize / 2 + placeholder.getBoundsInParent().getHeight() / 4);
		result.getChildren().add(placeholder);
		return result;
	}

	/**
	 * Updates the simulation on each time step.
	 */
	public void step() {
		if (mySimulation != null) {
			mySimulation.step();
			myGraph.update(mySimulation.getProportions());
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
		mySimulation = new Simulation(game);
	}

	public void displaySimulation(int size) {
		hidePlaceholder();
		//TODO: fix this so it takes the variable instead of constant 
		mySimulation.setupGrid(size, "Hexagon");
		mySimulation.getGame().setStates();
		myDisplayGrid.update(mySimulation.getGrid());
	}

	/**
	 * Shuffles the cells and reloads the current simulation.
	 */
	public void shuffleGrid() {
		if (mySimulation != null) {
			stop();
			mySimulation.shuffle();
			myGraph.clear();
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
		center.getChildren().remove(placeholder);
	}

	// public void cellClicked(GraphicCell graphicCell) {
	public void cellClicked(GraphicPolygon cell) {
		if (myAnimation == null || myAnimation.getStatus() == Animation.Status.PAUSED) {
			Map<String, State> states = mySimulation.getGame().getStates();
			ChoiceDialog<String> dialog = new ChoiceDialog<>(GraphicPolygon.getStateName(states, cell),
					states.keySet());
			dialog.setTitle("State");
			dialog.setHeaderText("Each cell can have several states.");
			dialog.setContentText("Choose your cell's state:");
			Optional<String> result = dialog.showAndWait();
			result.ifPresent(picked -> {
				cell.update(states.get(picked));
				updateGrid();
			});
		}
	}

}
