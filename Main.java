import java.util.*;

public class Main {
	// main
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Player player;
		String input;

		// Character creator
		System.out.println("Hello! Welcome to my game!\nThis is the character Creator. You have 50 points to spend\n"
				+ "on any skills you want.");
		player = creator(in);

		Map M = new Map();
		System.out.println("For help, type Help. Type 0 to exit");

		// actual game loop
		while (true) {
			System.out.println(M.directions(player));
			System.out.println(M.getInfo(player.getX(), player.getY()));

			// player heals for 5 health every 'hour'
			player.heal(5);

			// if player has enough xp to level up
			if (player.levelup())
				System.out.println("You have " + (player.getXp() / 100) + " level ups available!");
			System.out.println();
			input = in.nextLine();
			if (input.equals("0"))
				break;
			else if (input.equalsIgnoreCase("help"))
				help();
			else if (input.equalsIgnoreCase("area"))
				area();
			else if (input.equalsIgnoreCase("explore"))
				explore(player);
			else if (input.equalsIgnoreCase("Map") || input.equalsIgnoreCase("look map"))
				M.printMap(player);
			else if (input.equalsIgnoreCase("Level up") || input.equalsIgnoreCase("Level"))
				levelup(player);
			else
				Action(input, M, player);

			System.out.println("------------");
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////
	// action state, allows for interaction by splitting verb and subject
	// and putting verb through dictionary class to find out what it is.
	//////////////////////////////////////////////////////////////////////////////////////////////////
	public static void Action(String input, Map M, Player P) {
		char v;
		String s;
		Dictionary d = new Dictionary();
		NPC[] Vil = M.getNPCs(P.getX(), P.getY());
		//////////////////////////////
		// if there is a space between two words. splits the verb and subject.
		//////////////////////////////
		if (input.trim().indexOf(" ") != -1) {
			v = getVerb(input);
			s = getSubject(input);
			// talk action//
			if (v == 's') {
				for (int i = 0; i < Vil.length; i++) {
					if (Vil[i] != null) {
						if (Vil[i].equals(s)) {
							System.out.println(Vil[i].talkTo());
							break;
						}
					} else if (i == Vil.length - 1) {
						System.out.println("I dont know who you want to do that to.");
						break;
					}
				}
			}
			// look action//
			else if (v == 'i') {
				if (s.equalsIgnoreCase("area") || s.equalsIgnoreCase("around") || s.equalsIgnoreCase("surroundings")) {
					area();
				} else if (s.equalsIgnoreCase("self") || s.equalsIgnoreCase("me")) {
					P.inspect();
				} else {

					for (int i = 0; i < Vil.length; i++) {
						if (Vil[i] != null) {
							if (Vil[i].equals(s)) {
								System.out.println(Vil[i].inspect());
								break;
							}
						} else if (i == Vil.length - 1) {
							System.out.println("I dont know who you want to do that to.");
							break;
						}
					}
				}
			}
			// attack action//
			else if (v == 'k') {
				for (int i = 0; i < Vil.length; i++) {
					if (Vil[i] != null) {
						if (Vil[i].equals(s)) {
							System.out.println(Vil[i].attack());
							break;
						}
					} else if (i == Vil.length - 1) {
						System.out.println("I dont know who you want to do that to.");
					}
				}
			}
			// move action//
			else if (v == 'g') {
				int rn = (int) (Math.random() * 9 + 1);
				if (rn == 1)
					battle(P);
				else {
					movement(input, P, M);
				}
			}
		}
		//////////////////////////////
		// if only one word is detected
		//////////////////////////////
		else {
			// move action//
			if (d.getDirection(input) != 'x') {
				int rn = (int) (Math.random() * 9 + 1);
				if (rn == 1)
					battle(P);
				else {
					movement(input, P, M);
				}
			}
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////
	// get verb, goes from beginning to first space
	//////////////////////////////////////////////////////////////////////////////////////////////////
	public static char getVerb(String in) {
		Dictionary d = new Dictionary();
		int spc = in.indexOf(' ');
		String verb = in.substring(0, spc);
		return d.verb(verb);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////
	// get subject, goes from first space to end.
	//////////////////////////////////////////////////////////////////////////////////////////////////
	public static String getSubject(String in) {
		int spc = in.indexOf(' ');
		String Subject = in.substring(spc + 1, in.length());
		return Subject;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////
	// prints help
	//////////////////////////////////////////////////////////////////////////////////////////////////
	public static void help() {
		System.out.println(
				"You can go north, south, east, or west depending on where you are. Available directions should be printed above where you type.\n"
						+ "If there are any NPCs in your area, they should be printed below the directions. You can talk, inspect, taunt, or attack NPCs.\n"
						+ "You can explore for items or enemies, and while moving on the map you should find some enemies. Print out the map by saying 'map'."
						+ "\nIf you have a levelup available, type 'l' or \"level up\".");
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////
	// prints area//////////will remove this later
	//////////////////////////////////////////////////////////////////////////////////////////////////
	public static void area() {
		System.out.println("You look at a barren wasteland of unflavored text.");
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////
	// chance of finding monster or item
	//////////////////////////////////////////////////////////////////////////////////////////////////
	public static void explore(Player p) {
		int rn = (int) (Math.random() * 9) + 1;

		if (rn <= 4) {
			System.out.println("you found an Item!");
		} else {
			System.out.println("A monster appears out of the shadows!");
			battle(p);
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////
	// battle method, another loop
	//////////////////////////////////////////////////////////////////////////////////////////////////
	public static void battle(Player P) {
		Scanner in = new Scanner(System.in);
		Monster S;

		// generating the random monster////////
		int rn = (int) (Math.random() * 4);
		if (rn == 0)
			S = new Skeleton();
		else if (rn == 1)
			S = new MonsterousPlant();
		else if (rn == 2)
			S = new LoneWolf();
		else
			S = new CorruptedKnight();

		Dictionary d = new Dictionary();
		// repeats until someone has no health////////
		do {
			// intro text//////
			System.out.println("You are fighting a " + S.getName());
			System.out.println("You have " + P.getHp() + "/" + P.getMHp());
			System.out.println("The " + S.getName() + " Has " + S.getHp() + " HP remaining.");
			System.out.println("What do you want to do?");
			System.out.println();
			String a = in.nextLine();
			// booleans for player action and attacks.///
			boolean playeraction = false;
			boolean playerattack = false;
			boolean playerblock = false;
			if (d.verb(a) == 'r')
				break;
			if (d.verb(a) == 'a') // attack action//
			{
				playeraction = true;
				playerattack = true;
			} else if (d.verb(a) == 'd') // block action//
			{
				System.out.println("You block the " + S.getName() + "'s attack!");
				playerblock = true;
			} else if (d.verb(a) == 's') // talk action//
			{
				System.out.println(S.talkTo());
				playeraction = true;
			} else if (d.verb(a) == 'i') // inspect action//
			{
				System.out.println(S.inspect());
			} else if (d.verb(a) == 't') // taunt action//
			{
				System.out.println(S.taunt());
				playeraction = true;
			} else if (d.verb(a) == 'u') // use action//
			{
				useItem(P);
				playeraction = true;
			} else {
				System.out.println("I didnt understand that, please only type one word while in a battle.");
			}
			////////////////////////////////////
			// decides if the player did an action that the monster can attack
			//////////////////////////////////// after
			////////////////////////////////////
			if (P.getFullSpd() >= S.getFullSpd())// if player speed greater
			{
				if (playeraction) {
					// decides if the player attacked///
					if (playerattack)
						System.out.println("You do " + S.damage(P.attack()) + " damage!");
					System.out.println(" The " + S.getName() + " does " + P.damage(S.attack()) + " damage!");
				}
			} else// if monster speed greater
			{
				if (playeraction) {
					System.out.println(" The " + S.getName() + " does " + P.damage(S.attack()) + " damage!");
					if (playerattack)
						System.out.println("You do " + S.damage(P.attack()) + " damage!");
				}
			}
			System.out.println("Your hp = " + P.getHp());
			System.out.println(S.getName() + " hp = " + S.getHp());
			System.out.println();
		} while (P.getHp() > 0 && S.getHp() > 0);

		if (P.getHp() > 0 && S.getHp() <= 0)// player won/////
		{
			System.out.println("You won!");
			int xp = (int) (Math.random() * 25) + 5;// generates random amount
													// of experience between 5
													// and 25
			System.out.println("You got " + xp + " Experience!");
			P.addXp(xp);
		} else if (P.getHp() > 0 && S.getHp() > 0)// player likely ran
			System.out.println("You escaped!");
		else // player likely died
			System.out.println("You died.");
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////
	// Using items
	//////////////////////////////////////////////////////////////////////////////////////////////////
	public static void useItem(Player P) {
		Scanner in = new Scanner(System.in);
		Dictionary D = new Dictionary();
		///////////////////////////////////////////////////////////////////////////////////////////////
		// infinite loop until an output is reached. Possibility to break later
		///////////////////////////////////////////////////////////////////////////////////////////////
		do {
			System.out.println("What item would you like to use? Press 0 to exit.");
			P.getPack();
			String next = in.nextLine();
			if (next.equals("0")) // 0 exit code
				break;
			if (P.hasItem()) // tests if the player has any items at all, should
								// probobly move this up.
			{

				if (D.getPot(next) == 'h') {
					P.useItem(0);
					break;
				} else if (D.getPot(next) == 's') {
					P.useItem(1);
					break;
				} else if (D.getPot(next) == 'd') {
					P.useItem(2);
					break;
				} else if (D.getPot(next) == 'a') {
					P.useItem(3);
					break;
				} else
					System.out.println("I didn't understand that.");
			} else {
				System.out.println("You have no items to use!");
				break;
			}
		} while (true);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////
	// move commands
	//////////////////////////////////////////////////////////////////////////////////////////////////
	public static void movement(String input, Player P, Map M) {
		Dictionary D = new Dictionary();
		char direction = D.getDirection(input);
		System.out.println(direction);
		if (direction == 'n')
			System.out.println(P.goNorth(M));
		else if (direction == 's')
			System.out.println(P.goSouth(M));
		else if (direction == 'e')
			System.out.println(P.goEast(M));
		else if (direction == 'w')
			System.out.println(P.goWest(M));

		M.printMap(P);
	}

	public static void levelup(Player P)// level ups//
										// WIP///////////////////////////////////////////////////
	{
		if (P.levelup()) {
			Scanner in = new Scanner(System.in);
			Dictionary D = new Dictionary();
			System.out.println("What attribute would you like to level up?");
			String input = in.nextLine();
			if (D.levelup(input) == 'a')
				P.levelAtk();
			else if (D.levelup(input) == 'd')
				P.levelDef();
			else if (D.levelup(input) == 's')
				P.levelSpd();
			else if (D.levelup(input) == 'h')
				P.levelHp();
		} else
			System.out.println("You do not have enough XP to level up!");
	}

	public static Player creator(Scanner in) {
		int pts = 30;
		int hp;
		int atk;
		int def;
		int spd;
		// name
		System.out.println("First off, What is your name?");
		String name = in.nextLine();
		// health
		System.out.println(
				"You start with 100 Health, how much would you like to add? \nYou have " + pts + " points left.");
		while (true) {
			hp = in.nextInt();
			if (hp <= pts) {
				pts -= hp;
				break;
			} else
				System.out.println("You do not have enough points for that.");
		}
		// attack
		System.out.println(
				"You start with 25 attack, how much would you like to add? \nYou have " + pts + " points left.");
		while (true) {
			atk = in.nextInt();
			if (atk <= pts) {
				pts -= atk;
				break;
			} else
				System.out.println("You do not have enough points for that.");
		}
		// defense
		System.out.println(
				"You start with 25 defense, how much would you like to add? \nYou have " + pts + " points left.");
		while (true) {
			def = in.nextInt();
			if (def <= pts) {
				pts -= def;
				break;
			} else
				System.out.println("You do not have enough points for that.");
		}
		// speed
		System.out.println(
				"You start with 25 Speed, how much would you like to add? \nYou have " + pts + " points left.");
		while (true) {
			spd = in.nextInt();
			if (spd <= pts) {
				pts -= spd;
				break;
			} else
				System.out.println("You do not have enough points for that");
		}
		System.out.println("So your name is " + name + ", You have " + (hp + 100) + " Health, " + (atk + 25)
				+ " attack, " + (def + 25) + " defense, and " + (spd + 25) + " speed.");
		return new Player(name, hp + 100, atk + 25, def + 25, spd + 25);
	}
}