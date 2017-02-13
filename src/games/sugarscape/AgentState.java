package games.sugarscape;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import cellsociety_team18.Cell;
import cellsociety_team18.Game;
import cellsociety_team18.State;
import javafx.scene.paint.Color;

public class AgentState extends State {
	private int sugarMetabolism;
	private int sugar;
	private Game game;

	public AgentState(Game game) {
		this.game = game;
		this.sugar = game.getIntParameter("sugarStart");
		this.sugarMetabolism = game.getIntParameter("sugarMetabolism");
		setColor(Color.BLUE);
	}

	@Override
	public void chooseState() {
		if (sugar <= 0) {
			getCell().setNextState(new SugarState(0, game));
			return;
		}
		Cell temp = getCell();
		Cell move = getPossibleMove();
		if (move != null) {
			eatSugar(move.getNextState());
			move.setNextState(this);
			if (temp.getNextState().equals(this))
				temp.setNextState(new SugarState(0, game));
		}
		sugar -= sugarMetabolism;
	}

	private Cell getPossibleMove() {
		List<Cell> options = getCell().getNeighbors();
		Iterator<Cell> i = options.iterator();
		while (i.hasNext()) {
			Cell cell = i.next();
			if (!(cell.getNextState() instanceof SugarState))
				i.remove();
		}
		Collections.sort(options, (a, b) -> {
			if (((SugarState) a.getNextState()).getCurSugar() != ((SugarState) b.getNextState()).getCurSugar())
				return ((SugarState) b.getNextState()).getCurSugar() - ((SugarState) a.getNextState()).getCurSugar();
			else
				return ((int) (Math.random() * 2)) - 1;
		});
		if (options.size() == 0)
			return null;
		System.out.println(((SugarState)options.get(0).getNextState()).getCurSugar());
		return options.get(0);
	}

	private void eatSugar(State state) {
		this.sugar += ((SugarState) state).getCurSugar();
		((SugarState) state).resetCurSugar();
	}
}
