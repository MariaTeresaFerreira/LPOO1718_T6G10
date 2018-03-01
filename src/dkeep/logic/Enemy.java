package dkeep.logic;

public class Enemy {
	
	protected char rep;
	protected Coords c;
	int status;
	
	public Enemy(char nrep, Coords coords) {
		this.rep = nrep;
		this.c = coords;
		this.status = 0;
	}
	
	public boolean validateEMov(char [][] board, Coords nc) {
		
		int x = nc.X();
		int y = nc.Y();
		char newpos = board[x][y];
		
		return newpos != 'X' && newpos != 'I' && newpos != 'K' &&  newpos != 'H' 
				&& newpos != '$' && newpos != '*'; 
	}

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
	
	public void setRep(char nrep) {
		this.rep = nrep;
	}
	
	public Coords getCoords() {
		return this.c;
	}
	
	public char getRep() {
		return this.rep;
	}

}
