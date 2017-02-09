package cellsociety_team18;

import java.util.Objects;

/**
 * @author Nikita Zemlevskiy This class represents a simple cartesian point.
 *         There are methods to access its coordinates and get distances between
 *         them.
 */
public class Point {
	private double x;
	private double y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Check if one point is equal to another object
	 * 
	 * @param other
	 *            object to which comparing
	 * @return if the object is a point and if its coordinates are equal to
	 *         those of this
	 * 
	 */
	@Override
	public boolean equals(Object other) {

		return (other instanceof Point && ((Point) other).getX() == x && ((Point) other).getY() == y);
	}

	/**
	 * Generate hashcode for this object.
	 * 
	 * @return hashcode for this point.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

}