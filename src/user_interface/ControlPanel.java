/**
 * 
 */
package user_interface;

import cellsociety_team18.Game;
import cellsociety_team18.Parameter;
import cellsociety_team18.Simulation;
import cellsociety_team18.XMLParser;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
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
public class ControlPanel extends ScrollPane {

	private final static int HORIZONTAL_SPACING = 8;
	private final static int VERTICAL_SPACING = 16;

	private ViewController viewController;
	private Simulation myCurrentSimulation;
	private ResourceBundle resources;
	private String dataFolder = "data/";

	private VBox panelElements;

	private Text myDescription;
	private Text myTitle;
	private ComboBox<String> myGames;
	private ComboBox<String> myConfigurations;
	private Slider myDelaySlider;
	private TextField myGridSizeField;
	private ToggleButton myOutlineToggle;
	private ComboBox<String> myGridEdges;
	private ComboBox<String> myCells;
	private ComboBox<String> myCellDistributions;
	private TextField myNeighborsField;
	private TextField myCellSizeField;
	private Node myConfigurationControls;
	private ParameterTable myParameterTable;

	private boolean gameLoaded = false;

	/**
	 * @param viewController
	 *            The owner of the control panel.
	 * @param resources
	 *            The ResourceBundle for the control panel.
	 * @param width
	 *            The control panel's width.
	 * @return A control panel.
	 */
	public ControlPanel(ViewController viewController, ResourceBundle resources, int width) {
		this.viewController = viewController;
		this.resources = resources;
		setWidth(width);
		setHbarPolicy(ScrollBarPolicy.NEVER);
		setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		setup();
	}

	private Game getGame() {
		return myCurrentSimulation.getGame();
	}

	private void setup() {
		panelElements = new VBox(VERTICAL_SPACING);
		panelElements.setPadding(new Insets(20, 30, 20, 20));
		setContent(panelElements);
		panelElements.getChildren().addAll(getGameLabels(), getSimulationSelector(), getConfigurationSelector());
		getChildren().addAll(panelElements, getDivider());
	}

	/**
	 * @return A divider for the grid and control panel.
	 */
	private Node getDivider() {
		Group result = new Group();
		Rectangle separator = new Rectangle(1, 500);
		separator.setFill(Color.LIGHTGRAY);
		result.getChildren().add(separator);
		return result;
	}

	private void addControls() {
		if (gameLoaded == false) {
			myConfigurationControls = createConfigurationControls();
			HBox box = new HBox(HORIZONTAL_SPACING);
			box.getChildren().addAll(makeButton("LoadTitle", e -> loadGame()),
					makeButton("SaveTitle", e -> saveConfiguration()));
			Node controlButtons = setupControlButtons();
			panelElements.getChildren().addAll(myConfigurationControls, makeSeparator(false), box, makeSeparator(false),
					controlButtons, getDelaySlider());
			gameLoaded = true;
		}
	}

