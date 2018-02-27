package dkeep.logic;

public class Guard extends Enemy {
	
	private String route;
	private String movs;
	
	public Guard(String nroute, char nrep, Coords coord) {
		super(nrep, coord);
		this.route = nroute;
		this.movs = "";
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
	
}
