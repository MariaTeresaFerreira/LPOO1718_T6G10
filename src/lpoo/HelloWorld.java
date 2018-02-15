package lpoo;

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
	
	public static void main(String args[]) {
		System.out.println("Hello World");
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
		
			printMatrix(tabuleiro);
		}
}

