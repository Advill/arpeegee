//////////////////////////////////////////////////////////////////////////////////////////////////
/*This is going to be used to decide what the user is saying outside of the main code
/* This is so that I have a more modular place to put new words or add new actions 
/* without having to deal with tons of if statements in the main method					 */
//////////////////////////////////////////////////////////////////////////////////////////////////
public class Dictionary {
	// attributes
	private String[] attack;
	private String[] inspect;
	private String[] taunt;
	private String[] defend;
	private String[] explore;
	private String[] talk;
	private String[] use;
	private String[] run;
	private String[] healthpot;
	private String[] speedpot;
	private String[] defensepot;
	private String[] attackpot;
	private String[] go;
	private String[] north;
	private String[] south;
	private String[] east;
	private String[] west;

	public Dictionary() {
		// Attack
		attack = new String[5];
		attack[0] = "attack";
		attack[1] = "stab";
		attack[2] = "fight";
		attack[3] = "battle";
		attack[4] = "challenge";

		// inspect
		inspect = new String[4];
		inspect[0] = "inspect";
		inspect[1] = "look";
		inspect[2] = "check";
		inspect[3] = "search";

		// taunt
		taunt = new String[2];
		taunt[0] = "taunt";
		taunt[1] = "annoy";

		// defend
		defend = new String[4];
		defend[0] = "defend";
		defend[1] = "block";
		defend[2] = "evade";
		defend[3] = "defense";

		// explore
		explore = new String[1];
		explore[0] = "explore";

		// talk
		talk = new String[2];
		talk[0] = "talk";
		talk[1] = "speak";

		// use
		use = new String[3];
		use[0] = "use";
		use[1] = "interact";
		use[2] = "drink";

		// run
		run = new String[3];
		run[0] = "run";
		run[1] = "flee";
		run[2] = "escape";

		//////////////////////////////////////////////////////////////////////////////////////////////////
		// Potion-Specific words
		//////////////////////////////////////////////////////////////////////////////////////////////////
		healthpot = new String[2];
		healthpot[0] = "health";
		healthpot[1] = "Health Potion";

		speedpot = new String[2];
		speedpot[0] = "speed";
		speedpot[1] = "speed Potion";

		defensepot = new String[2];
		defensepot[0] = "defense";
		defensepot[1] = "defense pot";

		attackpot = new String[2];
		attackpot[0] = "attack";
		attackpot[1] = "attackpotion";
		//////////////////////////////////////////////////////////////////////////////////////////////////
		// Directions
		//////////////////////////////////////////////////////////////////////////////////////////////////
		// Go
		go = new String[4];
		go[0] = "go";
		go[1] = "move";
		go[2] = "travel";
		go[3] = "walk";

		north = new String[4];
		north[0] = "North";
		north[1] = "up";
		north[2] = "N";
		north[3] = "U";

		south = new String[4];
		south[0] = "South";
		south[1] = "down";
		south[2] = "S";
		south[3] = "D";

		east = new String[4];
		east[0] = "East";
		east[1] = "right";
		east[2] = "E";
		east[3] = "R";

		west = new String[4];
		west[0] = "West";
		west[1] = "left";
		west[2] = "W";
		west[3] = "L";
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////
	// decides what the user is trying to say
	//////////////////////////////////////////////////////////////////////////////////////////////////
	public char verb(String V) {
		// attack = a
		for (int i = 0; i < attack.length; i++) {
			if (V.equalsIgnoreCase(attack[i]))
				return 'a';
		}
		// inspect = i
		for (int i = 0; i < inspect.length; i++) {
			if (V.equalsIgnoreCase(inspect[i]))
				return 'i';
		}
		// taunt = t
		for (int i = 0; i < taunt.length; i++) {
			if (V.equalsIgnoreCase(taunt[i]))
				return 't';
		}
		// defend = d
		for (int i = 0; i < defend.length; i++) {
			if (V.equalsIgnoreCase(defend[i]))
				return 'd';
		}
		// explore = e
		for (int i = 0; i < explore.length; i++) {
			if (V.equalsIgnoreCase(explore[i]))
				return 'e';
		}
		// talk = t
		for (int i = 0; i < talk.length; i++) {
			if (V.equalsIgnoreCase(talk[i]))
				return 's';
		}
		// use = u
		for (int i = 0; i < use.length; i++) {
			if (V.equalsIgnoreCase(use[i]))
				return 'u';
		}
		// go = g
		for (int i = 0; i < go.length; i++) {
			if (V.equalsIgnoreCase(go[i]))
				return 'g';
		}
		// run = r
		for (int i = 0; i < run.length; i++) {
			if (V.equalsIgnoreCase(run[i]))
				return 'r';
		}
		// else = x
		return 'x';
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////
	// tests for different items.
	//////////////////////////////////////////////////////////////////////////////////////////////////
	public char getPot(String P) {
		for (int i = 0; i < healthpot.length; i++) {
			if (P.equalsIgnoreCase(healthpot[i]))
				return 'h';// health potion
			if (P.equalsIgnoreCase(speedpot[i]))
				return 's';// speed potion
			if (P.equalsIgnoreCase(defensepot[i]))
				return 'd';// defense potion
			if (P.equalsIgnoreCase(attackpot[i]))
				return 'a';// attack potion
		}
		return 'x';
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////
	// tests for directions, used after the computer understands that the player
	////////////////////////////////////////////////////////////////////////////////////////////////// is
	////////////////////////////////////////////////////////////////////////////////////////////////// trying
	////////////////////////////////////////////////////////////////////////////////////////////////// to
	////////////////////////////////////////////////////////////////////////////////////////////////// move.
	//////////////////////////////////////////////////////////////////////////////////////////////////
	public char getDirection(String d) {
		if (d.indexOf(" ") == -1)// if no space
		{
			for (int i = 0; i < north.length; i++) {
				if (d.equalsIgnoreCase(north[i]))
					return 'n';
				else if (d.equalsIgnoreCase(south[i]))
					return 's';
				else if (d.equalsIgnoreCase(east[i]))
					return 'e';
				else if (d.equalsIgnoreCase(west[i]))
					return 'w';
			}
			return 'x';
		} else {
			int z = d.indexOf(" ");// if there is a space
			String str = d.substring(z + 1, d.length());// substring after the
														// space to the end.
			for (int i = 0; i < north.length; i++) {
				if (str.equalsIgnoreCase(north[i]))
					return 'n';
				else if (str.equalsIgnoreCase(south[i]))
					return 's';
				else if (str.equalsIgnoreCase(east[i]))
					return 'e';
				else if (str.equalsIgnoreCase(west[i]))
					return 'w';
			}
		}
		return 'x';
	}

	public char levelup(String in) {
		if (in.equalsIgnoreCase("Attack") || in.equalsIgnoreCase("A"))
			return 'a';
		else if (in.equalsIgnoreCase("Defense") || in.equalsIgnoreCase("D"))
			return 'd';
		else if (in.equalsIgnoreCase("Speed") || in.equalsIgnoreCase("S"))
			return 's';
		else if (in.equalsIgnoreCase("Health") || in.equalsIgnoreCase("Hp") || in.equalsIgnoreCase("H"))
			return 'h';
		return 'x';
	}
}
