package games.segregation;

import cellsociety_team18.Game;

/**
 * @author elliott
 * This class represents a population in Segregation.
 */
public class PopulationTwo extends AgentState {

	/**
	 * @param game The state's game.
	 */
	public PopulationTwo(Game game) {
		super(game, game.getSettings().getParameter("population2Color").toUpperCase());
	}

}
