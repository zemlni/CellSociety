package sugarscape;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import cellsociety_team18.Cell;
import cellsociety_team18.State;
import javafx.scene.paint.Color;

public class AgentState extends State {
	private int sugarMetabolism;
	private int sugar;
	private int maxSugar;
	private int interval;
	
	public AgentState(int sugarStart, int sugarMetabolism, int maxSugar, int interval){
		this.sugar = sugarStart;
		this.sugarMetabolism = sugarMetabolism;
		this.maxSugar = maxSugar;
		this.interval = interval;
		setColor(Color.BLUE);
	}
	@Override
	public void chooseState() {
		if (sugar <= 0) {
			getCell().setNextState(new EmptyState(0, maxSugar, interval));
			System.out.println("DEATh");
			return;
		}
		Cell temp = getCell();
		Cell move =  getPossibleMove();
		if (move != null) {
			eatSugar(move.getNextState());
			move.setNextState(this);
			temp.setNextState(new EmptyState(0, maxSugar, interval));
		}
		
		sugar -= sugarMetabolism;
	}

	private Cell getPossibleMove() {
		List<Cell> options = getCell().getNeighbors();
		Iterator<Cell> i = options.iterator();
		while (i.hasNext()) {
			Cell cell = i.next();
			if (!(cell.getNextState() instanceof EmptyState))
				i.remove();
		}
		Collections.sort(options, (a, b) -> ((EmptyState)a.getNextState()).getCurSugar() - ((EmptyState)b.getNextState()).getCurSugar());
		if (options.size() == 0)
			return null;
		return options.get(0);
	}

	private void eatSugar(State state) {
		this.sugar += ((EmptyState)state).getCurSugar();
		((EmptyState)state).resetCurSugar();
	}
}
