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
	}
	
	public void killHero() {
		this.alive = false;
	}
	
}
