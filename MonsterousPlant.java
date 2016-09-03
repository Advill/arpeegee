public class MonsterousPlant extends Monster
{
	public MonsterousPlant()
	{
		super("Monsterous Plant", 200, 10, 30, 0);
	}
	public String inspect()
	{
		return ("You see before you a large flower, at least twice the size of yourself."
		+ "\nIt has Spiked roots and limbs that creep slowly around you, searching for an opening."
		+ "\nIt has " + super.getHp() + " health remaining of it's " + super.getMHp() + ". it has " + super.getAtk() + " attack, " +super.getDef()+" defense, and "+ super.getSpd() + " speed.");
	}
	public String talkTo()
	{
		return "You attempt communication by throwing rocks at the plant. \nIt goes as well as you expect.";
	}
	public String taunt()
	{
		super.atkInc(5);
		return "You tell the plant it belongs in an anime. \nThe plant did not like that.";
	}
}