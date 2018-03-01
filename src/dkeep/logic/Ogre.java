package dkeep.logic;

import java.util.*;

public class Ogre extends Enemy{

	private Coords club;
	private char clubrep;
	private char woke;
	
	public Ogre(char nrep, Coords coords, Coords ccoords, char clubr) {
		super(nrep, coords);
		this.clubrep = clubr;
		this.club = ccoords;
		this.woke = 'Y';
	}
	
	public char randommov() {
		Random randomno = new Random();
		int n = randomno.nextInt(4);
		if(n == 0) {
			return 'w';
		}else if(n == 1) {
			return 'a';
		}else if(n == 2) {
			return 's';
		}else return 'd';
	}
	
	public boolean validateCMov(char [][] board, Coords nc) {
		
		int x = nc.X();
		int y = nc.Y();
		char newpos = board[x][y];
		
		if(x != 0 && y != 0 && newpos != 'X' && newpos != 'I' &&
				newpos != 'G' && newpos != '$') {
			return true; 
		}
		return false;
	}
	
	public void moveOgre(char key, char [][] board) {
		
		if(this.woke == 'Y') {
			super.moveEnemy(key, board);
		}
	}
	
	public void wakeOgre() {
		
		if(this.woke == '2') {
			this.woke = '1';
		}else if (this.woke == '1') {
			this.woke = '0';
		}else if (this.woke == '0') {
			this.woke = 'Y';
			this.setRep('O');
		}
	}
	
	public void stunMe() {
		this.woke = '2';
		this.setRep('8');
	}
	
	public void attackOgre(char [][] board) {
		
		if (this.woke == 'Y') {
			Coords c = super.getCoords();
			Coords nc = new Coords(0, 0);
			char key2; 
			while(!this.validateCMov(board, nc)){
				key2 = randommov();
				if (key2 == 'w') {
					nc.setCoords(c.X() - 1, c.Y());
						
				}else if (key2 == 's') {
					nc.setCoords(c.X() + 1, c.Y());
						
				}else if (key2 == 'a') {
					nc.setCoords(c.X(), c.Y() - 1);
						
				}else if (key2 == 'd') {
					nc.setCoords(c.X(), c.Y() + 1);
				}
			}
			this.club.setCoords(nc);
		}
	}
	
	public char getWoke() {
		return this.woke;
	}
	
	public Coords getCCoords() {
		return this.club;
	}
	
	public char getCRep() {
		return this.clubrep;
	}
	
	public void setCRep(char nr) {
		this.clubrep = nr;
	}
}
