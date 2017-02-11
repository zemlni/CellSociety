package slime;

import cellsociety_team18.Game;
import cellsociety_team18.State;

public class SlimeGame extends Game {

	@Override
	public State getRandomState() {
		double rand = Math.random();
		return rand < getDoubleParameter("percentSlime") ? new SlimeState(getIntParameter("sniffThreshold")) : new EmptyState();
	}

	@Override
	public void setup() {
		setParameters("evaporationTime", "sniffThreshold", "percentSlime");

	}

	@Override
	public void setStates() {
		getStates().put("Slime", new SlimeState(getIntParameter("sniffThreshold")));
		getStates().put("Chemical", new ChemicalState(getIntParameter("evaporationTime")));
		getStates().put("Empty", new EmptyState());


	}

}
