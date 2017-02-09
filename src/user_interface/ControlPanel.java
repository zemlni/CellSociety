/**
 * 
 */
package user_interface;

import cellsociety_team18.Parameter;

import java.io.File;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * @author elliott This class represents the Control Panel of the UI. This
 *         Control Panel is comprised of description labels, combo boxes, and
 *         action buttons.
 */
public class ControlPanel extends VBox {

	private final static int HORIZONTAL_SPACING = 8;
	private final static int VERTICAL_SPACING = 16;

	private ViewController viewController;
	private ResourceBundle resources;

	private Text myDescription;
	private Text myTitle;
	private ComboBox<String> myGames;
	private TextField myDelayField;
	private TextField myGridSizeField;
	private ParameterTable myParameterTable;

	private boolean gameLoaded = false;

	/**
	 * 
	 */
	public ControlPanel(ViewController viewController, ResourceBundle resources) {
		super(VERTICAL_SPACING);
		this.viewController = viewController;
		this.resources = resources;
		setPrefWidth(280);
		setLayoutY(20);
		setLayoutX(20);
		setup();
	}

	private void setup() {
		Node labels = setupLabels();
		Node simulationSelector = setupSimulationSelector();
		getChildren().addAll(labels, simulationSelector);
	}

	private void addControls() {
		if (gameLoaded == false) {
			Node configurationFields = setupFields();
			Node controlButtons = setupControlButtons();
			Node showGraph = makeButton("ShowGraphTitle", e -> viewController.start());
			getChildren().addAll(configurationFields, makeSeparator(false), controlButtons, makeSeparator(false), showGraph);
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
		Button loadButton = makeButton("LoadTitle", e -> loadGame());
		result.getChildren().addAll(pick, myGames, loadButton);
		result.setAlignment(Pos.CENTER_LEFT);
		return result;
	}

	private void readGamesFromDisk() {
		File folder = new File("data/");
		for (File file : folder.listFiles()) {
			String name = file.getName();
			if (name.substring(name.lastIndexOf('.') + 1).equals("xml")) {
				myGames.getItems().add(name.substring(0, name.length() - 4));
			}
		}
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
		result.getChildren().addAll(createDelaySettings(), createGridSizeSettings());
		return result;
	}

	private Node createDelaySettings() {
		HBox box = new HBox(HORIZONTAL_SPACING);
		Label label = new Label(resources.getString("DelayString"));
		myDelayField = createField(Integer.toString(viewController.getDelay()));
		box.getChildren().addAll(label, myDelayField);
		box.setAlignment(Pos.CENTER_LEFT);
		return box;
	}

	private Node createGridSizeSettings() {
		HBox box = new HBox(HORIZONTAL_SPACING);
		Label label = new Label(resources.getString("GridSizeString"));
		myGridSizeField = createField(Integer.toString(viewController.getGridSizeInCells()));
		box.getChildren().addAll(label, myGridSizeField);
		box.setAlignment(Pos.CENTER_LEFT);
		return box;
	}

	private TextField createField(String value) {
		TextField field = new TextField(value);
		field.setPrefWidth(55);
		return field;
	}

	private ParameterTable createParameters() {
		return new ParameterTable(viewController.getGame().getParametersAndValues());
	}

	private void setupParameters() {
		getChildren().remove(myParameterTable);
		myParameterTable = createParameters();
		getChildren().add(2, myParameterTable);
	}

	private void newSimulation() {
		viewController.initializeSimulation(myGames.getValue());
		myTitle.setText(viewController.getTitle());
		myDescription.setText(viewController.getDescription());
		addControls();
		setupParameters();
	}

	private void loadGame() {
		if (didUpdateGameParameters()) {
			viewController.setDelay(Integer.parseInt(myDelayField.getText()));
			viewController.getGrid().changeSizeInCells(Integer.parseInt(myGridSizeField.getText()));
			viewController.stop();
			viewController.displaySimulation(viewController.getGridSizeInCells());
		}
	}

	private Boolean didUpdateGameParameters() {
		ObservableList<Parameter> data = myParameterTable.getData();
		for (Parameter entry : data) {
			viewController.getGame().setParameter(entry.getParameter(), entry.getValue());
		}
		return true;
	}

	public void showError(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(resources.getString("ErrorTitle"));
		alert.setContentText(message);
		alert.showAndWait();
	}

}
