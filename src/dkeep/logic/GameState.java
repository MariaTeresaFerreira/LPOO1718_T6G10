package dkeep.logic;

import java.util.*;

public class GameState {

	private char [][] board;
	private int level;
	Coords leverORkey;
	private Hero hero;
	private LinkedList<Guard> guards;
	private LinkedList<Ogre> ogres;
	private LinkedList<Coords> exits;
	
	
	public int getLvl() {
		return this.level;
	}
	
	public Coords findChar(char board[][], char c) {
		
		Coords b = new Coords(-1, -1);
		for(int i = 0; i < board.length; i++)
			for(int j = 0; j < board[i].length; j++)
				if (board[i][j] == c) {
					b.setCoords(i, j);
				}
		return b;
	}
	
	public void printArray(char[] anArray) {
		for (int i = 0; i < anArray.length; i++) {
			if (i > 0) System.out.print(" ");
			System.out.print(anArray[i]);
		}
	}

	public void printMatrix(char[][] aMatrix) {

		for(int i = 0; i < aMatrix.length; i++) {
			if(i > 0) System.out.print("\n");
			printArray(aMatrix[i]);
		}
	}
	
	public void printGameState() {
		System.out.print('\n');
		this.printMatrix(this.board);
	}
	
	public void lvl1() {
		char [][] b = {
				{'X','X','X','X','X','X','X','X','X','X'},
				{'X','H',' ',' ','I',' ','X',' ','G','X'},
				{'X','X','X',' ','X','X','X',' ',' ','X'},
				{'X',' ','I',' ','I',' ','X',' ',' ','X'},
				{'X','X','X',' ','X','X','X',' ',' ','X'},
				{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X','X','X',' ','X','X','X','X',' ','X'},
				{'X',' ','I',' ','I',' ','X','k',' ','X'},
				{'X','X','X','X','X','X','X','X','X','X'}
		};
		
		Coords ch = findChar(b, 'H');
		Coords cg = findChar(b, 'G');
		Coords ce1 = new Coords(5, 0);
		Coords ce2 = new Coords(6, 0);
		this.exits.add(ce1);
		this.exits.add(ce2);
		String patrolRoute = "assssaaaaaasdddddddwwwww";
		Guard guard = new Guard(patrolRoute, 'G', cg);
		
		this.leverORkey = findChar(b, 'k');
		this.board = b;
		this.level = 1;
		this.hero = new Hero('H', ch);
		this.guards.add(guard);
	}
	
	public void lvl2() {
		
		char [][] b = {
				{'X','X','X','X','X','X','X','X','X'},
				{'I',' ',' ',' ','O',' ',' ','k','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X','H',' ',' ',' ',' ',' ',' ','X'},
				{'X','X','X','X','X','X','X','X','X'}
			};
		
		Coords ch = findChar(b, 'H');
		Coords co = findChar(b, 'O');
		Coords cc = new Coords(co.X(), co.Y() - 1);
		Coords ce = new Coords(1, 0);
		this.exits.add(ce);
		Ogre ogr = new Ogre('O', co, cc,'*');
		this.board = b;
		this.leverORkey = findChar(b, 'k');
		this.level = 2;
		this.hero = new Hero('H', ch);
		this.ogres.add(ogr);
	}
	
	public GameState(int level) {
		this.guards = new LinkedList<Guard>();
		this.ogres = new LinkedList<Ogre>();
		this.exits = new LinkedList<Coords>();
		if (level == 1) {
			this.lvl1();
		}else if (level == 2) {
			this.lvl2();
		}
	}
	
	public void clearBoard() {
		
		char cell;
		//Cycle that clears previous images from the board
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[0].length; j++) {
				cell = this.board[i][j];
				if (cell != 'X' && cell != 'I' && cell != 'S' && cell != ' ' && cell != 'k') {
					this.board[i][j] = ' ';		
				}
			}
		}
		
	}
	
	public void updateBoard() {
		
		this.clearBoard();
		Coords ch = this.hero.getCoords();
		Coords cg;
		Coords cc;
		this.board[ch.X()][ch.Y()] = this.hero.getRep();
		int i = 0;
		for (i = 0; i < this.guards.size(); i++) {
			cg = this.guards.get(i).getCoords();
			this.board[cg.X()][cg.Y()] = this.guards.get(i).getRep();
		}

		for(i = 0; i < this.ogres.size(); i++) {
			cg = this.ogres.get(i).getCoords();
			cc = this.ogres.get(i).getCCoords();
			this.board[cg.X()][cg.Y()] = this.ogres.get(i).getRep();
			this.board[cc.X()][cc.Y()] = this.ogres.get(i).getCRep();
		}
		
		
	}
	
	public void unlockAll() {
		
		int xh, yh, xl, yl;
		xh = this.hero.getCoords().X();
		yh = this.hero.getCoords().Y();
		xl = this.leverORkey.X();
		yl = this.leverORkey.Y();
		if (xh == xl && yl == yh && this.level == 1) {
			for (int i = 0; i < this.board.length; i++) {
				for(int j = 0; j < this.board[0].length; j++) {
					if (this.board[i][j] == 'I') {
						this.board[i][j] = 'S';
					}
				}
			}
		}
	}
	
	public boolean exit() {
		
		int xh, yh;
		xh = this.hero.getCoords().X();
		yh = this.hero.getCoords().Y();
		for(int i = 0; i < this.exits.size(); i++) {
			if(xh == this.exits.get(i).X() && yh  == this.exits.get(i).Y()) {
				return true;
			}
		}
		
		return false;
	}
	
	public void moveGuards() {
		
		for(int i = 0; i < this.guards.size(); i++) {
			this.guards.get(i).moveGuard(this.board);
		}
	}
	
	public Hero getHero() {
		return this.hero;
	}
	
	public char [][] getBoard(){
		return this.board;
	}
}
