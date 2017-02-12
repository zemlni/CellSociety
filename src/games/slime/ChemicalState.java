package games.slime;

import cellsociety_team18.State;
import javafx.scene.paint.Color;

public class ChemicalState extends State {
	private int time;
	public ChemicalState(int evaporationTime) {
		time = evaporationTime;
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
	}
	public int getSlimeContent(){
		return time;
	}
}