	/**
	 * @return The control panel's game labels.
	 */
	private Node getGameLabels() {
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
	 * @return The game combo box and its label.
	 */
	private Node getSimulationSelector() {
		HBox result = new HBox(HORIZONTAL_SPACING);
		myGames = new ComboBox<String>();
		myGames.valueProperty().addListener(e -> newSimulation());
		addOptionsFromDisk(myGames, false);
		result.getChildren().addAll(addLabelToNode(myGames, "PickGameString"));
		result.setAlignment(Pos.CENTER_LEFT);
		return result;
	}

	/**
	 * @return The configuration combo box and its label.
	 */
	private Node getConfigurationSelector() {
		HBox result = new HBox(HORIZONTAL_SPACING);
		myConfigurations = new ComboBox<String>();
		myConfigurations.valueProperty().addListener(e -> newSimulation());
		addOptionsFromDisk(myConfigurations, true);
		result.getChildren().addAll(addLabelToNode(myConfigurations, "PickConfigurationString"));
		result.setAlignment(Pos.CENTER_LEFT);
		return result;
	}

	private void addOptionsFromDisk(ComboBox<String> box, boolean configurationFiles) {
		List<File> options = Arrays.asList(new File(dataFolder).listFiles());
		Collections.sort(options);
		for (File file : options) {
			String name = file.getName();
			if (XMLParser.isXMLFile(name)) {
				if (configurationFiles && name.contains("Configuration")
						|| !(configurationFiles || name.contains("Configuration"))) {
					box.getItems().add(name.substring(0, name.length() - 4));
				}
			}
		}
	}

	/**
	 * @return The control panel's buttons.
	 */
	private Node setupControlButtons() {
		HBox result = new HBox(HORIZONTAL_SPACING);
		Button start = makeButton("StartTitle", e -> viewController.start((int) myDelaySlider.getValue()));
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
	private Node createConfigurationControls() {

		VBox result = new VBox(VERTICAL_SPACING);

		HBox sizeBox = makeSizeBox();

		HBox cellBox = new HBox(HORIZONTAL_SPACING);
		myCells = createComboBox(new String[] { "Square", "Triangle", "Hexagon" },
				myCurrentSimulation.getSettings().getParameter("cellType"));
		cellBox.getChildren().addAll(addLabelToNode(myCells, "CellType"));
		cellBox.setAlignment(Pos.CENTER_LEFT);

		HBox edgeBox = makeEdgeBox();
		HBox distributionBox = makeDistributionBox();

		myNeighborsField = createField(myCurrentSimulation.getSettings().getParameter("numberOfNeighbors"));

		myOutlineToggle = new ToggleButton(resources.getString("OutlineGrid"));
		myOutlineToggle
				.setSelected(Boolean.parseBoolean(myCurrentSimulation.getSettings().getParameter("outlineGrid")));

		result.getChildren().addAll(sizeBox, myOutlineToggle, cellBox, edgeBox, distributionBox,
				addLabelToNode(myNeighborsField, "NumberNeighbors"));
		return result;

	}

	private HBox makeSizeBox() {
		HBox sizeBox = new HBox(HORIZONTAL_SPACING);
		myGridSizeField = createField(myCurrentSimulation.getSettings().getParameter("gridSize"));
		myCellSizeField = createField(myCurrentSimulation.getSettings().getParameter("cellSize"));
		sizeBox.getChildren().addAll(addLabelToNode(myGridSizeField, "GridSizeString"),
				addLabelToNode(myCellSizeField, "CellSize"));
		return sizeBox;
	}

	private HBox makeEdgeBox() {
		HBox edgeBox = new HBox(HORIZONTAL_SPACING);
		myGridEdges = new ComboBox<String>();
		myGridEdges.getItems().addAll("Bounded", "Toroidal");
		myGridEdges.setValue(myCurrentSimulation.getSettings().getParameter("gridEdge"));
		edgeBox.getChildren().addAll(addLabelToNode(myGridEdges, "GridEdge"));
		edgeBox.setAlignment(Pos.CENTER_LEFT);
		return edgeBox;
	}

	private HBox makeDistributionBox() {
		HBox distributionBox = new HBox(HORIZONTAL_SPACING);
		myCellDistributions = new ComboBox<String>();
		myCellDistributions.getItems().addAll("Probabilistic", "Random");
		myCellDistributions.setValue(myCurrentSimulation.getSettings().getParameter("cellDistribution"));
		distributionBox.getChildren().addAll(addLabelToNode(myCellDistributions, "CellDistribution"));
		distributionBox.setAlignment(Pos.CENTER_LEFT);
		return distributionBox;
	}

	private ComboBox<String> createComboBox(String[] values, String value) {
		ComboBox<String> result = new ComboBox<String>();
		result.getItems().addAll(values);
		result.setValue(value);
		return result;
	}

	private TextField createField(String value) {
		TextField field = new TextField(value);
		field.setPrefWidth(40);
		return field;
	}

	private Node addLabelToNode(Node node, String property) {
		HBox box = new HBox(HORIZONTAL_SPACING);
		Label label = new Label(resources.getString(property));
		box.getChildren().addAll(label, node);
		box.setAlignment(Pos.CENTER_LEFT);
		return box;
	}

	private ParameterTable createParameters() {
		return new ParameterTable(getGame().getParametersAndValues());
	}

	private void setupParameters() {
		panelElements.getChildren().remove(myParameterTable);
		panelElements.getChildren().remove(myConfigurationControls);
		myParameterTable = createParameters();
		myConfigurationControls = createConfigurationControls();
		panelElements.getChildren().add(3, myParameterTable);
		panelElements.getChildren().add(4, myConfigurationControls);
	}

	private void newSimulation() {
		if (myGames.getValue() != null && myConfigurations.getValue() != null) {
			getSimulation();
			myTitle.setText(getGame().getTitle());
			myDescription.setText(getGame().getDescription());
			addControls();
			setupParameters();
		}
	}

	private void loadGame() {
		if (checkParameters()) {
			updateParameters();
			viewController.stop();
			viewController.displaySimulation(myCurrentSimulation);
			getSimulation();
		}
	}

	private void getSimulation() {
		myCurrentSimulation = viewController.newSimulation(myGames.getValue(), myConfigurations.getValue());
	}

	private void updateGameParameters() {
		ObservableList<Parameter> data = myParameterTable.getData();
		for (Parameter entry : data) {
			getGame().getSettings().put(entry.getParameter(), entry.getValue());
		}
	}

	private Node makeLabel(String property, int fontSize) {
		Text label = new Text(resources.getString(property));
		label.setFont(Font.font("Helvetica", fontSize));
		return label;
	}

	private Node getDelaySlider() {
		configureSlider();
		VBox box = new VBox(0);
		box.getChildren().addAll(myDelaySlider, makeLabel("DelayString", 14));
		box.setAlignment(Pos.CENTER);
		return box;
	}

	private void configureSlider() {
		myDelaySlider = new Slider();
		myDelaySlider.setMin(20);
		myDelaySlider.setMax(1000);
		myDelaySlider.setValue(myCurrentSimulation.getSettings().getIntParameter("delay"));
		myDelaySlider.setShowTickLabels(true);
		myDelaySlider.setShowTickMarks(false);
		myDelaySlider.setMajorTickUnit(500);
		myDelaySlider.setMinorTickCount(0);
		myDelaySlider.setBlockIncrement(10);
	}

	private void updateParameters() {
		updateGameParameters();
		updateConfigurationParameters();
	}

	private void updateConfigurationParameters() {
		myCurrentSimulation.getSettings().put("gridSize", myGridSizeField.getText());
		myCurrentSimulation.getSettings().put("cellType", myCells.getValue());
		myCurrentSimulation.getSettings().put("gridEdge", myGridEdges.getValue());
		myCurrentSimulation.getSettings().put("cellDistribution", myCellDistributions.getValue());
		myCurrentSimulation.getSettings().put("cellSize", myCellSizeField.getText());
		myCurrentSimulation.getSettings().put("outlineGrid", String.valueOf(myOutlineToggle.isSelected()));
		myCurrentSimulation.getSettings().put("numberOfNeighbors", myNeighborsField.getText());
	}

	private void saveConfiguration() {
		if (checkParameters()) {
			updateConfigurationParameters();
			XMLParser.write(myCurrentSimulation.getSettings().getMap());
			resetConfigurations();
		}
	}

	private void resetConfigurations() {
		String current = myConfigurations.getValue();
		myConfigurations.getItems().removeAll(myConfigurations.getItems());
		addOptionsFromDisk(myConfigurations, true);
		myConfigurations.setValue(current);
	}

	private void showMessage() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Error");
		alert.setHeaderText("CellSociety encountered an error.");
		alert.setContentText("One of your input values is incomplete or of the wrong type.");
		alert.showAndWait();
	}

	private Boolean checkParameters() {
		try {
			Integer.parseInt(myGridSizeField.getText());
			Integer.parseInt(myNeighborsField.getText());
			Double.parseDouble(myCellSizeField.getText());
		} catch (Exception e) {
			showMessage();
			return false;
		}
		return true;
	}

}
