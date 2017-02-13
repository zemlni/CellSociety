package games.slime;

import cellsociety_team18.Game;
import cellsociety_team18.State;

public class SlimeGame extends Game {

	@Override
	public State getRandomState() {
		double rand = Math.random();
		return rand < getDoubleParameter("percentSlime")
				? new SlimeState(getIntParameter("sniffThreshold"), getIntParameter("evaporationTime"), getDoubleParameter("diffusionChance"))
				: new EmptyState();
	}

	@Override
	public void setup() {
		setParameters("evaporationTime", "sniffThreshold", "percentSlime");
	}

	@Override
	public void setStates() {
		getStates().put("Slime", new SlimeState(getIntParameter("sniffThreshold"), getIntParameter("evaporationTime"), getDoubleParameter("diffusionChance")));
		getStates().put("Chemical", new ChemicalState(getIntParameter("evaporationTime"), getDoubleParameter("diffusionChance")));
		getStates().put("Empty", new EmptyState());
	}
}
