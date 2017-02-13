package cellsociety_team18;

import java.util.Map;

/**
 * @author elliott This class represents Settings – either game or configuration
 *         settings.
 */
public class Settings {

	Map<String, String> data;

	public Settings(Map<String, String> data) {
		this.data = data;
	}

	public Map<String, String> getMap() {
		return data;
	}

	public String getParameter(String parameter) {
		return data.get(parameter);
	}

	public double getDoubleParameter(String parameter) {
		return Double.parseDouble(data.get(parameter));
	}

	public int getIntParameter(String parameter) {
		return Integer.parseInt(data.get(parameter));
	}

	/**
	 * set a configuration to a certain value
	 * 
	 * @param key
	 *            config to set
	 * @param value
	 *            value of config to be set
	 */
	public void put(String key, String value) {
		data.put(key, value);
	}

}
