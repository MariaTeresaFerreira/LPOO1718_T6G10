package src.dkeep.cli;

import java.util.*;
import src.dkeep.logic.*;

import javax.swing.JTextArea;

public class Main {
	
	public static void main(String [] args) {
		JTextArea x = new JTextArea();
		GameState g = new GameState(1, 'd', 3);
		g.playLvl1(false, x);
		if(g.getHero().isAlive()) g.playLvl2(false, x);
		if (!g.getHero().isAlive()) {
			g.printGameState(false, x);
			System.out.println("GG EZ");
		}
	}
}
