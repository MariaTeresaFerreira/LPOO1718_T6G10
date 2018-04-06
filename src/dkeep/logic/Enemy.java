package dkeep.logic;

/**
 * Class for enemy.
 */
public class Enemy {
	
	protected char rep;
	protected Coords c;
	protected Integer status;
	
	/**
	 * Constructs the object
	 *
	 * @param      nrep    The enemy's representation as a char
	 * @param      coords  The coordinates
	 */
	public Enemy(char nrep, Coords coords) {
		this.rep = nrep;
		this.c = coords;
		this.status = 0;
	}
	
	/**
	 * Validates Enemy movement
	 *
	 * @param      board  The game board
	 * @param      nc     The new Coords
	 *
	 * @return     if a new set of coords is valid 
	 */
	public boolean validateEMov(char [][] board, Coords nc) {
		
		int x = nc.X();
		int y = nc.Y();
		char newpos = board[x][y];
		
		return newpos != 'X' && newpos != 'I' && newpos != 'K' &&  newpos != 'H' 
				&& newpos != '$' && newpos != '*' && newpos != 'S'; 
	}

	/**
	 * Moves an Enemy on the map
	 *
	 * @param      key    W = UP, A = LEFT, S = DOWN, D = RIGHT
	 * @param      board  The game board
	 */
	public void moveEnemy(char key, char [][] board) {
		
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
		
		if (validateEMov(board, nc)) this.c.setCoords(nc);
	}
	

	/**
	 * Sets the new representation
	 *
	 * @param      nrep  The new representation
	 */
	public void setRep(char nrep) {
		this.rep = nrep;
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

}
