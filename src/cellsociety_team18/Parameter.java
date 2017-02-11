package cellsociety_team18;

import javafx.beans.property.SimpleStringProperty;

/**
 * @author Elliott Bolzan
 * A class representing a parameter for a simulation.
 */
public class Parameter {

	private final SimpleStringProperty parameter;
	private final SimpleStringProperty value;

	/**
	 * @param parameter The name of the parameter.
	 * @param value The value of the parameter.
	 * @return The parameter.
	 */
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
