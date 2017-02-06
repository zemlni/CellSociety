package user_interface;

import java.awt.Dimension;
import java.io.File;
import java.util.ResourceBundle;

import cellsociety_team18.Simulation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author elliott This class controls the user interface.
 */
public class ViewController {

	private static final Dimension DEFAULT_SIZE = new Dimension(920, 600);
    public static final String DEFAULT_RESOURCE_PACKAGE = "resources";

	private int delay = 250;
	private int gridSizeInCells = 40;
	private final int gridSizeInPixels = 600;
	
	private Simulation mySimulation;
	private Group root;
	private DisplayGrid myDisplayGrid;
	private Timeline myAnimation;
	private ComboBox<String> myGames;
	private TextField myDelayField;
	private TextField myGridSizeField;
	private Text myDescription;
	private Text myTitle;
	private Node placeholder;
    private ResourceBundle myResources;

	public ViewController(Stage stage) {
		stage.setTitle("CellSociety");
        myResources = ResourceBundle.getBundle("UIStrings");
		setupGrid();
		setupUI(stage);
	}

	/**
	 * Create and start the animation.
	 */
	public void start() {
		KeyFrame frame = new KeyFrame(Duration.millis(Integer.parseInt(myDelayField.getText())), e -> step());
		myAnimation = new Timeline();
		myAnimation.setCycleCount(Timeline.INDEFINITE);
		myAnimation.getKeyFrames().add(frame);
		myAnimation.play();
	}

	/**
	 * Pause the animation.
	 */
	public void stop() {
		myAnimation.pause();
	}

	/**
	 * Created the visual representation of the grid.
	 */
	private void setupGrid() {
		myDisplayGrid = new DisplayGrid(gridSizeInPixels, gridSizeInCells);
	}

