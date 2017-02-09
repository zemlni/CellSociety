package segregation;

import javafx.scene.paint.Color;

/**
 * @author elliott
 * This class represents a Blue population in Segregation.
 */
public class BlueState extends AgentState {

	/**
	 * @param satisfactionThreshold The percentage of neighbors that must be the same as the Agent in question.
	 */
	public BlueState(double satisfactionThreshold) {
		super(satisfactionThreshold);
		setColor(Color.BLUE);
	}

}
