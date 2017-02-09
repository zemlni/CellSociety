package segregation;

import javafx.scene.paint.Color;

/**
 * @author elliott
 * This class represents a Red population in Segregation.
 */
public class RedState extends AgentState {

	/**
	 * @param satisfactionThreshold The percentage of neighbors that must be the same as the Agent in question.
	 */
	public RedState(double satisfactionThreshold) {
		super(satisfactionThreshold);
		setColor(Color.RED);
	}
	
}
