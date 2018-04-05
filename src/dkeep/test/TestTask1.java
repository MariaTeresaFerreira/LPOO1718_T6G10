package dkeep.test;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;
import dkeep.logic.*;

public class TestTask1 {
	
	char [][] map = {{ 'X', 'X', 'X', 'X', 'X'}, 
					 { 'X', 'H', ' ', 'G', 'X'},
					 { 'I', ' ', ' ', ' ', 'X'},
					 { 'I', 'k', ' ', ' ', 'X'},
					 { 'X', 'X', 'X', 'X', 'X'}};
	@Test
	public void testMoveHero() {
		//TASK 1: Tests 1 2 and 4
		LinkedList<Guard> gds = new LinkedList<Guard>();
		LinkedList<Coords> ex = new LinkedList<Coords>();
		LinkedList<Ogre> ogs = new LinkedList<Ogre>();
		GameState g = new GameState(map, ex, ogs, gds);
		assertEquals(new Coords(1, 1), g.getHero().getCoords());
		g.getHero().moveHero('a', g.getBoard()); //Test 2 --> hero tries to move into a wall
		assertEquals(new Coords(1, 1), g.getHero().getCoords());
		g.getHero().moveHero('s', g.getBoard()); //Test 1 --> hero moves to a free cell
		assertEquals(new Coords(2, 1), g.getHero().getCoords());
		g.getHero().moveHero('a', g.getBoard()); //Test 4 --> hero tries to move into a closed door
		assertEquals(new Coords(2, 1), g.getHero().getCoords());
		
	}
	
	@Test
	public void testHeroIsCapturedByGuard() {
		//Test 3 --> Read Name
		LinkedList<Guard> gds = new LinkedList<Guard>();
		Coords cg = new Coords(1, 3);
		Guard e = new Guard("", 'G', cg, 's');
		gds.add(e);
		LinkedList<Coords> ex = new LinkedList<Coords>();
		LinkedList<Ogre> ogs = new LinkedList<Ogre>();
		GameState g = new GameState(map, ex, ogs, gds);
		assertTrue(g.getHero().isAlive());
		g.getHero().moveHero('d', g.getBoard());
		g.updateBoard();
		if( g.getHero().guardScan(g.getBoard())) {
			g.getHero().killHero();
		}
		assertEquals(false, g.getHero().isAlive());
	}

	@Test
	public void testLever() {
		//Tests 5 and 6
		LinkedList<Guard> gds = new LinkedList<Guard>();
		LinkedList<Coords> ex = new LinkedList<Coords>();
		LinkedList<Ogre> ogs = new LinkedList<Ogre>();
		Coords e1 = new Coords(2, 0);
		Coords e2 = new Coords(3, 0);
		ex.add(e1);
		ex.add(e2);
		GameState g = new GameState(map, ex, ogs, gds);
		g.getHero().moveHero('s', g.getBoard());
		g.getHero().moveHero('s', g.getBoard());
		g.unlockAll();
		g.updateBoard();
		//Test 5 -->  Hero moves into the lever and exit doors open
		assertEquals(true, g.getHero().getCoords().equals(g.getLeverORkey()));
		assertEquals('S', g.getBoard()[e1.X()][e1.Y()]);
		assertEquals('S', g.getBoard()[e2.X()][e2.Y()]);
		g.getHero().moveHero('a', g.getBoard());
		if(g.exit()) g.lvl2(3);
		//Test 6 --> Hero moves into the open Dungeon exit doors and progresses into the Keep
		assertEquals(2, g.getLvl());
			
	}
	
}
