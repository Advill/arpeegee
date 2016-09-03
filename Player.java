import java.util.*;
public class Player extends Monster
{
	//////////////////////////////////////////////////////////////////////////////////////////////////
	//attributes
	//////////////////////////////////////////////////////////////////////////////////////////////////
	private ArrayList<Item> pack = new ArrayList<Item>(0); 
	private int exp;
	private int playerx;
	private int playery;
	//////////////////////////////////////////////////////////////////////////////////////////////////
	//default Constructor	hp/atk/def/spd
	//////////////////////////////////////////////////////////////////////////////////////////////////
	public Player(String n, int hp, int atk, int def, int spd)
	{
		super(n, hp, atk, def, spd);
		exp = 0;
		pack = new ArrayList<Item>(0);
		playerx = 0;
		playery = 0;
		exp = 0;
	}
	public String taunt()
	{
		return "";
	}
	public String talkTo()
	{
		return "";
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////
	//getters and setters
	//////////////////////////////////////////////////////////////////////////////////////////////////
	public ArrayList<Item> getItems()
	{
		return pack;
	}
	public int getXp()
	{
		return exp;
	}
	public int getX()
	{
		return playerx;
	}
	public int getY()
	{
		return playery;
	}
	public boolean levelup()
	{
		if(exp >= 100)
			return true;
		return false;
	}
	public void setXY(int x, int y)
	{
		playerx = x;
		playery = y;
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////
	//print items and add items
	//////////////////////////////////////////////////////////////////////////////////////////////////
	public void getPack()
	{
		for(int i = 0; i < pack.size(); i++)
		{
			System.out.println(pack.get(i).getItem());
		}
	}
	public void additem(int id)
	{
		pack.add(new Item(id));
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////
	//add xp
	//////////////////////////////////////////////////////////////////////////////////////////////////
	public void addXp(int xp)
	{
		exp = exp + xp;
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////
	//level up
	//////////////////////////////////////////////////////////////////////////////////////////////////
	public void levelAtk()
	{
		exp -= 100;
		super.atkInc(5);
	}
	public void levelDef()
	{
		exp -= 100;
		super.defInc(5);
	}
	public void levelSpd()
	{
		exp -= 100;
		super.defInc(5);
	}
	public void levelHp()
	{
		exp -= 100;
		super.healthInc(10);
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////
	//inspect self
	//////////////////////////////////////////////////////////////////////////////////////////////////
	public String inspect()
	{
		return("You are an explorer named " + super.getName() + ". You have " + super.getAtk() + " attack, " + super.getDef() + " defense, and " + super.getSpd() + " Speed. \n You have " + super.getHp() + " Health remaining out of " + super.getMHp());
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////
	//using an item, input an id to decide
	//////////////////////////////////////////////////////////////////////////////////////////////////
	public void useItem(int i)
	{
		int itemid = -1;
		for(int x = 0; x < pack.size(); x++)
		{
			if(pack.get(x).equals(i))
			{
				itemid = x;
				break;
			}
		}
		if(itemid == -1)
		{
			System.out.println("You dont have that item.");
		}
		else
		{
			if(i == 0)
			{
				System.out.println("You gain ten health!");
				super.healthInc(10);
			}
			else if(i == 1)
			{
				System.out.println("You gain five speed!");
				super.spdInc(5);
			}
			else if(i == 2)
			{
				System.out.println("You gaina five defense!");
				super.defInc(5);
			}
			else if(i == 3)
			{
				System.out.println("You gain five attack!");
				super.atkInc(5);
			}
			pack.remove(i);
		}
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////
	//testing if the player has an item
	//////////////////////////////////////////////////////////////////////////////////////////////////
	public boolean hasItem()
	{
		return(pack.size() >= 1);
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////
	//moving directions on the map
	//////////////////////////////////////////////////////////////////////////////////////////////////
	public String goNorth()//north
	{
		if(playerx > 0)
		{
			playerx --;
			return "You move north.";
		}
		else
			return "You cannot move any further north!";
	}
	public String goSouth()//south
	{
		if(playerx < 9)
		{
			playerx ++;
			return "you move south.";
		}
		else
			return "you cannot move any further south!";
	}
	public String goEast()//east
	{
		if(playery < 9)
		{
			playery++;
			return "you move east.";
		}
		else
			return "you cannot move any further east!";
	}
	public String goWest()//west
	{
		if(playery > 0)
		{
			playery --;
			return "You move west.";
		}
		else
			return "you cannot move any further west!";
	}
}