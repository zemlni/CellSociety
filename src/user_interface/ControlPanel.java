/**
 * 
 */
package user_interface;

import cellsociety_team18.Game;
import cellsociety_team18.Parameter;
import cellsociety_team18.Simulation;

import java.io.File;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
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

/**
 * @author elliott This class represents the Control Panel of the UI. This
 *         Control Panel is comprised of description labels, combo boxes, and
 *         action buttons.
 */
public class ControlPanel extends HBox {

	private final static int HORIZONTAL_SPACING = 8;
	private final static int VERTICAL_SPACING = 16;
	private final static int DEFAULT_GRID_SIZE_IN_CELLS = 40;

	private ViewController viewController;
	private Simulation myCurrentSimulation;
	private ResourceBundle resources;

	private VBox panelElements;
	private Text myDescription;
	private Text myTitle;
	private ComboBox<String> myGames;
	private TextField myDelayField;
	private TextField myGridSizeField;
	private ParameterTable myParameterTable;
	private boolean gameLoaded = false;

	/**
	 * @param viewController The owner of the control panel.
	 * @param resources The ResourceBundle for the control panel.
	 * @param width The control panel's width.
	 * @return A control panel.
	 */
	public ControlPanel(ViewController viewController, ResourceBundle resources, int width) {
		super(HORIZONTAL_SPACING);
		this.viewController = viewController;
		this.resources = resources;
		setPrefWidth(width);
		setPadding(new Insets(20, 0, 0, 20));
		setup();
	}

	private void setup() {
		panelElements = new VBox(VERTICAL_SPACING);
		Node labels = setupLabels();
		Node simulationSelector = setupSimulationSelector();
		panelElements.getChildren().addAll(labels, simulationSelector);
		getChildren().addAll(panelElements, setupDivider());
	}
	
	/**
	 * @return A divider for the grid and control panel.
	 */
	private Node setupDivider() {
		Group result = new Group();
		Rectangle separator = new Rectangle(1, 400);
		separator.setFill(Color.LIGHTGRAY);
		result.getChildren().add(separator);
		return result;
	}

	private void addControls() {
		if (gameLoaded == false) {
			Node configurationFields = setupFields();
			Button loadButton = makeButton("LoadTitle", e -> loadGame());
			Node controlButtons = setupControlButtons();
			panelElements.getChildren().addAll(configurationFields, loadButton, makeSeparator(false), controlButtons);
			gameLoaded = true;
		}
	}

	/**
	 * @return The control panel's labels.
	 */
	private Node setupLabels() {
		VBox result = new VBox(VERTICAL_SPACING);
		myTitle = new Text(resources.getString("PlaceholderTitle"));
		myTitle.setFont(Font.font("Helvetica", FontWeight.BOLD, 24));
		myDescription = new Text(resources.getString("PlaceholderDescription"));
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
	private Node setupSimulationSelector() {
		HBox result = new HBox(HORIZONTAL_SPACING);
		Text pick = new Text(resources.getString("PickString"));
		pick.setFont(Font.font("Helvetica", 16));
		myGames = new ComboBox<String>();
		myGames.valueProperty().addListener(e -> newSimulation());
		readGamesFromDisk();
		result.getChildren().addAll(pick, myGames);
		result.setAlignment(Pos.CENTER_LEFT);
		return result;
	}

	private void readGamesFromDisk() {
		File folder = new File("data/");
		for (File file : folder.listFiles()) {
			String name = file.getName();
			if (isXMLFile(name)) {
				myGames.getItems().add(name.substring(0, name.length() - 4));
			}
		}
	}
	
	private boolean isXMLFile(String fileName) {
		return fileName.substring(fileName.lastIndexOf('.') + 1).equals("xml");
	}

	/**
	 * @return The control panel's buttons.
	 */
	private Node setupControlButtons() {
		HBox result = new HBox(HORIZONTAL_SPACING);
		Button start = makeButton("StartTitle", e -> viewController.start());
		Button stop = makeButton("StopTitle", e -> viewController.stop());
		Button step = makeButton("StepTitle", e -> viewController.step());
		Button reload = makeButton("ReloadTitle", e -> viewController.shuffleGrid());
		result.getChildren().addAll(start, makeSeparator(true), stop, makeSeparator(true), step, makeSeparator(true),
				reload);
		return result;
	}

	private Button makeButton(String property, EventHandler<ActionEvent> handler) {
		Button button = new Button(resources.getString(property));
		button.setOnAction(handler);
		return button;
	}

	private Node makeSeparator(boolean vertical) {
		Separator separator = new Separator();
		if (vertical) {
			separator.setOrientation(Orientation.VERTICAL);
		}
		return separator;
	}

	/**
	 * @return The control panel's input fields.
	 */
	private Node setupFields() {
		HBox result = new HBox(20);
		myDelayField = createField(Integer.toString(viewController.getDelay()));
		myGridSizeField = createField(Integer.toString(DEFAULT_GRID_SIZE_IN_CELLS));
		result.getChildren().addAll(addLabelToField(myDelayField, "DelayString"), addLabelToField(myGridSizeField, "GridSizeString"));
		return result;
	}

	private TextField createField(String value) {
		TextField field = new TextField(value);
		field.setPrefWidth(55);
		return field;
	}

	private Node addLabelToField(TextField field, String property) {
		HBox box = new HBox(HORIZONTAL_SPACING);
		Label label = new Label(resources.getString(property));
		box.getChildren().addAll(label, field);
		box.setAlignment(Pos.CENTER_LEFT);
		return box;
	}
	
	private ParameterTable createParameters() {
		return new ParameterTable(getGame().getParametersAndValues());
	}

	private void setupParameters() {
		panelElements.getChildren().remove(myParameterTable);
		myParameterTable = createParameters();
		panelElements.getChildren().add(2, myParameterTable);
	}

	private void newSimulation() {
		getSimulation();
		myTitle.setText(getGame().getTitle());
		myDescription.setText(getGame().getDescription());
		addControls();
		setupParameters();
	}

	private void loadGame() {
		if (didUpdateGameParameters()) {
			viewController.setDelay(Integer.parseInt(myDelayField.getText()));
			viewController.stop();
			viewController.displaySimulation(myCurrentSimulation, Integer.parseInt(myGridSizeField.getText()), "Triangle");
			getSimulation();
		}
	}
	
	private void getSimulation() {
		myCurrentSimulation = viewController.newSimulation(myGames.getValue());
	}

	private Boolean didUpdateGameParameters() {
		ObservableList<Parameter> data = myParameterTable.getData();
		for (Parameter entry : data) {
			getGame().setParameter(entry.getParameter(), entry.getValue());
		}
		return true;
	}

	private Game getGame() {
		return myCurrentSimulation.getGame();
	}
	
}
