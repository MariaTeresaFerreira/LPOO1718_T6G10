package dkeep.logic;

public class Hero {
	
	private char rep;
	private boolean armed;
	private boolean alive;
	private Coords c;
	
	public Hero(char rep, Coords coord){
		this.rep = rep;
		this.armed = false;
		this.c = coord;
		this.alive = true;
	}
	
	public boolean validateHMov(char [][] board, Coords nc) {
		
		int x = nc.X();
		int y = nc.Y();
		char newpos = board[x][y];
		return newpos != 'X' && newpos != 'I' && newpos != 'O' && newpos != 'G' && newpos != '*';
	}
	
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
	
	public void setRep(char nr) {
		this.rep = nr;
	}
	
	public void armHero() {
		this.armed = true;
		this.setRep('A');
	}
	
	public void killHero() {
		this.alive = false;
	}
	
	public Coords getCoords() {
		return this.c;
	}
	
	public char getRep() {
		return this.rep;
	}
	
	public boolean isAlive() {
		return this.alive;
	}
	
	public boolean isArmed() {
		return this.armed;
	}
	
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
