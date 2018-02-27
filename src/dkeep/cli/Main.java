package dkeep.cli;

import java.util.*;
import dkeep.logic.*;

public class Main {

	public static void main(String [] args) {
		Scanner reader = new Scanner(System.in);
		GameState g = new GameState(1);
		char key;
		while(g.getHero().isAlive() && g.getLvl() == 1) {
			g.printGameState();
			key = reader.next().charAt(0);
			g.getHero().moveHero(key, g.getBoard());
			g.updateBoard();
			g.moveGuards();
			g.updateBoard();
			if(g.getHero().guardScan(g.getBoard())) {
				g.getHero().killHero();
			}
			g.unlockAll();
			if(g.exit()) g.lvl2();
			g.updateBoard();
						
		}
		if (g.getHero().isAlive()) System.out.println("GG WP");
		else System.out.println("GG EZ");
	}
	
}
