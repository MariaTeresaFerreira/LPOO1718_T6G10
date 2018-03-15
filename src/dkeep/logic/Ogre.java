package dkeep.logic;

import java.util.*;

public class Ogre extends Enemy{

	private Coords club;
	private char clubrep;
	
	public Ogre(char nrep, Coords coords, Coords ccoords, char clubr) {
		super(nrep, coords);
		this.clubrep = clubr;
		this.club = ccoords;
		super.status = 0;
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
		
		if(super.status.intValue() == 0) {
			super.moveEnemy(key, board);
		}
	}
	
	public void wakeOgre() {
		
		if(super.status.intValue() == 3) {
			super.status = 2;
		}else if (super.status.intValue() == 2) {
			super.status = 1;
		}else if (super.status.intValue() == 1) {
			super.status = 0;
			this.setRep('O');
		}
	}
	
	public void stunMe() {
		super.status = 3;
		this.setRep('8');
	}
	
	public void attackOgre(char [][] board) {
		
		if (super.status.intValue() == 0) {
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
	
	public int getWoke() {
		return super.status.intValue();
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
