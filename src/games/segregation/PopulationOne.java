package games.segregation;

import cellsociety_team18.Game;

/**
 * @author elliott
 * This class represents a population in Segregation.
 */
public class PopulationOne extends AgentState {

	/**
	 * @param game The state's game.
	 */
	public PopulationOne(Game game) {
		super(game, game.getParameter("population1Color").toUpperCase());
	}

}
