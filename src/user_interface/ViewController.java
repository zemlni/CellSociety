package user_interface;

import java.awt.Dimension;
import java.io.File;

import javax.activation.MimetypesFileTypeMap;

import cellsociety_team18.Grid;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ViewController {

	private static final Dimension DEFAULT_SIZE = new Dimension(920, 600);

	private int delay = 250;
	private int gridSizeInCells = 40;
	private final int gridSizeInPixels = 600;

	private Simulation mySimulation;
	private DisplayGrid myDisplayGrid;
	private Timeline myAnimation;

	private ComboBox<String> myGames;
	private TextField delayField;
	private TextField gridSizeField;
	
	private Text description;
	private Text title;

	public ViewController(Stage stage) {
		stage.setTitle("CellSociety");
		setupGrid();
		setupUI(stage);
	}

	public void start() {
		KeyFrame frame = new KeyFrame(Duration.millis(Integer.parseInt(delayField.getText())), e -> step());
		myAnimation = new Timeline();
		myAnimation.setCycleCount(Timeline.INDEFINITE);
		myAnimation.getKeyFrames().add(frame);		myAnimation.play();
	}
	
	public void stop() {
		myAnimation.pause();
	}
	//changed signature here. you were taking a stage but weren't doing anything with it. 
	private void setupGrid() {
		myDisplayGrid = new DisplayGrid(gridSizeInPixels, gridSizeInCells);
	}
	
	private void setupUI(Stage stage) {
		Group root = new Group();
		Node controls = createControlPanel();
		root.getChildren().addAll(myDisplayGrid, controls);
		Scene scene = new Scene(root, DEFAULT_SIZE.width, DEFAULT_SIZE.height, Color.WHITE);
		stage.setScene(scene);
		stage.show();
	}

	private Node createControlPanel() {
		VBox result = new VBox(16);
		result.setPrefWidth(280);
		result.setLayoutY(20);
		result.setLayoutX(gridSizeInPixels + 20);

		Node labels = setupLabels();
		Node simulationSelector = setupComboBox();
		Node fields = setupFields();
		Node buttons = setupButtons();

		result.getChildren().addAll(labels, simulationSelector, fields, buttons);

		return result;
	}
	
	private Node setupLabels() {
		VBox result = new VBox(16);
		title = new Text("Simulation Game");
		title.setFont(Font.font("Helvetica", FontWeight.BOLD, 24));
		description = new Text(
				"Select the simulation below to begin:");
		description.setFont(Font.font("Helvetica", 16));
		description.setWrappingWidth(260);
		description.setTextAlignment(TextAlignment.JUSTIFY);
		result.getChildren().addAll(title, description);
		return result;
	}

	private Node setupComboBox() {
		HBox result = new HBox(8);
		Text pick = new Text("Pick a game:");
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
			initializeSimulation(myGames.getValue(), gridSizeInCells);
		});
		result.getChildren().addAll(pick, myGames);
		result.setAlignment(Pos.CENTER_LEFT);
		return result;
	}

	private Node setupButtons() {
		VBox result = new VBox(16);
		HBox startStop = new HBox(8);
		Button start = new Button("Start");
		start.setOnAction(e -> start());
		Button stop = new Button("Stop");
		stop.setOnAction(e -> stop());
		Separator separator = new Separator();
		separator.setOrientation(Orientation.VERTICAL);
		Button step = new Button("Step");
		step.setOnAction(e -> step());
		startStop.getChildren().addAll(start, stop, separator, step);
		Button shuffle = new Button("Shuffle Cells");
		shuffle.setOnAction(e -> shuffleGrid());
		result.getChildren().addAll(startStop, shuffle);
		return result;
	}
	
	private Node setupFields() {
		HBox result = new HBox(20);
		Label delayLabel = new Label("Delay (ms):");
		delayField = new TextField();
		delayField.setText(Integer.toString(delay));
		delayField.setPrefWidth(55);
		HBox delayBox = new HBox(8);
		delayBox.getChildren().addAll(delayLabel, delayField);
		delayBox.setAlignment(Pos.CENTER_LEFT);
		
		Label gridSizeLabel = new Label("Grid Size:");
		gridSizeField = new TextField();
		gridSizeField.setText(Integer.toString(gridSizeInCells));
		gridSizeField.setPrefWidth(40);
		gridSizeField.textProperty().addListener(e -> {
			if (gridSizeField.getText().length() > 0)
				gridSizeInCells = Integer.parseInt(gridSizeField.getText());
		});
		HBox gridSizeBox = new HBox(8);
		gridSizeBox.getChildren().addAll(gridSizeLabel, gridSizeField);
		gridSizeBox.setAlignment(Pos.CENTER_LEFT);
		result.getChildren().addAll(delayBox, gridSizeBox);
		return result;
	}
	
	private void step() {
		mySimulation.step();
		updateGrid();
	}

	private void initializeSimulation(String game, int size) {
		mySimulation = new Simulation(game, size); //CREATE THIS CONSTRUCTOR
		//mySimulation = new Simulation(1, 0);
		title.setText(mySimulation.getGrid().getGame().getTitle());
		description.setText(mySimulation.getGrid().getGame().getDescription());
		myDisplayGrid.update(mySimulation.getGrid());
	}
	
	private void shuffleGrid() {
		mySimulation.shuffle();
		updateGrid();
	}
	
	private void updateGrid() {
		myDisplayGrid.update(mySimulation.getGrid());
	}

}
