package games.slime;

import java.util.ArrayList;
import java.util.Arrays;

import cellsociety_team18.Game;
import cellsociety_team18.State;
import games.EmptyState;

public class SlimeGame extends Game {

	@Override
	public State getStateProbabilistically() {
		double rand = Math.random();
		return rand < getDoubleParameter("percentSlime") ? new SlimeState(this) : new EmptyState(this);
	}

	@Override
	public void setup() {
		setParameters("evaporationTime", "sniffThreshold", "percentSlime", "diffusionChance");
	}

	@Override
	public void setStates() {
		getStates().put("Slime", new SlimeState(this));
		getStates().put("Chemical",
				new ChemicalState(this));
		getStates().put("Empty", new EmptyState(this));
	}

	@Override
	public State getStateRandomly() {
		return getStateRandomly(new ArrayList<>(Arrays.asList(new SlimeState(this), new EmptyState(this))));
	}
}
