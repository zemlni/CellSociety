package games.slime;

import java.util.List;

import cellsociety_team18.Cell;
import cellsociety_team18.State;
import javafx.scene.paint.Color;

public class ChemicalState extends State {
	private int time;
	private double diffusionChance;
	public ChemicalState(int evaporationTime, double diffusionChance) {
		time = evaporationTime;
		this.diffusionChance = diffusionChance;
		setColor(Color.GREEN);
	}

	@Override
	public void chooseState() {
		if (time < 0){
			getCell().setNextState(new EmptyState());
			return;
		}
		setColor(getColor().darker());
		time--;
		List<Cell> neighbors = getCell().getNeighbors();
		/*for (Cell neighbor: neighbors){
			double rand = Math.random();
			if (rand < diffusionChance && !(neighbor.getNextState() instanceof SlimeState) && !(neighbor.getState() instanceof SlimeState)){
				neighbor.setNextState(new ChemicalState(time - 1, diffusionChance));
			}
		}*/
	}
	public int getSlimeContent(){
		return time;
	}
}