	/**
	 * Created all the UI components.
	 */
	private void setupUI(Stage stage) {
		root = new Group();
		Node controls = createControlPanel();
		Node divider = setupDivider();
		placeholder = createPlaceholder();
		root.getChildren().addAll(myDisplayGrid, placeholder, divider, controls);
		Scene scene = new Scene(root, DEFAULT_SIZE.width, DEFAULT_SIZE.height, Color.WHITE);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * @return The control panel.
	 */
	private Node createControlPanel() {
		VBox result = new VBox(16);
		result.setPrefWidth(280);
		result.setLayoutY(20);
		result.setLayoutX(gridSizeInPixels + 20);
		Node labels = setupLabels();
		Node simulationSelector = setupComboBox();
		Separator horizontalSeparator = new Separator();
		Node fields = setupFields();
		Node buttons = setupButtons();
		result.getChildren().addAll(labels, simulationSelector, horizontalSeparator, fields, buttons);
		return result;
	}

	/**
	 * @return A divider for the grid and control panel.
	 */
	private Node setupDivider() {
		Group result = new Group();
		Rectangle separator = new Rectangle(2, DEFAULT_SIZE.height);
		separator.setX(gridSizeInPixels);
		separator.setFill(Color.BLACK);
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
		placeholder.setX((gridSizeInPixels - placeholder.getBoundsInParent().getWidth()) / 2);
		placeholder.setY(DEFAULT_SIZE.height / 2 + placeholder.getBoundsInParent().getHeight() / 4);
		result.getChildren().add(placeholder);
		return result;
	}

	/**
	 * @return The control panel's labels.
	 */
	private Node setupLabels() {
		VBox result = new VBox(16);
		myTitle = new Text(myResources.getString("PlaceholderTitle"));
		myTitle.setFont(Font.font("Helvetica", FontWeight.BOLD, 24));
		myDescription = new Text(myResources.getString("PlaceholderDescription"));
		myDescription.setFont(Font.font("Helvetica", 16));
		myDescription.setWrappingWidth(260);
		myDescription.setTextAlignment(TextAlignment.JUSTIFY);
		Separator separator = new Separator();
		result.getChildren().addAll(myTitle, myDescription, separator);
		return result;
	}

	/**
	 * @return The combo box and its label.
	 */
	private Node setupComboBox() {
		HBox result = new HBox(8);
		Text pick = new Text(myResources.getString("PickString"));
		pick.setFont(Font.font("Helvetica", 16));
		myGames = new ComboBox<String>();
		File folder = new File("data/");
		for (File file : folder.listFiles()) {
			String name = file.getName();
			if (name.substring(name.lastIndexOf('.') + 1).equals("xml")) {
				myGames.getItems().add(name.substring(0, name.length() - 4));
			}
		}
		myGames.valueProperty().addListener(e -> {
			myDisplayGrid.changeSizeInCells(gridSizeInCells);
			if (myAnimation != null)
				stop();
			initializeSimulation(myGames.getValue(), gridSizeInCells);
		});
		result.getChildren().addAll(pick, myGames);
		result.setAlignment(Pos.CENTER_LEFT);
		return result;
	}

	/**
	 * @return The control panel's buttons.
	 */
	private Node setupButtons() {
		VBox result = new VBox(16);
		Node controlButtons = createControlButtons();
		Button reload = new Button(myResources.getString("ReloadTitle"));
		reload.setOnAction(e -> shuffleGrid());
		result.getChildren().addAll(controlButtons, reload);
		return result;
	}
	
	/**
	 * @return The buttons used to control the simulation: start, stop, and step.
	 */
	private Node createControlButtons() {
		HBox result = new HBox(8);
		Button start = new Button(myResources.getString("StartTitle"));
		start.setOnAction(e -> start());
		Button stop = new Button(myResources.getString("StopTitle"));
		stop.setOnAction(e -> stop());
		Separator verticalSeparator = new Separator();
		verticalSeparator.setOrientation(Orientation.VERTICAL);
		Button step = new Button(myResources.getString("StepTitle"));
		step.setOnAction(e -> step());
		result.getChildren().addAll(start, stop, verticalSeparator, step);
		return result;
	}

	/**
	 * @return The control panel's input fields.
	 */
	private Node setupFields() {
		HBox result = new HBox(20);
		result.getChildren().addAll(createDelayBox(), createGridSizeBox());
		return result;
	}

	/**
	 * @return A box containing the delay text field and its label.
	 */
	private Node createDelayBox() {
		HBox delayBox = new HBox(8);
		Label delayLabel = new Label(myResources.getString("DelayString"));
		myDelayField = new TextField();
		myDelayField.setText(Integer.toString(delay));
		myDelayField.setPrefWidth(55);
		delayBox.getChildren().addAll(delayLabel, myDelayField);
		delayBox.setAlignment(Pos.CENTER_LEFT);
		return delayBox;
	}

	/**
	 * @return A box containing the grid size text field and its label.
	 */
	private Node createGridSizeBox() {
		HBox gridSizeBox = new HBox(8);
		Label gridSizeLabel = new Label(myResources.getString("GridSizeString"));
		myGridSizeField = new TextField();
		myGridSizeField.setText(Integer.toString(gridSizeInCells));
		myGridSizeField.setPrefWidth(55);
		myGridSizeField.textProperty().addListener(e -> {
			if (myGridSizeField.getText().length() > 0) {
				gridSizeInCells = Integer.parseInt(myGridSizeField.getText());
				myDisplayGrid.changeSizeInCells(gridSizeInCells);
				initializeSimulation(myGames.getValue(), gridSizeInCells);
			}
		});
		gridSizeBox.getChildren().addAll(gridSizeLabel, myGridSizeField);
		gridSizeBox.setAlignment(Pos.CENTER_LEFT);
		return gridSizeBox;
	}

	/**
	 * Updates the simulation on each time step.
	 */
	private void step() {
		mySimulation.step();
		updateGrid();
	}

	/**
	 * Initializes the simulation and game.
	 * 
	 * @param game A String identifying the game.
	 * @param size The size of one side of the grid.
	 */
	private void initializeSimulation(String game, int size) {
		hidePlaceholder();
		mySimulation = new Simulation(game, size);
		myTitle.setText(mySimulation.getGrid().getGame().getTitle());
		myDescription.setText(mySimulation.getGrid().getGame().getDescription());
		myDisplayGrid.update(mySimulation.getGrid());
	}

	/**
	 * Shuffles the cells and reloads the current simulation.
	 */
	private void shuffleGrid() {
		stop();
		mySimulation.shuffle();
		updateGrid();
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
