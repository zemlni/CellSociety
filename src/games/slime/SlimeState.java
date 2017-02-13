package games.slime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cellsociety_team18.Cell;
import cellsociety_team18.Game;
import cellsociety_team18.State;
import javafx.scene.paint.Color;

public class SlimeState extends State {
	private int sniffThreshold;
	private Game game;

	public SlimeState(Game game) {
		this.game = game;
		this.sniffThreshold = game.getIntParameter("sniffThreshold");
		setColor(Color.web(game.getParameter("slimeColor").toUpperCase()));
	}

	@Override
	public void chooseState() {

		Cell move = getPossibleMove();
		Cell temp = getCell();

		if (move != null && !(move.getNextState() instanceof SlimeState) && !move.equals(temp)) {
			move.setNextState(new SlimeState(game));
			if (temp.getNextState().hashCode() == this.hashCode()){
				temp.setNextState(new ChemicalState(game));
			}
			return;
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
		Set<Cell> optionsSet = addNeighborsRecursive(sniffThreshold, getCell().getNeighbors());
		optionsSet.remove(getCell());
		Iterator<Cell> i = optionsSet.iterator();
		while (i.hasNext()) {
			Cell cell = i.next();
			if (cell.getNextState() instanceof SlimeState) {
				i.remove();
			}
		}
		List<Cell> options = new ArrayList<Cell>(optionsSet);
		Collections.sort(options, (a, b) -> {
			State aState = ((Cell) a).getNextState();
			State bState = ((Cell) b).getNextState();
			if (!(aState instanceof ChemicalState) && !(bState instanceof ChemicalState))
				return 0;
			if (aState instanceof ChemicalState && !(bState instanceof ChemicalState))
				return 1;
			if (bState instanceof ChemicalState && !(aState instanceof ChemicalState))
				return -1;
			return ((ChemicalState) aState).getSlimeContent() - ((ChemicalState) bState).getSlimeContent();
		});
		if (options.size() == 0) {
			return null;
		}
		Cell highest = options.get(0);
		options = getCell().getNeighbors();
		Collections.shuffle(options);
		Cell answer = null;
		for (Cell cell : options) {
			Set<Cell> temp = addNeighborsRecursive(sniffThreshold - 1, cell.getNeighbors());
			if (temp.contains(highest)) {
				answer = cell;
				break;
			}
		}

		return answer;
	}

}
