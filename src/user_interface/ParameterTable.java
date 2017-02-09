package user_interface;

import cellsociety_team18.Parameter;

import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class ParameterTable extends TableView<Parameter> {
	
	private ObservableList<Parameter> data;

	@SuppressWarnings("unchecked")
	public ParameterTable(Map<String, String> parameters) {

		setEditable(true);
		setPrefHeight(100);
		setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		TableColumn<Parameter, String> parameterCol = new TableColumn<Parameter, String>("Parameter");
		parameterCol.setCellValueFactory(new PropertyValueFactory<Parameter, String>("parameter"));

		TableColumn<Parameter, String> valueCol = new TableColumn<Parameter, String>("Value");
		valueCol.setCellValueFactory(new PropertyValueFactory<Parameter, String>("value"));

		valueCol.setCellFactory(TextFieldTableCell.forTableColumn());
		valueCol.setOnEditCommit(new EventHandler<CellEditEvent<Parameter, String>>() {
			@Override
			public void handle(CellEditEvent<Parameter, String> t) {
				((Parameter) t.getTableView().getItems().get(t.getTablePosition().getRow())).setValue(t.getNewValue());
			}
		});

		getColumns().addAll(parameterCol, valueCol);

		data = FXCollections.observableArrayList();
		for (HashMap.Entry<String, String> entry : parameters.entrySet()) {
			data.add(new Parameter(entry.getKey(), entry.getValue()));
		}
		setItems(data);

	}
	
	public ObservableList<Parameter> getData() {
		return data;
	}

}
