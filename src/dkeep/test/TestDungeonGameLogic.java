package dkeep.test;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;
import dkeep.logic.*;

public class TestDungeonGameLogic {
	
	char [][] map = 	{{ 'X', 'X', 'X', 'X', 'X'}, 
					 { 'X', 'H', ' ', 'G', 'X'},
					 { 'I', ' ', ' ', ' ', 'X'},
					 { 'I', 'k', ' ', ' ', 'X'},
					 { 'X', 'X', 'X', 'X', 'X'}};
	@Test
	public void testMoveHeroIntoFreeCell() {
		LinkedList<Guard> gds = new LinkedList<Guard>();
		LinkedList<Coords> ex = new LinkedList<Coords>();
		LinkedList<Ogre> ogs = new LinkedList<Ogre>();
		GameState g = new GameState(map, ex, ogs, gds);
		assertEquals(new Coords(1, 1), g.getHero().getCoords());
		g.getHero().moveHero('s', g.getBoard());
		assertEquals(new Coords(2, 1), g.getHero().getCoords());
	}
	
	@Test
	public void testHeroIsCapturedByGuard() {
		LinkedList<Guard> gds = new LinkedList<Guard>();
		Coords cg = new Coords(1, 3);
		String r = "";
		char rep = 'G';
		Guard e = new Guard(r, rep, cg);
		gds.add(e);
		LinkedList<Coords> ex = new LinkedList<Coords>();
		LinkedList<Ogre> ogs = new LinkedList<Ogre>();
		GameState g = new GameState(map, ex, ogs, gds);
		assertEquals(true, g.getHero().isAlive());
		g.getHero().moveHero('d', g.getBoard());
		g.updateBoard();
		g.printGameState();
		if( g.getHero().guardScan(g.getBoard())) {
			g.getHero().killHero();
		}
		assertEquals(false, g.getHero().isAlive());
	}

}
