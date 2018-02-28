package dkeep.cli;

import java.util.*;
import dkeep.logic.*;

public class Main {

	public static void main(String [] args) {
		GameState g = new GameState(1);
		g.playLvl1();
		if(g.getHero().isAlive()) g.playLvl2();
		if (g.getHero().isAlive()) System.out.println("GG WP");
		else System.out.println("GG EZ");
	}
}
