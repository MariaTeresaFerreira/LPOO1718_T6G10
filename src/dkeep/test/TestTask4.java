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
	
	char [][] mapLvl1 = {
			{'X','X','X','X','X','X','X','X','X','X'},
			{'X','H',' ',' ','I',' ','X',' ','G','X'},
			{'X','X','X',' ','X','X','X',' ',' ','X'},
			{'X',' ','I',' ','I',' ','X',' ',' ','X'},
			{'X','X','X',' ','X','X','X',' ',' ','X'},
			{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X','X','X',' ','X','X','X','X',' ','X'},
			{'X',' ','I',' ','I',' ','X','k',' ','X'},
			{'X','X','X','X','X','X','X','X','X','X'}
	};
	

	public static boolean equalInverted(char i, char j) {
		if (i == 'w') return j == 's';
		else if (i == 's') return j == 'w';
		else if (i == 'a') return j == 'd';
		else return j == 'a'; 
	} 
	
	public static boolean equalMatrix(char [][] a, char [][] b) {
		
		if (a.length != b.length) return false;
		
		for (int i = 0; i < a.length; i++) {
			if (a[i].length != b[i].length) return false;
			for (int j = 0; j < a[i].length; j++) {
				if (a[i][j] != b[i][j]) return false;
			}
		}
		
		return true;
	}
	
	public static int countChars(char [][] a, char b) {
		int count = 0;
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				if (a[i][j] == b) count++;
			}
		}
		
		return count;
	}
	
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
		Guard og = new Guard(route, 'G', cg, 'r'); //ROOKIE
		gds.add(og);
		int i = 0, j = 0, k;
		GameState g = new GameState(mapRoutes, ex, ogs, gds);
		while(j < route.length()*2) {
			k = g.getGuards().get(0).getPatrolCount();
			//Verifies that the rookie moved into the expected position
			assertEquals(g.getGuards().get(0).getRoute().charAt(g.getGuards().get(0).getPatrolCount()), 
					g.getGuards().get(0).getRoute().charAt(i));
			g.moveGuards();
			if (k != 0 && k != route.length() - 1) {
				assertTrue(g.getGuards().get(0).getPatrolCount() != 0);
			}
			i++;
			if (i == route.length()) i = 0;
			j++;
		} 
	} 
	
	@Test (timeout = 1000)
	public void TestSuspiciousGuard() {
		LinkedList<Guard> gds = new LinkedList<Guard>();
		LinkedList<Coords> ex = new LinkedList<Coords>();
		LinkedList<Ogre> ogs = new LinkedList<Ogre>();
		Coords cg = new Coords(2, 2);
		String route1 = "wasd", route2 = "wasd";
		Guard og = new Guard(route1, 'G', cg, 's');//SUSPICIOUS 
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
		Guard og = new Guard(route1, 'G', cg, 'd');//DRUNKEN 
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

	@Test
	public void TestLvl1() {
		GameState g1 = new GameState(1, 'r', 1);
		GameState g2 = new GameState(1, 'd', 1);
		GameState g3 = new GameState(1, 's', 1);
		
		assertEquals('r', g1.getGuards().get(0).getPersona());
		assertEquals('d', g2.getGuards().get(0).getPersona());
		assertEquals('s', g3.getGuards().get(0).getPersona());
		
		assertTrue(equalMatrix(g1.getBoard(), mapLvl1));
		assertTrue(equalMatrix(g2.getBoard(), mapLvl1));
		assertTrue(equalMatrix(g3.getBoard(), mapLvl1));
		
		assertEquals(new Coords(1, 1), g1.getHero().getCoords());
		assertEquals(new Coords(1, 1), g2.getHero().getCoords());
		assertEquals(new Coords(1, 1), g3.getHero().getCoords());
		
		assertEquals(new Coords(1, 8), g1.getGuards().get(0).getCoords());
		assertEquals(new Coords(1, 8), g2.getGuards().get(0).getCoords());
		assertEquals(new Coords(1, 8), g3.getGuards().get(0).getCoords());
		
		Coords ex1 = new Coords(5, 0);
		Coords ex2 = new Coords(6, 0);
		
		assertTrue(ex1.equals(g1.getExits().get(0)) || ex2.equals(g1.getExits().get(0)));
		assertTrue(ex1.equals(g2.getExits().get(0)) || ex2.equals(g2.getExits().get(0)));
		assertTrue(ex1.equals(g3.getExits().get(0)) || ex2.equals(g3.getExits().get(0)));
		
		g1.getHero().moveHero('d', g1.getBoard());
		g1.updateBoard();
		assertEquals(1, countChars(g1.getBoard(), 'H'));
	}
	
	@Test
	public void TestLvl2() {
		GameState g1 = new GameState(1, 'r', 1);
		g1.lvl2(1);
		assertTrue(g1.getHero().isArmed());
		assertEquals(g1.getHero().getRep(), 'A');
		//Verifies that the exits were updated
		Coords ex1 = new Coords(1, 0);
		assertTrue(ex1.equals(g1.getExits().get(0)));
		assertTrue(g1.getExits().size() == 1);
		assertTrue(g1.getGuards().size() == 0);
		for(int j = 0; j < g1.getOgres().size(); j++) {
			assertTrue(g1.getOgres().get(j).getCoords().X() != 0);
			assertTrue(g1.getOgres().get(j).getCoords().Y() != 0);
		}
	}
	
	@Test
	public void TestMiscellaneous(){
		
	}
}
