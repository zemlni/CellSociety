package games.sugarscape;

import java.util.ArrayList;
import java.util.Arrays;

import cellsociety_team18.Game;
import cellsociety_team18.State;

/**
 * @author nikita Class represents a sugarscape game and all setup methods.
 */
public class SugarScapeGame extends Game {

	/**
	 * get state according to preset probabilities.
	 * 
	 * @return state requested
	 */
	@Override
	public State getStateProbabilistically() {
		double rand = Math.random();
		return rand < getSettings().getDoubleParameter("percentAgent") ? new AgentState(this) : new SugarState(this);
	}

	/**
	 * setup all relevant fields
	 */
	@Override
	public void setup() {
		setParameters("percentAgent", "sugarStart", "sugarMetabolism", "maxSugar", "interval");

	}

	/**
	 * set the states that are possible in this game
	 */
	@Override
	public void setStates() {
		getStates().put("Agent", new AgentState(this));
		getStates().put("Sugar", new SugarState(this));

	}

	/**
	 * get state disregarding preset probabilities
	 * 
	 * @return state requested
	 */
	@Override
	public State getStateRandomly() {
		return getStateRandomly(new ArrayList<>(Arrays.asList(new SugarState(this), new AgentState(this))));
	}
}
