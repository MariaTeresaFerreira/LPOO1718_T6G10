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
	public void testMoveHero() {
		LinkedList<Guard> gds = new LinkedList<Guard>();
		LinkedList<Coords> ex = new LinkedList<Coords>();
		LinkedList<Ogre> ogs = new LinkedList<Ogre>();
		GameState g = new GameState(map, ex, ogs, gds);
		assertEquals(new Coords(1, 1), g.getHero().getCoords());
		g.getHero().moveHero('a', g.getBoard()); // hero tries to move into a wall
		assertEquals(new Coords(1, 1), g.getHero().getCoords());
		g.getHero().moveHero('s', g.getBoard()); // hero moves to a free cell
		assertEquals(new Coords(2, 1), g.getHero().getCoords());
		g.getHero().moveHero('a', g.getBoard()); // hero tries to move into a closed door
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
		assertTrue(g.getHero().isAlive());
		g.getHero().moveHero('d', g.getBoard());
		g.updateBoard();
		//g.printGameState();
		if( g.getHero().guardScan(g.getBoard())) {
			g.getHero().killHero();
		}
		assertEquals(false, g.getHero().isAlive());
	}

	@Test
	public void testLever() {
		LinkedList<Guard> gds = new LinkedList<Guard>();
		LinkedList<Coords> ex = new LinkedList<Coords>();
		LinkedList<Ogre> ogs = new LinkedList<Ogre>();
		Coords e1 = new Coords(2, 0);
		Coords e2 = new Coords(3, 0);
		ex.add(e1);
		ex.add(e2);
		System.out.println(ex);
		GameState g = new GameState(map, ex, ogs, gds);
		System.out.println(g.getExits());
		g.getHero().moveHero('s', g.getBoard());
		g.getHero().moveHero('s', g.getBoard());
		g.unlockAll();
		g.updateBoard();
		g.printGameState();
		assertEquals(true, g.getHero().getCoords().equals(g.getLeverORkey()));
		assertEquals('S', g.getBoard()[e1.X()][e1.Y()]);
		assertEquals('S', g.getBoard()[e2.X()][e2.Y()]); // Hero moves into the lever and exit doors open
		g.getHero().moveHero('a', g.getBoard());
		if(g.exit()) g.lvl2();
		assertEquals(2, g.getLvl()); // Hero moves into the open Dungeon exit doors and progresses into the Keep
			
	}
	
}
