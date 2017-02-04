package cellsociety_team18;

import game_of_life.*;

public class GameOfLifeGame extends Game {

	@Override
	public void setup() {
		setName("life");
	}

	@Override
	public State getRandomState(Cell cell) {
		int rand = (int)Math.random()*2;
		return rand == 1 ? new LiveState(cell) : new DeadState(cell);
	}

}
