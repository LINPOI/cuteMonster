package finalproject2;

public class Enemy {
	private double attack = 1.0d;
	private double hp = 1.0d;
	private double addition = 1.0d;
	private double score = 1.0d;
	private static double immunity = 0.0d;

	public Enemy(double immunity) {
		this.immunity = immunity;
	}

	public Enemy() {
		// TODO Auto-generated constructor stub
	}

	public Enemy createEnemy(double additionMultiplier, double attackMultiplier, double hpMultiplier, double score) {
		this.addition = additionMultiplier - immunity * 0.01;
		this.attack = attackMultiplier * addition;
		this.hp = hpMultiplier * addition;
		this.score = score;
		return this;
	}

	public static void setImmunity(double immunityValue) {
		immunity = immunityValue;
	}
	public double getScore() {
		return score;
	}
	public double getHP() {
		return hp;
	}
	public double getAttack() {
		return attack;
	}
	public static Enemy enemyLevel_1 = new Enemy().createEnemy(1.1, 1, 1, 0.1);
	public static Enemy enemyLevel_2 = new Enemy().createEnemy(1.2, 3, 3, 0.2);
	public static Enemy enemyLevel_3 = new Enemy().createEnemy(1.3, 5, 6, 0.5);
	public static Enemy enemyLevel_4 = new Enemy().createEnemy(1.5, 10, 15, 1);
	public static Enemy enemyLevel_5 = new Enemy().createEnemy(1.7, 15, 20, 2);
	public static Enemy enemyLevel_6 = new Enemy().createEnemy(2.0, 30, 50, 10);

}
