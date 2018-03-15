package dkeep.logic;

public class Coords {

	private int x;
	private int y;
	
	public Coords(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean equal(Coords nc) {
		return	this.x == nc.X() && this.y == nc.Y();
	}
	
	public boolean equals(Object t) {
		return t != null && t instanceof Coords && ((Coords) t).X() == this.x && 
				((Coords) t).Y() == this.y;
	}
	
	public int X() {
		return this.x;
	}
	
	public int Y() {
		return this.y;
	}
	
	public void setCoords(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setCoords(Coords nc) {
		this.x = nc.X();
		this.y = nc.Y();
	}
}
