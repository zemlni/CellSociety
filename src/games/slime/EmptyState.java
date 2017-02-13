package games.slime;

import cellsociety_team18.State;
import javafx.scene.paint.Color;

public class EmptyState extends State {

	
	public EmptyState(){
		setColor(Color.BLACK);
	}
	@Override
	public void chooseState() {}

}
