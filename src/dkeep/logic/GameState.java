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
		this.exits.clear();
		this.exits.add(ce);
		Ogre ogr = new Ogre('O', co, cc,'*');
		this.board = b;
		this.leverORkey = findChar(b, 'k');
		this.level = 2;
		this.hero = new Hero('H', ch);
		this.ogres.add(ogr);
		this.guards.clear();
	}
	
	public void lvl3() {
		this.level = 3;
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
		boolean ogreOnKey = false;
		boolean clubOnKey = false;
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
			}else {
				this.ogres.get(i).setRep('O');
			}
			cc = this.ogres.get(i).getCCoords();
			clubOnKey = clubOnKey || (this.ogres.get(i).getCCoords().equal(this.leverORkey) &&
					this.hero.getRep() != 'K');
			if(clubOnKey) {
				this.ogres.get(i).setCRep('$');
			}else {
				this.ogres.get(i).setCRep('*');
			}
			
			this.board[cg.X()][cg.Y()] = this.ogres.get(i).getRep();
			this.board[cc.X()][cc.Y()] = this.ogres.get(i).getCRep();
			//Fazer uma seq, de || com o ogreOnKey verificando se a entidade esta em cima da chave
		}
		
		if (this.hero.getRep() != 'K' && !ogreOnKey && !clubOnKey) {
			this.board[this.leverORkey.X()][this.leverORkey.Y()] = 'k';
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
	
	public void playLvl1() {
		
		Scanner reader = new Scanner(System.in);
		char key;
		while(this.getHero().isAlive() && this.getLvl() == 1) {
			this.printGameState();
			key = reader.next().charAt(0);
			this.getHero().moveHero(key, this.getBoard());
			this.updateBoard();
			this.moveGuards();
			this.updateBoard();
			if(this.getHero().guardScan(this.getBoard())) {
				this.getHero().killHero();
			}
			this.unlockAll();
			if(this.exit()) this.lvl2();
			this.updateBoard();
						
		}
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
		/*if(this.getHero().getCoords().)*/
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
	
	public void playLvl2() {
		Scanner reader = new Scanner(System.in);
		char key;
		int unlocked = 0;
		while (this.getHero().isAlive() && this.getLvl() == 2) {
			if (this.hero.getRep() == 'K') {
				if(this.checkANUnlock(unlocked)) unlocked++;
			}
			this.printGameState();
			key = reader.next().charAt(0);
			this.getHero().moveHero(key, this.getBoard());
			this.updateBoard();
			this.catchKey();
			this.updateBoard();
			this.moveOgres();
			this.updateBoard();
			this.attackOgres();
			this.updateBoard();
			if(this.exit()) this.lvl3();
			if (this.isPlayerHit()) {
				this.hero.killHero();
				this.updateBoard();
				this.printGameState();
			}
		}
	}
}
