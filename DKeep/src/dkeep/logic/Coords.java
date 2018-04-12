package src.dkeep.logic;

/**
 * Class for coordinates.
 */
public class Coords {

	private int x;
	private int y;
	
	/*
	 * Constructs the object
	 *
	 * @param      x     X coordinate
	 * @param      y     Y coordinate
	 */
	public Coords(int x, int y) {
		this.x = x;
		this.y = y;
	}
		
	/**
	 * Equals
	 *
	 * @param      t     Object to be compared to the calling coordinate
	 *
	 * @return     true if Object t is a Coordinate and has the same values as the calling obj
	 */
	public boolean equals(Object t) {
		return t != null && t instanceof Coords && ((Coords) t).X() == this.x && 
				((Coords) t).Y() == this.y;
	}
	
	/**
	 * Get X
	 *
	 * @return     x
	 */
	public int X() {
		return this.x;
	}

	/**
	 * Get Y
	 *
	 * @return     y
	 */
	public int Y() {
		return this.y;
	}
	
	/**
	 * Sets the coordinates.
	 *
	 * @param      x     new x
	 * @param      y     new y
	 */
	public void setCoords(int x, int y) {
		this.x = x;
		this.y = y;
	}
	

	/**
	 * Copy Constructor
	 *
	 * @param      nc    Coords to be copied
	 */
	public void setCoords(Coords nc) {
		this.x = nc.X();
		this.y = nc.Y();
	}
}
