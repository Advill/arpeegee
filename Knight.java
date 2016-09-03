public class Knight extends NPC
{
	//attributes, faction only different from base NPC
	private String faction;
	
	public Knight(String n, int a, String f)
	{
		super(n, a);
		faction = f;
	}
	public Knight()
	{
		super("unnamed", 1);
		faction = "Debug";
	}
	//attack, may implement attacking a knight starting a fight
	public String attack()
	{
		return("Go home, citizen. You are drunk.");
	}
	//inspect
	public String inspect()
	{
		return("You see a Knight in not-so-shining armor. His name is " + super.getName() + " and he is " + super.getAge());
	}
	//talk
	public String talkTo()
	{
		int rn = (int)(Math.random() * 5) + 1;
		if(rn == 1)
		{
			return("Man, " + faction + " sure is great.");
		}
		else if(rn == 2)
		{
			return("And then I said to her; NO! Ha ha ha!");
		}
		else if(rn == 3)
		{
			return("Being a knight 'aint all its cracked up to be, let me tell ya.");
		}
		else if(rn == 4)
		{
			return("If you decide to go exploring, be ready to encounter monsters. Though I wouldnt reccomend it.");
		}
		else
		{
			return("I'm really hoping no one asks me to do anything.");
		}
	}
}