package src.dkeep.logic;

import java.util.*;
import javax.swing.JTextArea;

//import sun.nio.cs.ext.TIS_620;

public class GameState {

	private char [][] board;
	private int level;
	Coords leverORkey;
	private Hero hero;
	private LinkedList<Guard> guards;
	private LinkedList<Ogre> ogres;
	private LinkedList<Coords> exits;


	/**
	 * Gets the level.
	 *
	 * @return     The level.
	 */
	public int getLvl() {
		return this.level;
	}

	/*
	 * Given a char and the board returns the char's coordinates
	 *
	 *	@param  board	board
	 *	@param  c		char to be found
	 *	@return the coordinates of the given char
	 */
	public Coords findChar(char board[][], char c) {

		Coords b = new Coords(-1, -1);
		for(int i = 0; i < board.length; i++)
			for(int j = 0; j < board[i].length; j++)
				if (board[i][j] == c) {
					b.setCoords(i, j);
				}
		return b;
	}

	/**
	 * prints an Array
	 *
	 * @param      anArray  An array
	 */
	public void printArray(char[] anArray) {
		for (int i = 0; i < anArray.length; i++) {
			if (i > 0) System.out.print(" ");
			System.out.print(anArray[i]);
		}
	}

	/**
	 * prints a Matrix
	 *
	 * @param      aMatrix  A matrix
	 */
	public void printMatrix(char[][] aMatrix) {

		for(int i = 0; i < aMatrix.length; i++) {
			if(i > 0) System.out.print("\n");
			printArray(aMatrix[i]);
		}
	}

	/**
	 * Prints the game state
	 *
	 * @param      gui   The graphical user interface (if it was called there or not)
	 * @param      t     The text area to write the gamestate to
	 */
	public void printGameState(boolean gui, JTextArea t) {
		if(gui) {
			this.printMatrixGUI(this.board, t);
		}else {
			System.out.print('\n');
			this.printMatrix(this.board);
		}
		
	}

	/**
	 * Level 1 (Setup)
	 *
	 * @param      p     guards personality
	 */
	public void lvl1(char p) {
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
		Guard guard = new Guard(patrolRoute, 'G', cg, p);

		this.leverORkey = findChar(b, 'k');
		this.board = b;
		this.level = 1;
		this.hero = new Hero('H', ch);
		this.guards.add(guard);
	}

	/**
	 * Level 2
	 *
	 * @param      no    number of ogres
	 */
	public void lvl2(int no) {

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
		//int n = ogreN.nextInt(no);
		int rx, ry;
		int cy;
		while(no > 1) {
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
			no--;
		}
		this.board = b;
		this.leverORkey = findChar(b, 'k');
		this.level = 2;
		this.hero = new Hero('H', ch);
		this.guards.clear();
		this.hero.armHero();
	}

