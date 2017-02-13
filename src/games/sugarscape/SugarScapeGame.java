package games.sugarscape;

import java.util.ArrayList;
import java.util.Arrays;

import cellsociety_team18.Game;
import cellsociety_team18.State;

public class SugarScapeGame extends Game {

	@Override
	public State getStateProbabilistically() {
		double rand = Math.random();
		return rand < getSettings().getDoubleParameter("percentAgent")
				? new AgentState(this)
				: new SugarState(this);
	}

	@Override
	public void setup() {
		setParameters("percentAgent", "sugarStart", "sugarMetabolism", "maxSugar", "interval");

	}

	@Override
	public void setStates() {
		getStates().put("Agent", new AgentState(this));
		getStates().put("Sugar",
				new SugarState(this));

	}

	@Override
	public State getStateRandomly() {
		return getStateRandomly(new ArrayList<>(Arrays.asList(new SugarState(this), new AgentState(this))));
	}
}
