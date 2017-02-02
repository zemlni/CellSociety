package cellsociety_team18;

public abstract class GameOfLifeState extends State {

	public GameOfLifeState(Cell cell) {
		super(cell);
	}
	
	public int getLiveNeighbors() {
		int total = 0;
		for (Cell cell: getCell().getNeighbors()) {
			if (cell.getState() instanceof LiveState) {
				total++;
			}
		}
		return total;
	}

	@Override
	public abstract void applyRules();

}
