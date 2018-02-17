package lpoo;

import java.util.*;


public class HelloWorld {




	public static void printArray(char[] anArray) {
		for (int i = 0; i < anArray.length; i++) {
			if (i > 0) System.out.print(" ");
			System.out.print(anArray[i]);
		}
	}

	public static void printMatrix(char[][] aMatrix) {

		for(int i = 0; i < aMatrix.length; i++) {
			if(i > 0) System.out.print("\n");
			printArray(aMatrix[i]);
		}
	}

	public static boolean validateMovement(int l, int c, char[][] aMatrix) {
		return aMatrix[l][c] != 'X' && aMatrix[l][c] != 'I';
	}

	public static void updatePosition(char key, char[][] aMatrix, int lH, int cH) {

		if(key == 'd' || key == 'D') {
			if(validateMovement(lH, cH+1, aMatrix)) {
				aMatrix[lH][cH] = ' ';
				aMatrix[lH][cH+1] = 'H'; //TODO: Nao matar o guarda ;)
			}
		}else if(key == 'a' || key == 'A') {
			if(validateMovement(lH, cH-1, aMatrix)) {
				aMatrix[lH][cH] = ' ';
				aMatrix[lH][cH-1] = 'H';
			} //TODO: Nao matar o guarda ;)
		}else if(key == 's' || key == 'S') {
			if(validateMovement(lH+1, cH, aMatrix)) {
				aMatrix[lH][cH] = ' ';
				aMatrix[lH+1][cH] = 'H';
			}//TODO: Nao matar o guarda ;)
		}else if(key == 'w' || key == 'W') {
			if(validateMovement(lH - 1, cH, aMatrix)) {
				aMatrix[lH][cH] = ' ';
				aMatrix[lH-1][cH] = 'H';
			}//TODO: Nao matar o guarda ;)
			}



	}

	public static int[] findHero(char a[][]) {
		int b[] = new int[2];
		for(int i = 0; i < a.length; i++)
			for(int j = 0; j < a[i].length; j++)
				if (a[i][j] == 'H') {
					b[0] = i;
					b[1] = j;
				}
		return b;
	}

	public static void main(String args[]) {
		System.out.println("Hello World");

		Scanner scan = new Scanner(System.in);
		char s = 'd';

		char tabuleiro[][] = {
				{'X','X','X','X','X','X','X','X','X','X'},
				{'X','H',' ',' ','I',' ','X',' ','G','X'},
				{'X','X','X',' ','X','X','X',' ',' ','X'},
				{'X',' ','I',' ','I',' ','X',' ',' ','X'},
				{'X','X','X',' ','X','X','X',' ',' ','X'},
				{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X','X','X',' ','X','X','X','X',' ','X'},
				{'X',' ','I',' ','I',' ','X','K',' ','X'},
				{'X','X','X','X','X','X','X','X','X','X'}
		};
		int [] pos;

		while(true) { 
			printMatrix(tabuleiro);
			pos = findHero(tabuleiro);
			s = scan.next().charAt(0);
			System.out.println(s);
			updatePosition(s, tabuleiro, pos[0], pos[1]);
		}

	}
}

