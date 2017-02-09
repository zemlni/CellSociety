package cellsociety_team18;

import javafx.beans.property.SimpleStringProperty;

public class Parameter {

	private final SimpleStringProperty parameter;
	private final SimpleStringProperty value;

	public Parameter(String parameter, String value) {
		this.parameter = new SimpleStringProperty(parameter);
		this.value = new SimpleStringProperty(value);
	}

	public String getParameter() {
		return parameter.get();
	}

	public void setParameter(String parameter) {
		this.parameter.set(parameter);
	}

	public String getValue() {
		return value.get();
	}

	public void setValue(String value) {
		this.value.set(value);
	}

}
