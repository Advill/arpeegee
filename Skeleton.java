public class Skeleton extends Monster {
	public Skeleton() {
		super("Skeleton", 10, 15, 15, 15);
	}

	public String inspect() {
		return ("You see before you a spooky Skeleton.\n He has " + super.getHp()
				+ " health remaining of his 5. He has 3 attack, defense, and speed.");
	}

	public String talkTo() {
		return "The skeleton does a jig as you attempt to communicate. you are slightly scared";
	}

	public String taunt() {
		return "You shout that the skeleton did not drink enough milk. The skeleton is enraged";
	}
}