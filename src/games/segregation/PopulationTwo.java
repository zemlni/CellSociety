package games.segregation;

import cellsociety_team18.Game;

/**
 * @author elliott
 * This class represents a population in Segregation.
 */
public class PopulationTwo extends AgentState {

	/**
	 */
	public PopulationTwo(Game game) {
		super(game, game.getParameter("population2Color").toUpperCase());
	}

}
