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
		this.patrolCount = 23;
		this.persona = 'd';//this.generatePersona();
		super.status = 2;
	}
	
	public char getNextMov() {
		String c = "";
		if(super.status.intValue() == 0) {
			this.patrolCount ++;
			if(this.patrolCount.intValue() == this.route.length()) {
				this.patrolCount = 0;
			}
			c += this.route.charAt(this.patrolCount - 1);
		}else if (super.status.intValue() == 2) {
			this.patrolCount--;
			if(this.patrolCount.intValue() <= 0) {
				this.patrolCount = this.route.length() - 2;
			}
			c += this.route.charAt(this.patrolCount + 1);
			this.invertKey(c);
		}else {
			c += 'a';
		}
		
		System.out.println(c);
		return c.charAt(0);
		
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
//		String aux1 = "";
//		String key, aux2;
//		for (int i = 1; i <= this.route.length(); i++){
//			key = this.route.substring(this.route.length() - i, this.route.length() - (i - 1));
//			System.out.println("ANTES: " + key + key.length());
//			System.out.println(key == "d");
//			aux2 = this.invertKey(key);
//			System.out.println("DEPOIS: " + aux2);
//			aux1 += aux2;
//		}
//
//		this.route = aux1;

		System.out.println("inverteu");
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
			if(super.status.intValue() == 2) {
				super.status = 0;
				System.out.println("DESVERTOU");
			}else {
				super.status = 2;
				System.out.println("REVERTOU");
			}
		}
	}
	
	public void eventDrunken() {
		if(this.randomEvent() || (super.status.intValue() == 1 && this.wakeUp())) {
			if(super.status.intValue() == 0 || super.status.intValue() == 2) {
				super.status = 1;
				System.out.println("bebedoso dormidoso");
			}else if (super.status == 1) {
				if(this.wakeUp()) {//Acordar e andar para a frente
					if(this.randomEvent()) {
						this.invertPatrol();
						super.status = 2;
						System.out.println("bebedoso dormidoso got woke e ta de marchatras");
					}
					else {
						super.status = 0;
						System.out.println("bebedoso dormidoso got woke e ta de frente");
					}
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
	
	
}
