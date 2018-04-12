package dkeep.logic;

import java.util.*;

public class Ogre extends Enemy{

	private Coords club;
	private char clubrep;
	
	/**
	 * Constructs the object
	 *
	 * @param      nrep     The new representation
	 * @param      coords   The coordinates
	 * @param      ccoords  The club coords
	 * @param      clubr    The club representation
	 */
	public Ogre(char nrep, Coords coords, Coords ccoords, char clubr) {
		super(nrep, coords);
		this.clubrep = clubr;
		this.club = ccoords;
		super.status = 0;
	}
	
	/**
	 * Randomizes the ogre's movement
	 *
	 * @return     A random 'WASD' char
	 */
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
	
	/**
	 * Validates if the ogre can attack a certain position
	 *
	 * @param      board  The board
	 * @param      nc     The new coordinates for the club
	 *
	 * @return     True if he can attack,false otherwise
	 */
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
	
	/**
	 * Moves the ogre if it isn't stunned
	 *
	 * @param      key    The key (diretion)
	 * @param      board  The board
	 */
	public void moveOgre(char key, char [][] board) {
		
		if(super.status.intValue() == 0) {
			super.moveEnemy(key, board);
		}
	}
	
	/**
	 * After 3 turn wakes a stunned ogre
	 */
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
	
	/**
	 * Stuns an ogre
	 */
	public void stunMe() {
		super.status = 3;
		this.setRep('8');
	}
	
	/**
	 * Ogre attacks
	 *
	 * @param      board  The board
	 */
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
	
	/*
	 * NOT TO BE IMPLEMENTED: WE CONSIDER THIS FUCNTIONALLITY UNNECESSARY, IF
	 * THE CLIENT SO CHOOSES CAN BE IMPLEMENTED AS THE GUARD SCAN IS YET IT IS
	 * CALLED FROM THE OGRE OBJECT (LIKE ATTACKOGRE OR MOVEOGRE)
	 *
	 * @param      board  The board
	 *
	 * @return     If it caught the hero True, False ow
	 */
	public boolean ogreScan(char [][] board) {
		int x0, x1, y0, y1;
		x0 = this.c.X() - 1;
		x1 = this.c.X() + 1;
		y0 = this.c.Y() - 1;
		y1 = this.c.Y() + 1;
		if(x0 > 0) {
			if ( board[ x0 ][ this.c.Y() ] == 'H' ||
					board[ x0 ][ this.c.Y() ] == 'K' ||
					board[ x0 ][ this.c.Y() ] == 'A') return true; 
		}
		
		if (x1 < board.length) {
			if ( board[ x1 ][ this.c.Y() ] == 'H' ||
					board[ x1 ][ this.c.Y() ] == 'K' ||
					board[ x1 ][ this.c.Y() ] == 'A') return true;
		}
		
		if (y0 > 0) {
			if(board[ this.c.X() ][ y0 ] == 'H' ||
					board[ this.c.X() ][ y0 ] == 'K' ||
					board[ this.c.X() ][ y0 ] == 'A') return true;
		}
		
		if (y1 < board[0].length) {
			if( board[ this.c.X() ][ y1 ] == 'H' ||
					board[ this.c.X() ][ y1 ] == 'K' ||
					board[ this.c.X() ][ y1 ] == 'A') return true;
		}
		
		return false;
	}
	
	/**
	 * Gets the woke.
	 *
	 * @return     The woke.
	 */
	public int getWoke() {
		return super.status.intValue();
	}
	
	/**
	 * Gets the club coordinates.
	 *
	 * @return     The club coordinates.
	 */
	public Coords getCCoords() {
		return this.club;
	}
	
	/**
	 * Gets the club rep.
	 *
	 * @return     The club rep.
	 */
	public char getCRep() {
		return this.clubrep;
	}
	
	/**
	 * Sets the club rep.
	 *
	 * @param      nr    new rep
	 */
	public void setCRep(char nr) {
		this.clubrep = nr;
	}
}
