package dkeep.logic;

import java.util.*;

public class GameState {

	private char [][] board;
	int level;
	private Hero hero;
	private LinkedList<Guard> guards;
	private LinkedList<Ogre> ogres;
	
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
		String patrolRoute = "assssaaaaaasdddddddwwwww";
		Guard guard = new Guard(patrolRoute, 'G', cg);
		
		this.board = b;
		this.level = 1;
		this.hero = new Hero('H', ch);
		this.guards.add(guard);
	}
	
	public GameState(int level) {
		this.guards = new LinkedList<Guard>();
		this.ogres = new LinkedList<Ogre>();
		this.lvl1();
	}
	
	public static void main(String args[]) {
		
		GameState g = new GameState(1);
		g.printGameState();
	}
}
