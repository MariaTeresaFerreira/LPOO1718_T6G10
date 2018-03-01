package dkeep.logic;

import java.util.*;

public class Guard extends Enemy {
	
	private String route;
	private String movs;
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
		this.movs = "";
		this.persona = this.generatePersona();
	}
	
	public void getNextMov() {
		
		int i = this.movs.length();
		this.movs += this.route.substring(i, i+1);
		
	}
	
	public void nextMov() {
		
		if (movs == "" || movs.length() == route.length()) {
			this.movs = this.route.substring(0, 1);
		}else {
			this.getNextMov();
		}
		
	}
	
	public void moveGuard(char board [][]) {
		
		this.nextMov();
		char key = this.movs.charAt(this.movs.length() - 1);
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
			System.out.println("ANTES: " + key + key.length());
			System.out.println(key == "d");
			aux2 = this.invertKey(key);
			System.out.println("DEPOIS: " + aux2);
			aux1 += aux2;
		}

		this.route = aux1;

	}
	
	
	
}
