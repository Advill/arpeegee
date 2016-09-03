public class CorruptedKnight extends Monster
{
	public CorruptedKnight()
	{
		super("Corrupted Knight", 125, 25, 20, 15);
	}
	public String inspect()
	{
		return "You look upon a powerful knight, black with corruption.\nHe has " + super.getHp() + "Health remaining out of his 10. He has 4 attack, 6 defense, and 3 speed.";
	}
	public String talkTo()
	{
		return "You attempt to communicate with the knight. He shows no sign of hearing you.";
	}
	public String taunt()
	{
		return "You think taunting the knight would be a bad idea and avoid it";
	}
}