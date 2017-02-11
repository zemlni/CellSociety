package user_interface;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

import cellsociety_team18.Simulation;
import cellsociety_team18.SimulationController;
import cellsociety_team18.State;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
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

	private final Dimension DEFAULT_SIZE = new Dimension(900, 500);
	private final String DEFAULT_RESOURCE_PACKAGE = "resources";
	private final int GRID_SIZE = 400;
	private final int CONTROL_PANEL_WIDTH = 300;
	
	private int delay = 250;

	private SimulationController mySimulationController = new SimulationController();
	private ArrayList<SimulationView> mySimulationViews = new ArrayList<SimulationView>();

	private PopulationGraph myGraph;
	private Timeline myAnimation;
	private ResourceBundle myResources = ResourceBundle.getBundle("UIStrings");

	public ViewController(Stage stage) {
		stage.setTitle("CellSociety");
		stage.setResizable(false);
		setupUI(stage);
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
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
		ControlPanel controlPanel = new ControlPanel(this, myResources, CONTROL_PANEL_WIDTH);
		borderPane.setLeft(controlPanel);
		myGraph = new PopulationGraph(DEFAULT_SIZE.width - CONTROL_PANEL_WIDTH, DEFAULT_SIZE.height);
		borderPane.setCenter(myGraph);
		return borderPane;
	}

	/**
	 * Updates the simulation on each time step.
	 */
	public void step() {
		mySimulationController.step();
		myGraph.update(mySimulationController.getProportions());
		updateGrids();
	}

	/**
	 * Initializes the simulation and game.
	 * @param game A String identifying the game.
	 */
	public Simulation newSimulation(String game) {
		return mySimulationController.create(game);
	}
	
	public void displaySimulation(Simulation simulation, int size) {
		simulation.setupGrid(size);
		simulation.getGame().setStates();
		mySimulationController.add(simulation);
		DisplayGrid grid = new DisplayGrid(this, simulation, GRID_SIZE);;
		grid.update(simulation.getGrid());
		SimulationView view = new SimulationView(simulation, grid, GRID_SIZE, mySimulationViews.size() + 1);
		view.setOnCloseRequest(new EventHandler<WindowEvent>() {
	          public void handle(WindowEvent we) {
	              stop();
	        	  mySimulationController.remove(view.getSimulation());
	              mySimulationViews.remove(view);
	              myGraph.update(mySimulationController.getProportions());
	          }
	      });     
        mySimulationViews.add(view);
	}

	/**
	 * Shuffles the cells and reloads the current simulation.
	 */
	public void shuffleGrid() {
		stop();
		mySimulationController.shuffle();
		myGraph.clear();
		updateGrids();
	}

	/**
	 * Updates the visualizations.
	 */
	private void updateGrids() {
		for (SimulationView view: mySimulationViews) {
			view.update();
		}
	}

	public void cellClicked(GraphicCell graphicCell, Simulation simulation) {
		if (myAnimation == null || myAnimation.getStatus() == Animation.Status.PAUSED) {
			HashMap<String, State> states = simulation.getGame().getStates();
			ChoiceDialog<String> dialog = new ChoiceDialog<>(GraphicCell.getStateName(states, graphicCell),
					states.keySet());
			dialog.setTitle("State");
			dialog.setHeaderText("Each cell can have several states.");
			dialog.setContentText("Choose your cell's state:");
			Optional<String> result = dialog.showAndWait();
			result.ifPresent(picked -> {
				graphicCell.update(states.get(picked));
				updateGrids();
			});
		}
	}

}
