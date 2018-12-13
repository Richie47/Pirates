ackage winterProjects;

import java.util.Random;
import java.util.Scanner;

/**
 * s. For each integer variable, generate a random integer from 1 to 10. For the
 * two Boolean variables, generate a random decimal. In 25% of the cases, the
 * pirate should have a parrot, and in 10% of the cases the pirate should have a
 * peg leg.
 * 
 * @author Richie
 *
 */
public class Pirate {

	private String name;
	private int health, swordsmanship, agility, doubloons;
	private boolean hasPegLeg, hasParrot;

	Random r = new Random();

	private static int numberOfPirates;

	public Pirate(String name) {

		health = 10;
		doubloons = 25;

		this.name = name;

		swordsmanship = r.nextInt(10) + 1;

		agility = r.nextInt(10) + 1;

		double parrot = Math.random();
		if (parrot <= .25) {
			hasParrot = true;

		}

		else {
			hasParrot = false;

		}

		double peg = Math.random();

		if (peg <= .10) {
			hasPegLeg = true;

		}

		else {
			hasPegLeg = false;

		}

		numberOfPirates++;
	}

	public String swashbuckle(Pirate p) {

		String event = "";
		int defender = p.agility;
		int attacker = this.swordsmanship;
		if (p.hasPegLeg)
			defender = p.agility - 1;

		if (this.hasParrot)
			attacker = this.swordsmanship + 1;

		if (defender > r.nextInt(20)) {
			event = p.name + " dodged " + this.name + "'s attack!";
		}

		else {
			int damage = r.nextInt(attacker) + 1;

			event = this.name + " swabs the deck with " + p.name + " for " + damage + "!";

			if (p.health - damage <= 0) {
				p.health = 0;
				event += "\n" + this.name + " has killed " + p.name + "\n" + this.name + " has received $"
						+ p.doubloons;
				this.doubloons += p.doubloons;
				p.doubloons = 0;

			} else
				p.health -= damage;
		}

		return event;

	}

	public boolean isKilled() {
		if (health <= 0) {
			return true;
		}

		return false;
	}

	public String toString() {

		String pirateStats = name + " (H: " + health + ", S: " + swordsmanship + ", A: " + agility + ", $" + doubloons
				+ ")\n";
		if (this.hasParrot)
			pirateStats += "A parrot has spawned with this pirate. (+1 attack bonus)\n";
		if (this.hasPegLeg)
			pirateStats += "This pirate has a peg-leg! (-1 agility)\n";
		return pirateStats;
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		Random r = new Random();

		Pirate s1 = new Pirate("Manu");
		Pirate s2 = new Pirate("Asta");
		Pirate s3 = new Pirate("Jak");

		System.out
				.println("WHO WILL BE THE KING OF THE PIRATES?\nPress [ENTER] to begin!\nPress Q to quit at any time");

		String inp = sc.nextLine();

		// String exitInp = Character.toString(inp.charAt(0));

		// System.out.println("First Pirate's Name:");
		// String pOne = sc.nextLine();
		// System.out.println("Second Pirate's Name:");
		// String pTwo = sc.nextLine();
		// System.out.println("Third Pirate's Name:");
		// String pThree = sc.nextLine();

		Pirate p1 = new Pirate("Richie");
		Pirate p2 = new Pirate("Zoro");
		Pirate p3 = new Pirate("Luffy");

		Pirate[] heroes = { p1, p2, p3 };
		Pirate[] enemies = { s1, s2, s3 };

		System.out.println("Your Team:");
		System.out.println(p1.toString());
		System.out.println(p2.toString());
		System.out.println(p3.toString());

		System.out.println("Enemy Team:");
		System.out.println(s1.toString());
		System.out.println(s2.toString());
		System.out.println(s3.toString());

		while (inp != "q" || inp != "Q") {

			if (inp == "L") {
				System.out.println(s1.toString());
			}
			for (int i = 0; i < heroes.length; i++) {

				int redoCurrentPlayer = i;
				if (!heroes[i].isKilled()) {

					int att = sc.nextInt();

					if (att <= enemies.length) {
						if (enemies[att - 1].isKilled()) {
							System.out.println("He's already dead, pick another target.");
							i--;
						} else {
							System.out.println(heroes[i].swashbuckle(enemies[att - 1]));

							if (enemies[att - 1].isKilled()) {
								System.out.println(heroes[i].toString());
							}
						}
					}

				}
			}

			for (int j = 0; j < enemies.length; j++) {
				int enemyChoice = r.nextInt(heroes.length) + 1;

				if (!enemies[j].isKilled()) {

					if (!heroes[enemyChoice - 1].isKilled()) {
						System.out.println(enemies[j].swashbuckle(heroes[enemyChoice - 1]));

						if (heroes[enemyChoice - 1].isKilled())
							System.out.println(enemies[j].toString());
					}

					else {
						j--;
					}
				}

			}

		}

	}

}
