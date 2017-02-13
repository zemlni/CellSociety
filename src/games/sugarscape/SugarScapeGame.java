package games.sugarscape;

import cellsociety_team18.Game;
import cellsociety_team18.State;

public class SugarScapeGame extends Game {

	@Override
	public State getRandomState() {
		double rand = Math.random();
		return rand < getDoubleParameter("percentAgent")
				? new AgentState(getIntParameter("sugarStart"), getIntParameter("sugarMetabolism"),
						getIntParameter("maxSugar"), getIntParameter("interval"))
				: new EmptyState(getIntParameter("maxSugar"), getIntParameter("maxSugar"), getIntParameter("interval"));
	}

	@Override
	public void setup() {
		setParameters("percentAgent", "sugarStart", "sugarMetabolism", "maxSugar", "interval");

	}

	@Override
	public void setStates() {
		getStates().put("Agent", new AgentState(getIntParameter("sugarStart"), getIntParameter("sugarMetabolism"),
				getIntParameter("maxSugar"), getIntParameter("interval")));
		getStates().put("Empty",
				new EmptyState(getIntParameter("maxSugar"), getIntParameter("maxSugar"), getIntParameter("interval")));

	}
}
