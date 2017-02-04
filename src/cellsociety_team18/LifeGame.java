package cellsociety_team18;

import java.io.File;

public class LifeGame extends Game {

	@Override
	public void setup() {
		State[] states = { new LiveState(), new DeadState() };
		setStates(states);
		setName("life");
	}

	@Override
	public State getRandomState(Cell cell) {
		int rand = (int)Math.random()*2;
		return rand == 1 ? new LiveState(cell) : new DeadState(cell);
	}

}
