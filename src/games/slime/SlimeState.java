package games.slime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cellsociety_team18.Cell;
import cellsociety_team18.State;
import javafx.scene.paint.Color;

public class SlimeState extends State {
	private int sniffThreshold;

	public SlimeState(int sniffThreshold) {
		this.sniffThreshold = sniffThreshold;
		setColor(Color.RED);
	}

	@Override
	public void chooseState() {

		Cell move = getPossibleMove();
		if (move != null) {
			move.setNextState(this);
			getCell().setNextState(new EmptyState());
		}
	}

	private Set<Cell> addNeighborsRecursive(int i, List<Cell> neighbors) {
		Set<Cell> answer = new HashSet<Cell>();
		if (i > 0) {
			for (Cell cell : neighbors) {
				answer.add(cell);
				answer.addAll(addNeighborsRecursive(i - 1, cell.getNeighbors()));
			}
		}
		return answer;
	}

	private Cell getPossibleMove() {
		Set<Cell> optionsSet = addNeighborsRecursive(sniffThreshold - 1, getCell().getNeighbors());
		optionsSet.remove(getCell());
		Iterator<Cell> i = optionsSet.iterator();
		while (i.hasNext()) {
			Cell cell = i.next();
			if (cell.getState() instanceof SlimeState)
				i.remove();
		}
		List<Cell> options = new ArrayList<Cell>(optionsSet);
		Collections.sort(options, (a, b) -> {
			State aState = ((Cell) a).getState();
			State bState = ((Cell) b).getState();
			if (!(aState instanceof ChemicalState))
				return -1;
			if (!(bState instanceof ChemicalState))
				return 1;
			return ((ChemicalState) aState).getSlimeContent() - ((ChemicalState) bState).getSlimeContent();
		});
		if (options.size() == 0)
			return null;
		return options.get(0);
	}

}
