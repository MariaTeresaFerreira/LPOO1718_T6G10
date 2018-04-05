package dkeep.logic;

import java.util.*;

//import sun.nio.cs.ext.TIS_620;

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
		this.exits.clear();
		this.exits.add(ce);
		Ogre ogr = new Ogre('O', co, cc,'*');
		this.ogres.add(ogr);
		Random ogreN = new Random();
		int n = ogreN.nextInt(3);
		int rx, ry;
		int cy;
		while(n > 0) {
			rx = ogreN.nextInt(6) + 1;
			ry = ogreN.nextInt(7) + 1;
			co = new Coords(rx, ry);
			if (co.Y() == this.board[0].length - 2) {
				cy = co.Y() - 1;
			}else if (co.Y() == 1) {
				cy = co.Y() + 1;
			}else {
				cy = co.Y();
			}
			cc = new Coords(co.X() + 1, cy);
			ogr = new Ogre('O', co, cc,'*');
			this.ogres.add(ogr);
			n--;
		}
		this.board = b;
		this.leverORkey = findChar(b, 'k');
		this.level = 2;
		this.hero = new Hero('H', ch);
		this.guards.clear();
		this.hero.armHero();
	}
	
	public void stunOgres() {
		Coords c1, c2, c3, c4;
		if(this.hero.isArmed()) {
			for (int i = 0; i < this.ogres.size(); i++) {
				c1 = new Coords(this.ogres.get(i).getCoords().X() + 1
						, this.ogres.get(i).getCoords().Y());
				c2 = new Coords(this.ogres.get(i).getCoords().X() - 1, 
						this.ogres.get(i).getCoords().Y());
				c3 = new Coords(this.ogres.get(i).getCoords().X(),
						this.ogres.get(i).getCoords().Y() + 1);
				c4 = new Coords(this.ogres.get(i).getCoords().X(), 
						this.ogres.get(i).getCoords().Y() + 1);
				if(this.hero.getCoords().equal(c1) || this.hero.getCoords().equal(c2) ||
						this.hero.getCoords().equal(c3) || this.hero.getCoords().equal(c4)) {
					this.ogres.get(i).stunMe();
				}
			}
		}
	}
	
	public void wakeOgres() {
		for(int i = 0; i < this.ogres.size(); i++) {
			this.ogres.get(i).wakeOgre();
		}
	}
	
	public void gg() {
		this.level = 0;
		System.out.println("GG WP");
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
	
	public GameState(char [][] board, LinkedList<Coords> exits,LinkedList<Ogre> ogres, LinkedList<Guard> guards) {
		this.board = board;
		this.guards = new LinkedList<Guard>();
		this.guards = guards;
		this.ogres = new LinkedList<Ogre>();
		this.ogres = ogres;
		this.exits = new LinkedList<Coords>();
		this.exits = exits;
		this.leverORkey = findChar(board, 'k');
		Coords ch = findChar(board, 'H');
		this.hero = new Hero('H', ch);
		if (ch.X() == -1) {
			ch = findChar(board, 'A');
			this.hero = new Hero('A', ch);
			this.hero.armHero();
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
		boolean ogreOnKey = false;
		boolean clubOnKey = false;
		boolean someoneOnKey = false;
		this.board[ch.X()][ch.Y()] = this.hero.getRep();
		int i = 0;
		for (i = 0; i < this.guards.size(); i++) {
			cg = this.guards.get(i).getCoords();
			this.board[cg.X()][cg.Y()] = this.guards.get(i).getRep();
		}

		for(i = 0; i < this.ogres.size(); i++) {
			cg = this.ogres.get(i).getCoords();
			ogreOnKey = ogreOnKey || (this.ogres.get(i).getCoords().equal(this.leverORkey) &&
					this.hero.getRep() != 'K');
			if (ogreOnKey) {
				this.ogres.get(i).setRep('$');
				someoneOnKey = true;
			}else {
				if(this.ogres.get(i).getWoke() == 0) {
					this.ogres.get(i).setRep('O');
				}else {
					this.ogres.get(i).setRep('8');
				}
			}
			cc = this.ogres.get(i).getCCoords();
			clubOnKey = clubOnKey || (this.ogres.get(i).getCCoords().equal(this.leverORkey) &&
					this.hero.getRep() != 'K');
			if(clubOnKey) {
				this.ogres.get(i).setCRep('$');
				someoneOnKey = true;
			}else {
				this.ogres.get(i).setCRep('*');
			}
			this.board[cg.X()][cg.Y()] = this.ogres.get(i).getRep();
			this.board[cc.X()][cc.Y()] = this.ogres.get(i).getCRep();
			//Fazer uma seq, de || com o ogreOnKey verificando se a entidade esta em cima da chave
			ogreOnKey = false;
			clubOnKey = false;
		}
		
		if (this.hero.getRep() != 'K' && !someoneOnKey) {
			this.board[this.leverORkey.X()][this.leverORkey.Y()] = 'k';
		} 
		
		
	}
	
	public void unlockAll() {
		
		int xh, yh, xl, yl;
		xh = this.hero.getCoords().X();
		yh = this.hero.getCoords().Y();
		xl = this.leverORkey.X();
		yl = this.leverORkey.Y();
		if (xh == xl && yl == yh) {
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
	
	public void moveOgres() {
		
		for(int i = 0; i < this.ogres.size(); i++) {
			this.ogres.get(i).moveOgre(this.ogres.get(i).randommov(), this.board);
		}
	}
	
	public void attackOgres() {
		for (int i = 0; i < this.ogres.size(); i++) {
			this.ogres.get(i).attackOgre(this.board);
		}
	}
	
	public Hero getHero() {
		return this.hero;
	}
	
	public char [][] getBoard(){
		return this.board;
	}
	
	public Coords getLeverORkey() {
		return this.leverORkey;
	}
	
	public LinkedList<Coords> getExits(){
		return this.exits;
	}
	
	public LinkedList<Ogre> getOgres(){
		return this.ogres;
	}
	
	public LinkedList<Guard> getGuards(){
		return this.guards;
	}
		
	public boolean isPlayerHit() {
		int xh = this.hero.getCoords().X();
		int yh = this.hero.getCoords().Y();
		int xc, yc;
		for (int i = 0; i < this.ogres.size(); i++) {
			xc = this.ogres.get(i).getCCoords().X();
			yc = this.ogres.get(i).getCCoords().Y();
			if(xc == xh && yh == yc) return true;
		}
		return false;
	}
	
	public boolean checkANUnlock(int x) {
		int xh, yh, xe, ye;
		xh = this.hero.getCoords().X();
		yh = this.hero.getCoords().Y();
		for(int i = 0; i < this.exits.size(); i++) {
			xe = this.exits.get(i).X();
			ye = this.exits.get(i).Y();
			if (xe == xh - 1 && ye == yh) {
				if(x == 1) {
					this.board[xe][ye] = 'S';
				}
				return true;
			}else if (xe == xh + 1 && ye == yh) {
				if(x == 1) {
					this.board[xe][ye] = 'S';
				}
				return true;
			}else if (xe == xh && ye == yh - 1) {
				if(x == 1) {
					this.board[xe][ye] = 'S';
				}
				return true;
			}else if ( xe == xh && ye == yh + 1) {
				if(x == 1) {
					this.board[xe][ye] = 'S';
				}
				return true;
			}
		}
		
		return false;
	}
	
	public void catchKey() {
		int xh, yh, xl, yl;
		xh = this.hero.getCoords().X();
		yh = this.hero.getCoords().Y();
		xl = this.leverORkey.X();
		yl = this.leverORkey.Y();
		if (xh == xl && yh == yl) {
			this.hero.setRep('K');
		}
	}
	
	
}
