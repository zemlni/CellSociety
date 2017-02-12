package games.sugarscape;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import cellsociety_team18.Cell;
import cellsociety_team18.State;
import javafx.scene.paint.Color;

public class AgentState extends State {
	private int sugarMetabolism;
	private int sugar;
	
	public AgentState(int sugarStart, int sugarMetabolism){
		this.sugar = sugarStart;
		this.sugarMetabolism = sugarMetabolism;
		setColor(Color.BLUE);
	}
	@Override
	public void chooseState() {
		if (sugar <= 0) {
			getCell().setNextState(new EmptyState());
			return;
		}

		SugarScapeCell move = (SugarScapeCell) getPossibleMove();
		if (move != null) {
			move.setNextState(this);
			getCell().setNextState(new EmptyState());
		}
		eatSugar(move);
		sugar -= sugarMetabolism;
	}

	private Cell getPossibleMove() {
		List<Cell> options = getCell().getNeighbors();
		Iterator<Cell> i = options.iterator();
		while (i.hasNext()) {
			Cell cell = i.next();
			if (!(cell.getState() instanceof EmptyState))
				i.remove();
		}
		Collections.sort(options, (a, b) -> ((SugarScapeCell) a).getCurSugar() - ((SugarScapeCell) b).getCurSugar());
		if (options.size() == 0)
			return null;
		return options.get(0);
	}

	private void eatSugar(SugarScapeCell cell) {
		this.sugar += cell.getCurSugar();
		cell.resetCurSugar();
	}
}
