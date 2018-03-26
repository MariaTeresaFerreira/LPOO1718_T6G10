package dkeep.test;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import dkeep.logic.Coords;
import dkeep.logic.GameState;
import dkeep.logic.Guard;
import dkeep.logic.Ogre;

public class TestTask2 {
	
	char map[][] = {{ 'X', 'X', 'X', 'X', 'X'}, 
			 		{ 'X', 'H', ' ', 'O', 'X'},
			 		{ 'X', ' ', ' ', ' ', 'X'},
			 		{ 'I', ' ', 'k', ' ', 'X'},
			 		{ 'X', 'X', 'X', 'X', 'X'}};
	
	
	
	@Test
	public void testOgreKill() { 
		//TASK 2: Test 1 --> Morte do Primaço (Cousinski's Death)
		LinkedList<Guard> gds = new LinkedList<Guard>();
		LinkedList<Coords> ex = new LinkedList<Coords>();
		LinkedList<Ogre> ogs = new LinkedList<Ogre>();
		Coords co = new Coords(1, 3);
		Ogre og = new Ogre('O', co, co, '*');
		ogs.add(og);
		GameState g = new GameState(map, ex, ogs, gds);
		g.getHero().moveHero('D', g.getBoard());
		g.updateBoard();
		if (og.ogreScan(g.getBoard())); g.getHero().killHero();
		assertFalse(g.getHero().isAlive());
	}
	
	@Test
	public void testDoor() {
		//TASK 2: Tests 2 to 5 (Remainder)
		LinkedList<Guard> gds = new LinkedList<Guard>();
		LinkedList<Coords> ex = new LinkedList<Coords>();
		LinkedList<Ogre> ogs = new LinkedList<Ogre>();
		int unlocked = 0;
		Coords ex1 = new Coords(3, 0);
		ex.add(ex1);
		GameState g = new GameState(map, ex, ogs, gds);
		g.getHero().moveHero('S', g.getBoard());
		g.getHero().moveHero('S', g.getBoard());
		g.updateBoard();
		g.getHero().moveHero('A', g.getBoard());
		g.updateBoard();
		//Test 3 --> Hero tries to move into a closed door (doesn't have key)
		assertEquals(new Coords(3, 1), g.getHero().getCoords());
		g.getHero().moveHero('D', g.getBoard());
		g.catchKey();
		g.updateBoard();
		//Test 2 --> Hero gets Key
		assertEquals('K', g.getHero().getRep());
		g.getHero().moveHero('A', g.getBoard());
		g.getHero().moveHero('A', g.getBoard());
		if(g.checkANUnlock(unlocked)) unlocked++;
		g.updateBoard();
		g.getHero().moveHero('A', g.getBoard());
		if(g.checkANUnlock(unlocked)) unlocked++;
		g.updateBoard();
		//Test 4 --> Hero opens door
		assertEquals('S', g.getBoard()[3][0]);
		g.getHero().moveHero('a', g.getBoard());
		if(g.exit()) g.lvl3();
		//Test 5 --> Hero wins :P
		assertEquals(3, g.getLvl());
	}

}
