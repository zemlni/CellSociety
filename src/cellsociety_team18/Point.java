package cellsociety_team18;


public class Point {
	private int x;
	private int y;
	
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	/**
	 * Get distance between this point and another point.
	 * @param p2 other point
	 */
	public int distance(Point p2){
		return (int) Math.hypot(x - p2.getX(), y - p2.getY());
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	@Override
	public boolean equals(Object other){
		
		return (other instanceof Point && ((Point)other).getX() == x && ((Point)other).getY() == y);
	}
	
}