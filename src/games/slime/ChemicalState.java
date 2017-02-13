package games.slime;

import java.util.List;

import cellsociety_team18.Cell;
import cellsociety_team18.Game;
import cellsociety_team18.State;
import javafx.scene.paint.Color;
import games.EmptyState;

public class ChemicalState extends State {
	private int time;
	private double diffusionChance;
	private Game game;
	public ChemicalState(Game game) {
		this.game = game;
		time = game.getIntParameter("evaporationTime");
		this.diffusionChance = game.getDoubleParameter("diffusionChance");
		setColor(Color.web(game.getParameter("chemicalColor").toUpperCase()));
	}
	
	public ChemicalState(int time, Game game){
		this(game);
		this.time = time;
	}

	@Override
	public void chooseState() {
		if (time < 0){
			if (!(getCell().getNextState() instanceof SlimeState)){
				getCell().setNextState(new EmptyState(game));
			}
			return;
		}
		setColor(getColor().darker());
		time--;
		List<Cell> neighbors = getCell().getNeighbors();
		for (Cell neighbor: neighbors){
			double rand = Math.random();
			if (rand < diffusionChance && !(neighbor.getNextState() instanceof SlimeState) && !(neighbor.getState() instanceof SlimeState)){
				neighbor.setNextState(new ChemicalState(time - 1, game));
			}
		}
	}
	public int getSlimeContent(){
		return time;
	}
}
