package games.slime;

import java.util.ArrayList;
import java.util.Arrays;

import cellsociety_team18.Game;
import cellsociety_team18.State;
import games.EmptyState;

/**
 * @author nikita Class represents Slime game and contains all relevant setup
 *         and state generation methods.
 */
public class SlimeGame extends Game {

	/**
	 * get state according to preset probabilities
	 * 
	 * @return state requested
	 */
	@Override
	public State getStateProbabilistically() {
		double rand = Math.random();
		return rand < getSettings().getDoubleParameter("percentSlime") ? new SlimeState(this) : new EmptyState(this);
	}

	/**
	 * set up parameters needed.
	 */
	@Override
	public void setup() {
		setParameters("evaporationTime", "sniffThreshold", "percentSlime", "diffusionChance");
	}

	/**
	 * define states available in this simulation.
	 */
	@Override
	public void setStates() {
		getStates().put("Slime", new SlimeState(this));
		getStates().put("Chemical", new ChemicalState(this));
		getStates().put("Empty", new EmptyState(this));
	}

	/**
	 * get state disregarding preset probabilities
	 * 
	 * @return state requested
	 */
	@Override
	public State getStateRandomly() {
		return getStateRandomly(new ArrayList<>(Arrays.asList(new SlimeState(this), new EmptyState(this))));
	}
}
