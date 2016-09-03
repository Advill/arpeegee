public class LoneWolf extends Monster
{
	public LoneWolf()
	{
		super("Lone Wolf", 75, 25, 10, 50);
	}
	public String inspect()
	{
		return ("A hungry-looking wolf circles you carfully. Teeth bared, it looks as though only one of you will be leaving this encounter." +
		"\n The wolf has " + super.getHp() + " health remaining of it's 7. The wolf has " + super.getAtk() + " attack, " + super.getDef() + " def, and " + super.getSpd() + " speed.");
	}
	public String talkTo()
	{
		return "You cannot communicate with the wolf. I'm not sure what you expected of this option.";
	}
	public String taunt()
	{
		return "You tell the dog he is a good boy, but the dog knows the truth.";
	}
}