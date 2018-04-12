package dkeep.logic;

import java.util.*;

public class Guard extends Enemy {
	
	private String route;
	private Integer patrolCount;
	private char persona;
		
	/**
	 * Constructs the object
	 *
	 * @param      nroute  The route
	 * @param      nrep    The representation
	 * @param      coord   The coordinatess
	 * @param      p       The Personality
	 */
	public Guard(String nroute, char nrep, Coords coord, char p) {
		super(nrep, coord);
		this.route = nroute;
		this.patrolCount = 0;
		this.persona = p;
		super.status = 0;
	}
	
	/**
	 * Gets the next mov as a key, if the guard is asleep (super.status == 1) returns ' '
	 *
	 * @return     The next mov as a key
	 */
	public char getNextMov() {
		if (super.status == 1) return ' ';
		char c = this.route.charAt(this.patrolCount.intValue());
		this.patrolCount++;
		if (this.patrolCount.intValue() == this.route.length()) this.patrolCount = 0;
		return c;
		
	}
	

	/**
	 * Gets the patrol count.
	 *
	 * @return     The patrol count.
	 */
	public int getPatrolCount() {
		return this.patrolCount.intValue();
	}
	
	/*
	 * Activates any random effects that might affect the guard and moves it
	 * 
	 * @param board		Game board
	 */
	public void moveGuard(char board [][]) {
		
		this.updateGuard();
		char key = this.getNextMov();
		super.moveEnemy(key, board);
	}
	
	/**
	 * Auxiliar to invertRoute takes a key as 'WASD' and returns it's oposite (w -- a, s --- d ...)
	 *
	 * @param      key   The key
	 *
	 * @return     The 'inverted' key
	 */
	public String invertKey(String key){
		if(key.equals("w")){
			return "s";
		}else if (key.equals("s")){
			return "w";
		}else if (key.equals("a")){
			return "d";
		}else if (key.equals("d")){
			return "a";
		}

		return "FALHOU";
	}


	/**
	 * Reverses the guards patrol ('wasd' -- 'dsaw')
	 */
	public void invertPatrol(){
		String aux1 = "";
		String key, aux2;
		for (int i = 1; i <= this.route.length(); i++){
			key = this.route.substring(this.route.length() - i, this.route.length() - (i - 1));
			aux2 = this.invertKey(key);
			aux1 += aux2;
		}

		this.route = aux1;
		 
	}
	
	/**
	 * Makes the guard 'go backwards'
	 */
	public void invertRoute(){
		int x = this.patrolCount.intValue();
		if (x == 0) x = 1;
		this.patrolCount = this.route.length() - x;
		if (this.patrolCount.intValue() == this.route.length()) this.patrolCount = 0;
		this.invertPatrol();
	}
	

	/**
	 * Random Event 
	 *
	 * @return     true or false (40% chance to roll true)
	 */
	public boolean randomEvent () {
		
		Random randomno = new Random();
		int n = randomno.nextInt(100);
		if(n >= 0 && n < 40 ) {
			return true;
		}
		return false;
		
	}
	
	/**
	 * Random Event specifically  for waking the drunken guard
	 *
	 * @return     50% chance to wake up
	 */
	public boolean wakeUp() {
		Random randomno = new Random();
		int n = randomno.nextInt(2);
		if (n == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * How the suspicious guard reacts to random event
	 */
	public void eventSuspicious() {
		if (this.randomEvent()) {
			this.invertRoute();
		}
	}
	
	/**
	 * How the drunken guard reacts to random event
	 */
	public void eventDrunken() {
		if(this.randomEvent() || (super.status.intValue() == 1 && this.wakeUp())) {
			if(super.status.intValue() == 0 || super.status.intValue() == 2) {
				super.status = 1;
				this.setRep('g');
			}else if (super.status == 1) {
				if(this.wakeUp()) {
					if(this.randomEvent()) {
						this.invertRoute();
					}
					super.status = 0;
					this.setRep('G');
				}
			}
		}		
	}
	
	/**
	 * If the guard is not a rookie triggers it's events
	 */
	public void updateGuard() {
		if(this.persona == 's') {
			this.eventSuspicious();
		}else if(this.persona == 'd') {
			this.eventDrunken();
		}
		
	}
	
	/**
	 * Gets the personality of the guard
	 *
	 * @return     The personality
	 */
	public char getPersona() {
		return this.persona;
	}
	
	/**
	 * Gets the route
	 *
	 * @return     The route
	 */
	public String getRoute() {
		return this.route;
	}
	
	/**
	 * Gets the status
	 *
	 * @return     The status
	 */
	public int getStatus() {
		return super.status.intValue();
	}
	
	
}
