package cellsociety_team18;

public class RectGrid extends Grid{
	public void setupGrid(int n){
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				Point p = new Point(i, j);
				this.cells.put(p, new RectCell());
			}
		}
	}
}
