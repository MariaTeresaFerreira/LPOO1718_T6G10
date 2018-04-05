package dkeep.test;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import dkeep.logic.Coords;
import dkeep.logic.GameState;
import dkeep.logic.Guard;
import dkeep.logic.Ogre;

public class TestTask4 {
	
	char mapJoust[][] = {{ 'X', 'X', 'X', 'X', 'X','X'}, 
	 					{ 'X', 'A', ' ', ' ', ' ', 'X'},
	 					{ 'X', ' ', ' ', ' ', 'O' ,'X'},
	 					{ 'X', 'X', 'X', 'X', 'X', 'X'}};
	
	char mapRoutes[][] = {{'X', 'X', 'X', 'X'},
							{'X', ' ', ' ', 'X'},
							{'X', ' ', 'G', 'X'},
							{'X', 'X', 'X', 'X'}
						};
	
	@Test
	public void TestStunOgre() {
		LinkedList<Guard> gds = new LinkedList<Guard>();
		LinkedList<Coords> ex = new LinkedList<Coords>();
		LinkedList<Ogre> ogs = new LinkedList<Ogre>();
		Coords co = new Coords(2, 4);
		Ogre og = new Ogre('O', co, co, '*');
		ogs.add(og);
		GameState g = new GameState(mapJoust, ex, ogs, gds);
		int n = 6;
		while(n > 0) {
			g.getHero().moveHero('D', g.getBoard());
			g.stunOgres();
			if (g.getOgres().get(0).getWoke() == 0) {
				g.getOgres().get(0).moveEnemy('A', g.getBoard());
			}
			if (n == 3 || n == 4 || n == 5) {  
				//Verifies that ogre was stunned for 3 turns
				assertFalse(g.getOgres().get(0).getWoke() == 0);
				assertEquals('8', g.getOgres().get(0).getRep());
			}
			
			if (n <= 2) {
				//Verifies that ogre awakened
				assertTrue(g.getOgres().get(0).getWoke() == 0);
				assertEquals('O', g.getOgres().get(0).getRep());
			}
			g.wakeOgres();
			n--;
		}
		//Verifies that the ogre kept walking, after waking up and reached his final pos.
		assertEquals(new Coords(2, 1), g.getOgres().get(0).getCoords());
		
	}
	
	@Test
	public void TestRookieGuard() {
		LinkedList<Guard> gds = new LinkedList<Guard>();
		LinkedList<Coords> ex = new LinkedList<Coords>();
		LinkedList<Ogre> ogs = new LinkedList<Ogre>();
		Coords cg = new Coords(2, 2);
		String route = "wasd";
		Guard og = new Guard(route, 'G', cg);
		og.setPersona('r'); //ROOKIE
		gds.add(og);
		int i = 0, j = 0;
		GameState g = new GameState(mapRoutes, ex, ogs, gds);
		while(j < route.length()*2) {
			//Verifies that the rookie moved into the expected position
			assertEquals(g.getGuards().get(0).getRoute().charAt(g.getGuards().get(0).getPatrolCount()), 
					g.getGuards().get(0).getRoute().charAt(i));
			g.moveGuards();
			i++;
			if (i == route.length()) i = 0;
			j++;
		} 
	} 

	public static boolean equalInverted(char i, char j) {
		if (i == 'w') return j == 's';
		else if (i == 's') return j == 'w';
		else if (i == 'a') return j == 'd';
		else return j == 'a'; 
	}
	
	@Test (timeout = 1000)
	public void TestSuspiciousGuard() {
		LinkedList<Guard> gds = new LinkedList<Guard>();
		LinkedList<Coords> ex = new LinkedList<Coords>();
		LinkedList<Ogre> ogs = new LinkedList<Ogre>();
		Coords cg = new Coords(2, 2);
		String route1 = "wasd", route2 = "wasd";
		Guard og = new Guard(route1, 'G', cg);
		og.setPersona('s');//SUSPICIOUS 
		gds.add(og);
		GameState g = new GameState(mapRoutes, ex, ogs, gds);
		boolean invert = false, revert = false;
		Integer i = 0;
		while(!invert || !revert) {
			g.moveGuards();
			if(!g.getGuards().get(0).getRoute().equals(route2)) {
				invert = true;
				i = route2.length() - g.getGuards().get(0).getPatrolCount();
				i--;
				//Verifies that the guard is walking backwards
				assertTrue(equalInverted(g.getGuards().get(0).getRoute().charAt(g.getGuards().get(0).getPatrolCount()),
						route2.charAt(i)));
			}
			if (invert == true && g.getGuards().get(0).getRoute().equals(route2)) {
				revert = true;
			}
		}
		assertTrue(invert && revert); 
	}
	
	@Test (timeout = 1000)
	public void TestDrunkenGuard() {
		LinkedList<Guard> gds = new LinkedList<Guard>();
		LinkedList<Coords> ex = new LinkedList<Coords>();
		LinkedList<Ogre> ogs = new LinkedList<Ogre>();
		Coords cg = new Coords(2, 2);
		String route1 = "wasd", route2 = "wasd";
		Guard og = new Guard(route1, 'G', cg);
		og.setPersona('d');//DRUNKEN 
		gds.add(og);
		GameState g = new GameState(mapRoutes, ex, ogs, gds);
		boolean invert = false, revert = false, asleep = false;
		Integer i = 0;
		int j = 0;
		while(!invert || !revert || !asleep) {
			g.moveGuards();
			if (g.getGuards().get(0).getStatus() == 1) {
				//Verifies if the guard has stopped moving and if his representation has changed to g
				assertEquals(' ', g.getGuards().get(0).getNextMov());
				assertEquals('g', g.getGuards().get(0).getRep());
				asleep = true;
			}
			if(!g.getGuards().get(0).getRoute().equals(route2)) {
				invert = true;
				i = route2.length() - g.getGuards().get(0).getPatrolCount();
				i--;
				//Verifies that the guard is walking backwards
				assertTrue(equalInverted(g.getGuards().get(0).getRoute().charAt(g.getGuards().get(0).getPatrolCount()),
						route2.charAt(i)));
			}
			if (invert == true && g.getGuards().get(0).getRoute().equals(route2)) {
				revert = true;
			}
		}
		assertTrue(invert && revert); 
	}

}