	/**
	 * Stuns all the ogres around the hero
	 */
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
				if(this.hero.getCoords().equals(c1) || this.hero.getCoords().equals(c2) ||
						this.hero.getCoords().equals(c3) || this.hero.getCoords().equals(c4)) {
					this.ogres.get(i).stunMe();
				}
			}
		}
	}

	/**
	 * Calls wake ogre for all the ogres in the game
	 */
	public void wakeOgres() {
		for(int i = 0; i < this.ogres.size(); i++) {
			this.ogres.get(i).wakeOgre();
		}
	}

	/**
	 * Player wins
	 */
	public void gg() {
		this.level = 0;
		System.out.println("GG WP");
	}

	/**
	 * Constructs the object.
	 *
	 * @param      level  The level
	 * @param      p      guard personality
	 * @param      no     number of ogres
	 */
	public GameState(int level, char p, int no) {
		this.guards = new LinkedList<Guard>();
		this.ogres = new LinkedList<Ogre>();
		this.exits = new LinkedList<Coords>();
		if (level == 1) {
			this.lvl1(p);
		}else if (level == 2) {
			this.lvl2(no);
		}
	}

	/**
	 * Constructs the object.
	 *
	 * @param      board   The board
	 * @param      exits   The exits
	 * @param      ogres   The ogres
	 * @param      guards  The guards
	 */
	public GameState(char [][] board, LinkedList<Coords> exits,LinkedList<Ogre> ogres, LinkedList<Guard> guards) {
		this.board = board;
		this.guards = new LinkedList<Guard>();
		this.guards = guards;
		this.ogres = new LinkedList<Ogre>();
		this.ogres = ogres;
		this.exits = new LinkedList<Coords>();
		this.exits = exits;
		this.level = 2;
		this.leverORkey = findChar(board, 'k');
		Coords ch = findChar(board, 'H');
		this.hero = new Hero('H', ch);
		if (ch.X() == -1) {
			ch = findChar(board, 'A');
			this.hero = new Hero('A', ch);
			this.hero.armHero();
		}

	}

	/**
	 * Removes entities (hero, guard, ogre and club) 
	 */
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

	/**
	 * Updates the board with the entities new positions
	 */
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
			ogreOnKey = ogreOnKey || (this.ogres.get(i).getCoords().equals(this.leverORkey) &&
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
			clubOnKey = clubOnKey || (this.ogres.get(i).getCCoords().equals(this.leverORkey) &&
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

	/**
	 * If the hero activates the lever unlocks all doors
	 */
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

	/**
	 * Checks if the hero has left the area
	 *
	 * @return     True if yes, false ow
	 */
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

	/**
	 * Moves the guards
	 */
	public void moveGuards() {

		for(int i = 0; i < this.guards.size(); i++) {
			this.guards.get(i).moveGuard(this.board);
		}
	}

	/**
	 * Moves the ogres
	 */
	public void moveOgres() {

		for(int i = 0; i < this.ogres.size(); i++) {
			this.ogres.get(i).moveOgre(this.ogres.get(i).randommov(), this.board);
		}
	}

	/**
	 * Ogres attack
	 */
	public void attackOgres() {
		for (int i = 0; i < this.ogres.size(); i++) {
			this.ogres.get(i).attackOgre(this.board);
		}
	}

	/**
	 * Gets the hero.
	 *
	 * @return     The hero.
	 */
	public Hero getHero() {
		return this.hero;
	}

	/**
	 * Gets the board.
	 *
	 * @return     The board.
	 */
	public char [][] getBoard(){
		return this.board;
	}

	/**
	 * Gets the lever or key.
	 *
	 * @return     The lever or key.
	 */
	public Coords getLeverORkey() {
		return this.leverORkey;
	}

	/**
	 * Gets the exits.
	 *
	 * @return     The exits.
	 */
	public LinkedList<Coords> getExits(){
		return this.exits;
	}
	
	/**
	 * Gets the ogres.
	 *
	 * @return     The ogres.
	 */
	public LinkedList<Ogre> getOgres(){
		return this.ogres;
	}
	
	/**
	 * Gets the guards.
	 *
	 * @return     The guards.
	 */
	public LinkedList<Guard> getGuards(){
		return this.guards;
	}

	/**
	 * Determines if player hit.
	 *
	 * @return     True if player hit, False otherwise.
	 */
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

	/**
	 * Checks if the hero is trying (and can) unlock a door, if so after 1 turn unlocks the door
	 *
	 * @param      x     hero needs 1 turn to unlock, if x == 0, does nothing, but if x == 1 opens the doors
	 *
	 * @return     True when it unlocks the door
	 */
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

	/**
	 * Hero catches key
	 */
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

	/**
	 * Play Level 1
	 *
	 * @param      gui   if its run on the graphical user interface then true
	 * @param      t     Text area to which it should write the board
	 */
	public void playLvl1(boolean gui, JTextArea t) {

		Scanner reader = new Scanner(System.in);
		char key;
		while(this.getHero().isAlive() && this.getLvl() == 1) {
			this.printGameState(gui, t);
			key = reader.next().charAt(0);
			this.getHero().moveHero(key, this.getBoard());
			this.updateBoard();
			this.moveGuards();
			this.updateBoard();
			if(this.getHero().guardScan(this.getBoard())) {
				this.getHero().killHero();
			}
			this.unlockAll();
			if(this.exit()) this.lvl2(3); // 3 ogres default for console
			this.updateBoard();

		}
	}

	/**
	 * Play Level 2
	 *
	 * @param      gui   if it is run on the graphical user interface
	 * @param      t     Text area to which it should write the board
	 */
	public void playLvl2(boolean gui, JTextArea t) {
		Scanner reader = new Scanner(System.in);
		char key;
		int unlocked = 0;
		while (this.getHero().isAlive() && this.getLvl() == 2) {
			if (this.getHero().getRep() == 'K') {
				if(this.checkANUnlock(unlocked)) unlocked++;
			}
			this.wakeOgres();
			this.updateBoard();
			this.printGameState(gui, t);
			key = reader.next().charAt(0);
			this.getHero().moveHero(key, this.getBoard());
			this.stunOgres();
			this.updateBoard();
			this.catchKey();
			this.updateBoard();
			this.moveOgres();
			this.updateBoard();
			this.attackOgres();
			this.updateBoard();
			if(this.exit()) this.gg();
			if (this.isPlayerHit()) {
				this.getHero().killHero();
				this.updateBoard();
				this.printGameState(gui, t);
			}
		}
	}
	
	/**
	 * Converts an array to a string
	 *
	 * @param      anArray  An array
	 *
	 * @return     the string
	 */
	public String arrayToString (char [] anArray) {
		String s = new String();
		
		for(int i = 0; i < anArray.length; i++) {
			s += anArray[i];
		}
		return s;
	}
	
	
	/**
	 * Converts a Matrix to a string
	 *
	 * @param      aMatrix  A matrix
	 *
	 * @return     the string
	 */
	public String matrixToString(char [][] aMatrix) {
		
		String s = "";
		
		for(int i = 0; i < aMatrix.length; i++) {
			if(i > 0)	s += "\n";
			s += arrayToString(aMatrix[i]);
		}
		return s;
		
	}
	
	/**
	 * Prints the GameState in text mode for the gui
	 *
	 * @param      aMatrix  A matrix
	 * @param      t        the JTextArea
	 */
	public void printMatrixGUI(char [][] aMatrix, JTextArea t) {
		
		String s = matrixToString(aMatrix);
		t.setText(s);
		
	}


}
