package games.slime;

import cellsociety_team18.State;

public abstract class SlimeGameState extends State {
	private int stateValue;
	@Override
	public abstract void chooseState();
	
	public int getStateValue() {
		return stateValue;
	}
	public void setStateValue(int stateValue){
		this.stateValue = stateValue;
	}

}
