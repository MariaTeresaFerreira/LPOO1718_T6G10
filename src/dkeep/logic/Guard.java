package dkeep.logic;

import java.util.*;

public class Guard extends Enemy {
	
	private String route;
	private Integer patrolCount;
	private char persona;
	
	public char generatePersona() {
		Random randomno = new Random();
		int x = randomno.nextInt(3);
		char ret;
		if(x == 0){
			ret = 'r';
		}else if (x == 1) {
			ret = 'd';
		}else {
			ret = 's';
		}
		
		return ret;
	}
	
	public Guard(String nroute, char nrep, Coords coord) {
		super(nrep, coord);
		this.route = nroute;
		this.patrolCount = 0;
		this.persona = this.generatePersona();
		super.status = 0;
	}
	
	public char getNextMov() {
		if (super.status == 1) return ' ';
		char c = this.route.charAt(this.patrolCount.intValue());
		this.patrolCount++;
		if (this.patrolCount.intValue() == this.route.length()) this.patrolCount = 0;
		return c;
		
	}
	
	public int getPatrolCount() {
		return this.patrolCount.intValue();
	}
	
	public void moveGuard(char board [][]) {
		
		this.updateGuard();
		char key = this.getNextMov();
		super.moveEnemy(key, board);
	}
	
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
	
	public void invertRoute(){
		int x = this.patrolCount.intValue();
		if (x == 0) x = 1;
		this.patrolCount = this.route.length() - x;
		if (this.patrolCount.intValue() == this.route.length()) this.patrolCount = 0;
		this.invertPatrol();
	}
	
	public boolean randomEvent () {
		
		Random randomno = new Random();
		int n = randomno.nextInt(100);
		if(n >= 0 && n < 40 ) {
			return true;
		}
		return false;
		
	}
	
	public boolean wakeUp() {
		Random randomno = new Random();
		int n = randomno.nextInt(2);
		if (n == 0) {
			return true;
		}
		return false;
	}
	
	public void eventSuspicious() {
		if (this.randomEvent()) {
			this.invertRoute();
		}
	}
	
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
	
	public void updateGuard() {
		if(this.persona == 's') {
			this.eventSuspicious();
		}else if(this.persona == 'd') {
			this.eventDrunken();
		}
		
	}
	
	public void setPersona(char p) {
		this.persona = p;
	}
	
	public String getRoute() {
		return this.route;
	}
	
	public int getStatus() {
		return super.status.intValue();
	}
	
	
}
