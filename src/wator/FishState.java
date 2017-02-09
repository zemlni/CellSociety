package wator;

import javafx.scene.paint.Color;

/**
 * @author elliott
 * This class represents a Fish in Wator.
 */
public class FishState extends AgentState {
	
	private int reproductionTime;
	
	/**
	 * @param reproductionTime The number of iterations before the fish reproduces.
	 * Fish states are green.
	 */
	public FishState(int reproductionTime) {
		super(reproductionTime);
		this.reproductionTime = reproductionTime;
		setColor(Color.LAWNGREEN);
	}

	/**
	 * Move to a random Empty neighbor.
	 */
	@Override
	public void chooseState() {
		moveTo(new FishState(reproductionTime));
	}

}
