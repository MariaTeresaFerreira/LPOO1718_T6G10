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
		if(a[i - 1][j] == 'G' || a[i + 1][j] == 'G' || a[i][j - 1] == 'G' || a[i][j + 1] == 'G')//Guard
			return true;
		if(a[i - 1][j] == 'O' || a[i + 1][j] == 'O' || a[i][j - 1] == 'O' || a[i][j + 1] == 'O')//Ogre
			return true;
		if(a[i - 1][j] == '$' || a[i + 1][j] == '$' || a[i][j - 1] == '$' || a[i][j + 1] == '$')//Ogre that holds the key
			return true;
		
		return false;
	}

	public static char[][] lvl2(){

		char[][] tabul = {
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
		return tabul;
	}
	
	public static char randommov() {
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
				{'X',' ','I',' ','I',' ','X','k',' ','X'},
				{'X','X','X','X','X','X','X','X','X','X'}
		};
		
		String patrolRoute = "assssaaaaaasdddddddwwwww";
		int patrolCount = 0;
		
		char hero = 'H', guard = 'G', ogre = 'O', key = 'k', lever = 'k';
		int [] posH = findChar(tabuleiro, hero);//Pos Hero
		int [] posK = findChar(tabuleiro, lever);//Pos Lever
		int [] posG = findChar(tabuleiro, guard);
		boolean lvl = true;//mudar para false
		while(posH[0] != -1) { 
			
			printMatrix(tabuleiro);
			
			posH = findChar(tabuleiro, hero); //gets the Heros position
			posG = findChar(tabuleiro, guard); //gets the Guards position
			lvl = nextLvl(posH, tabuleiro); 
			if(lvl == true) break;
			if(guardScan(posH, tabuleiro)) break;
			if(posH[0] == posK[0] && posH[1] == posK[1])
				alohomora(tabuleiro);
			s = scan.next().charAt(0);
			updatePosition(s, tabuleiro, posH[0], posH[1], hero);
			updatePosition(patrolRoute.charAt(patrolCount), tabuleiro, posG[0], posG[1], guard);
			patrolCount++;
			if(patrolCount == patrolRoute.length()) patrolCount = 0;
		}
		
		System.out.print('\n');
		guard = 'O';
		
		if(lvl == true) {
			tabuleiro = lvl2();
			posH = findChar(tabuleiro, hero);
			lvl = false;
			boolean unlock = false;
			posK = findChar(tabuleiro, lever);
			while(posH[0] != -1) {
				if(lvl == true) break;
				if(guardScan(posH, tabuleiro)) break;
				printMatrix(tabuleiro);
				posH = findChar(tabuleiro, hero);
				posG = findChar(tabuleiro, guard);
				if(posG[0] == -1) posG = findChar(tabuleiro, lever);
				if(posH[0] == posK[0] && posH[1] == posK[1]) hero = 'K';
				if(posG[0] == posK[0] && posG[1] == posK[1] && tabuleiro[posK[0]][posK[1]] != 'O') {
					guard = '$';
					tabuleiro[posG[0]][posG[1]] = '$';
				}else {
					guard = 'O';
				}
				
				if (hero != 'K' && guard != '$') {
					tabuleiro[posK[0]][posK[1]] = 'k';
				}
				
				s = scan.next().charAt(0);
				if (posH[0]!= -1)updatePosition(s, tabuleiro, posH[0], posH[1], hero);
				if (posH[0] == 1 && posH[1] == 1 && unlock == false && hero == 'K') unlock = true;
				if (unlock == true) tabuleiro[1][0] = 'S';
				updatePosition(randommov(), tabuleiro, posG[0], posG[1], guard);
				lvl = nextLvl(posH, tabuleiro);
				if(posH[0] == -1) lvl = true;
			}
		}
		
		if (lvl == true) System.out.println("\nGG WP");
		else System.out.println("\nGG EZ");

	}
}

