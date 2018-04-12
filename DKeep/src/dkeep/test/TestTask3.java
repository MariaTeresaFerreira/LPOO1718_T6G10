package src.dkeep.test;
import static org.junit.Assert.*;
import java.util.LinkedList;

import org.junit.Test;

import src.dkeep.logic.Coords;
import src.dkeep.logic.GameState;
import src.dkeep.logic.Guard;
import src.dkeep.logic.Ogre;

public class TestTask3 {

	char map[][] = {{ 'X', 'X', 'X', 'X', 'X'}, 
	 				{ 'X', 'H', ' ', 'O', 'X'},
	 				{ 'X', ' ', ' ', ' ', 'X'},
	 				{ 'I', ' ', 'k', ' ', 'X'},
	 				{ 'X', 'X', 'X', 'X', 'X'}};
	
	
	@Test(timeout = 1000)
	public void testOgreRandomMovement() {
		boolean w = false, a = false, s = false, d = false, o = false;
		LinkedList<Guard> gds = new LinkedList<Guard>();
		LinkedList<Coords> ex = new LinkedList<Coords>();
		LinkedList<Ogre> ogs = new LinkedList<Ogre>();
		Coords co = new Coords(1, 3);
		Ogre og = new Ogre('O', co, co, '*');
		ogs.add(og);
		GameState g = new GameState(map, ex, ogs, gds);
		Coords cw, ca, cs, cd, coo;
		while(!w || !a || !s || !d || !o) {
			cw = new Coords(co.X() - 1, co.Y());
			ca = new Coords(co.X(), co.Y() - 1);
			cs = new Coords(co.X() + 1, co.Y());
			cd = new Coords(co.X(), co.Y() + 1);
			coo = new Coords(co.X(), co.Y());
			g.getHero().moveHero(og.randommov(), g.getBoard());
			g.moveOgres();
			if(co.equals(cw)) {
				w = true;
			}else if(co.equals(ca)) {
				a = true;
			}else if(co.equals(cs)) {
				s = true;
			}else if(co.equals(cd)) {
				d = true;
			}else if(co.equals(coo)) {
				o = true;
			}
			else {
				fail("Ogre failed to move to the expected position");
			}
		}
		assertTrue(a && s && d && w && o);
	}
	
	@Test(timeout = 1000)
	public void testOgreRandomAttack() {
		boolean w = false, a = false, s = false, d = false, o = false;
		LinkedList<Guard> gds = new LinkedList<Guard>();
		LinkedList<Coords> ex = new LinkedList<Coords>();
		LinkedList<Ogre> ogs = new LinkedList<Ogre>();
		Coords co = new Coords(1, 3);
		Ogre og = new Ogre('O', co, co, '*');
		ogs.add(og);
		GameState g = new GameState(map, ex, ogs, gds);
		Coords cw, ca, cs, cd;// coo;
		while(!w || !a || !s || !d) {
			cw = new Coords(co.X() - 1, co.Y());
			ca = new Coords(co.X(), co.Y() - 1);
			cs = new Coords(co.X() + 1, co.Y());
			cd = new Coords(co.X(), co.Y() + 1);
			g.getHero().moveHero(og.randommov(), g.getBoard());
			g.attackOgres();
			if(co.equals(cw)) {
				w = true;
			}else if(co.equals(ca)) {
				a = true;
			}else if(co.equals(cs)) {
				s = true;
			}else if(co.equals(cd)) {
				d = true;
			}else {
				fail("Ogre failed to attack the expected position");
			}
		}
		assertTrue(a && s && d && w );
	}
	
	
	
	
	
}
