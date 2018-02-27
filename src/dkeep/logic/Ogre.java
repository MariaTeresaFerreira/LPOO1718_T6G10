package dkeep.logic;

import java.util.*;

public class Ogre extends Enemy{

	private Coords club;
	private char clubrep;
	
	public Ogre(char nrep, Coords coords, Coords ccoords, char clubr) {
		super(nrep, coords);
		this.clubrep = clubr;
		this.club = ccoords;
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
		
		return newpos != 'X' && newpos != 'I' && newpos != 'O' && newpos != 'G'
				&& newpos != '$' && newpos != '*'; 
	}
	
	public void attackMove(char key, char [][] board) {
		super.moveEnemy(key, board);
		
		Coords nc = super.getCoords();
		
		do{
			key = randommov();
			if (key == 'w') {
				nc.setCoords(nc.X() - 1, nc.Y());
				
			}else if (key == 's') {
				nc.setCoords(nc.X() + 1, nc.Y());
				
			}else if (key == 'a') {
				nc.setCoords(nc.X(), nc.Y() - 1);
				
			}else if (key == 'd') {
				nc.setCoords(nc.X(), nc.Y() + 1);
			}
		}while(!this.validateCMov(board, nc));
		this.club.setCoords(nc);
		System.out.println(this.c.X());
		System.out.println(this.c.Y());
		System.out.println(this.club.X());
		System.out.println(this.club.Y());
	}
	
	public Coords getCCoords() {
		return this.club;
	}
	
	public char getCRep() {
		return this.clubrep;
	}
}
