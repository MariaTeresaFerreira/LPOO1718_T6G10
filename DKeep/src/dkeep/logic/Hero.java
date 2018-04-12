package src.dkeep.logic;

public class Hero {
	
	private char rep;
	private boolean armed;
	private boolean alive;
	private Coords c;
	
	/**
	 * Constructs the object
	 *
	 * @param      rep    The representation
	 * @param      coord  The coordinate (starting)
	 */
	public Hero(char rep, Coords coord){
		this.rep = rep;
		this.armed = false;
		this.c = coord;
		this.alive = true;
	}
	
	/**
	 * Validates the hero's movement
	 *
	 * @param      board  The board
	 * @param      nc     The new coordinates
	 *
	 * @return     true or false depending if the move is valid
	 */
	public boolean validateHMov(char [][] board, Coords nc) {
		
		int x = nc.X();
		int y = nc.Y();
		char newpos = board[x][y];
		return newpos != 'X' && newpos != 'I' && newpos != 'O' && newpos != 'G' && newpos != '*';
	}
	
	/**
	 * Moves the hero
	 *
	 * @param      key    The key (WASD)
	 * @param      board  The board
	 */
	public void moveHero(char key, char [][] board) {
		
		int x = this.c.X();
		int y = this.c.Y();
		Coords nc = this.c;
		
		if (key == 'w' || key == 'W') {
			nc = new Coords(x - 1, y);
		}else if(key == 'a' || key == 'A') {
			nc = new Coords(x, y - 1);
		}else if(key == 's' || key == 'S') {
			nc = new Coords(x + 1, y);
		}else if(key == 'd' || key == 'D') {
			nc = new Coords(x, y + 1);
		}
		
		if (validateHMov(board, nc)) this.c.setCoords(nc);
	}
	

	/**
	 * Sets the hero's representation
	 *
	 * @param      nr    new representation
	 */
	public void setRep(char nr) {
		this.rep = nr;
	}
	
	/**
	 * Gives the hero a club so he can stun ogres
	 */
	public void armHero() {
		this.armed = true;
		this.setRep('A');
	}
	
	/**
	 * Kills the hero
	 */
	public void killHero() {
		this.alive = false;
	}
	
	/**
	 * Gets the coordinates
	 *
	 * @return     The coordinates
	 */
	public Coords getCoords() {
		return this.c;
	}
	
	/**
	 * Gets the representation
	 *
	 * @return     The representation
	 */
	public char getRep() {
		return this.rep;
	}
	
	/**
	 * Determines if the hero is alive
	 *
	 * @return     True if alive, False otherwise.
	 */
	public boolean isAlive() {
		return this.alive;
	}
	
	/**
	 * Determines if the hero is armed
	 *
	 * @return     True if armed, False otherwise.
	 */
	public boolean isArmed() {
		return this.armed;
	}
	
	/**
	 * Checks for guards in the hero's adjacent posintion's.
	 *
	 * @param      board  The board
	 *
	 * @return     True if there's a guard in the positions adjacent to the hero.
	 */
	public boolean guardScan(char [][] board) {
		int x0, x1, y0, y1;
		x0 = this.c.X() - 1;
		x1 = this.c.X() + 1;
		y0 = this.c.Y() - 1;
		y1 = this.c.Y() + 1;
		if(x0 > 0) {
			if ( board[ x0 ][ this.c.Y() ] == 'G') return true; 
		}
		
		if (x1 < board.length) {
			if ( board[ x1 ][ this.c.Y() ] == 'G') return true;
		}
		
		if (y0 > 0) {
			if(board[ this.c.X() ][ y0 ] == 'G') return true;
		}
		
		if (y1 < board[0].length) {
			if( board[ this.c.X() ][ y1 ] == 'G') return true;
		}
		
		return false;
	}
}
