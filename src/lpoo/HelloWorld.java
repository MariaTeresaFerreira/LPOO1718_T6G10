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

	public static void updatePosition(char key, char[][] aMatrix, int lH, int cH, char ca) {

		if(key == 'd' || key == 'D') {
			if(validateMovement(lH, cH+1, aMatrix)) {
				aMatrix[lH][cH] = ' ';
				aMatrix[lH][cH+1] = ca; //TODO: Nao matar o guarda ;)
			}
		}else if(key == 'a' || key == 'A') {
			if(validateMovement(lH, cH-1, aMatrix)) {
				aMatrix[lH][cH] = ' ';
				aMatrix[lH][cH-1] = ca;
			} //TODO: Nao matar o guarda ;)
		}else if(key == 's' || key == 'S') {
			if(validateMovement(lH+1, cH, aMatrix)) {
				aMatrix[lH][cH] = ' ';
				aMatrix[lH+1][cH] = ca;
			}//TODO: Nao matar o guarda ;)
		}else if(key == 'w' || key == 'W') {
			if(validateMovement(lH - 1, cH, aMatrix)) {
				aMatrix[lH][cH] = ' ';
				aMatrix[lH-1][cH] = ca;
			}//TODO: Nao matar o guarda ;)
			}



	}

	public static int[] findChar(char a[][], char c) {
		
		int b[] = {-1, -1};
		for(int i = 0; i < a.length; i++)
			for(int j = 0; j < a[i].length; j++)
				if (a[i][j] == c) {
					b[0] = i;
					b[1] = j;
				}
		return b;
	}

	public static void alohomora(char [][] aMatrix){
		for(int i = 0; i < aMatrix.length; i++)
			for(int j = 0; j < aMatrix[i].length; j++)
				if(aMatrix[i][j] == 'I')
					aMatrix[i][j] = 'S';
	}
	
	public static boolean nextLvl(int []posH, char[][] a) {
		
		if((posH[0] == 0) || (posH[0] == a.length - 1) || 
				(posH[1] == 0) || posH[1] == a[0].length) return true;
		return false;
	}
	
	public static boolean guardScan(int []posH, char [][] a) {
		
		int i = posH[0];
		int j = posH[1];
		if(a[i - 1][j] == 'G' || a[i + 1][j] == 'G' || a[i][j - 1] == 'G' || a[i][j + 1] == 'G')
			return true;
		
		return false;
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
		
		String patrolRoute = "assssaaaaaasdddddddwwwww";
		int patrolCount = 0;
		
		int [] posH;//Pos Hero
		int [] posK = findChar(tabuleiro, 'K');//Pos Lever
		int [] posG;
		boolean lvl = false;
		
		while(true) { 
			
			printMatrix(tabuleiro);
			
			posH = findChar(tabuleiro, 'H'); //gets the Heros position
			if (posH[0] == -1) break;
			posG = findChar(tabuleiro, 'G'); //gets the Guards position
			lvl = nextLvl(posH, tabuleiro); 
			if(lvl == true) break;
			if(guardScan(posH, tabuleiro)) break;
			if(posH[0] == posK[0] && posH[1] == posK[1])
				alohomora(tabuleiro);
			s = scan.next().charAt(0);
			updatePosition(s, tabuleiro, posH[0], posH[1], 'H');
			updatePosition(patrolRoute.charAt(patrolCount), tabuleiro, posG[0], posG[1], 'G');
			patrolCount++;
			if(patrolCount == patrolRoute.length()) patrolCount = 0;
		}
		
		if (lvl == true) System.out.println("\nGG WP");
		else System.out.println("\nGG EZ");

	}
}

