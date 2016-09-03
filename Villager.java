public class Villager extends NPC
{
	public Villager(String n, int a)
	{
		super(n, a);
	}
	public String inspect()
	{
		return(super.getName() + " Is a " + super.getAge() + " year old Citizen of this town. \n they likely roll in garbage when you are not around.");
	}
	public String attack()
	{
		return("YOU DARE CHALLENGE ME? " + super.getName() + " TO A BATTLE? FINE. \n you wake up in a gutter, covered in garbage. Smells like mum's cooking.");
	}
	public String talkTo()
	{
		int rn = (int)(Math.random() * 5) + 1;
		if(rn == 1)
		{
			return("I've been 'round these parts for a while. Monsters have started appearing more frequently, but I 'aint scared.");
		}
		else if(rn == 2)
		{
			return("What do you want? I 'aint sellin' nuthin'. Go bug someone else.");
		}
		else if(rn == 3)
		{
			return("Eh? Monsters you say? best let the knights handle 'em.");
		}
		else if(rn == 4)
		{
			return("I need another drink...");
		}
		else
		{
			return("I think I caught something from that wench... Crotch itches like hell.");
		}
	}
}