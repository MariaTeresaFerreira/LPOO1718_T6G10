package dkeep.logic;

public class Guard extends Enemy {
	
	private String route;
	int patrolCount;
	
	public Guard(String nroute, char nrep, Coords coord) {
		super(nrep, coord);
		this.route = nroute;
		this.patrolCount = 0;
	}
	
	public void moveGuard(char board [][]) {
		
		char key = this.route.charAt(this.patrolCount);
		super.moveEnemy(key, board);
		this.patrolCount++;
		if (this.patrolCount <= route.length()) this.patrolCount = 0;
	}
	
}
