package dkeep.cli;

import java.util.*;
import dkeep.logic.*;

public class Main {
	
	
	
	public static void main(String [] args) {
		GameState g = new GameState(1, 'd');
		g.playLvl1();
		if(g.getHero().isAlive()) g.playLvl2();
		if (!g.getHero().isAlive()) {
			g.printGameState();
			System.out.println("GG EZ");
		}
	}
}
