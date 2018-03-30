package dkeep.cli;

import java.util.*;
import dkeep.logic.*;

public class Main {
	
	public static void playLvl1(GameState g) {
		
		Scanner reader = new Scanner(System.in);
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
	}
	
	public static void playLvl2(GameState g) {
		Scanner reader = new Scanner(System.in);
		char key;
		int unlocked = 0;
		while (g.getHero().isAlive() && g.getLvl() == 2) {
			if (g.getHero().getRep() == 'K') {
				if(g.checkANUnlock(unlocked)) unlocked++;
			}
			g.wakeOgres();
			g.updateBoard();
			g.printGameState();
			key = reader.next().charAt(0);
			g.getHero().moveHero(key, g.getBoard());
			g.stunOgres();
			g.updateBoard();
			g.catchKey();
			g.updateBoard();
			g.moveOgres();
			g.updateBoard();
			g.attackOgres();
			g.updateBoard();
			if(g.exit()) g.gg();
			if (g.isPlayerHit()) {
				g.getHero().killHero();
				g.updateBoard();
				g.printGameState();
			}
		}
	}
	
	public static void main(String [] args) {
		GameState g = new GameState(1);
		playLvl1(g);
		if(g.getHero().isAlive()) playLvl2(g);
		if (!g.getHero().isAlive()) {
			g.printGameState();
			System.out.println("GG EZ");
		}
	}
}
