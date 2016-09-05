import java.util.*;
public class Map
{
	private Area[][] map;
	//////////////////////////////////////////////////////////////////////////////////////////////////
	//default constructor
	//////////////////////////////////////////////////////////////////////////////////////////////////
	public Map()
	{
		map = new Area[100][100];
		for(int x = 0; x < 100; x ++)
		{
			for(int y = 0; y < 100; y++)
				map[x][y] = new Forest();
		}
		ArrayList<Integer> locationsx = new ArrayList<Integer>(0);
		ArrayList<Integer> locationsy = new ArrayList<Integer>(0);
		for(int i = 0; i<100; i++)
		{
			locationsx.add(i);
			locationsy.add(i);
		}
		for(int i = 0; i<= 46; i++)
		{
			int x = randomgenerator(1,locationsx.size()-1);
			int y = randomgenerator(1,locationsy.size()-1);
			System.out.println("#" + i + ". " + locationsx.size() + " " + locationsy.size() + " " + x + " " + y);
			map[locationsx.remove(x)][locationsy.remove(y)] = new Town();
			//ensures x is not 10 larger than y, and that y is not 10 larger than x
			if(locationsx.size() - 10 >= locationsy.size() || randomgenerator(0,1) == 1 && !(locationsy.size() - 10 >= locationsx.size()))
			{
				System.out.println("removing x");
				locationsx.remove(x-1);
				locationsx.remove(x-1);
			}
			else
			{
				System.out.println("removing y");
				locationsy.remove(y-1);
				locationsy.remove(y-1);
			}
		}
	}
	private int randomgenerator(int min, int max)
	{
		return((int)(Math.random()*(max - min)) + min);
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////
	//prints out the map
	//////////////////////////////////////////////////////////////////////////////////////////////////
	public void printMap(Player P)
	{
		//if it falls within the bounds such that i dont have to calculate where the player is.
		if(P.getX() >= 10 && P.getX() <= 90 && P.getY() >= 10 && P.getY() <= 90)
		{
			for(int y = P.getY() - 10; y < P.getY() + 11; y++)
			{
				for(int x = P.getX() - 10; x < P.getX() + 11; x++)
				{
					if(P.getX() == x && P.getY() == y)
						System.out.print("@ ");
					else 
						System.out.print(map[x][y] + " ");
				}
			System.out.println();
			}
		}
		//otherwise
		else
		{
			int minX, maxX, minY, maxY;
			//xes
			if(P.getX() < 10)
			{
				minX = 0;
				maxX = 11 + P.getX();
			}
			else if(P.getX() > 90)
			{
				minX = 91 - (P.getX() - 90);
				maxX = 100;
			}
			else
			{
				minX = P.getX() - 10;
				maxX = P.getX() + 11;
			}
			//ys
			if(P.getY() < 10)
			{
				minY = 0;
				maxY = 11 + P.getY();
			}
			else if(P.getY() > 90)
			{
				minY = 91 - (P.getY() - 90);
				maxY = 100;
			}
			else
			{
				minY = P.getY() - 10;
				maxY = P.getY() + 11;
			}
			//loop to print
			for(int x = minX; x < maxX; x++)
			{
				for(int y = minY; y < maxY; y++)
				{
					if(P.getX() == x && P.getY() == y)
						System.out.print("@ ");
					else 
						System.out.print(map[x][y] + " ");
				}
			System.out.println();
			}
		}
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////
	//returns the NPC array of a location
	//////////////////////////////////////////////////////////////////////////////////////////////////
	public NPC[] getNPCs(int x, int y)
	{
		return (map[x][y].getNPCArray());
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////
	//tests if a location exists, 
	//////////////////////////////////////////////////////////////////////////////////////////////////
	public boolean northExists(Player P)
	{
		int n = P.getY() - 1;
		if(n >= 0 && n <= 99 && map[P.getX()][n] != null)
			return true;
		return false;
	}
	public boolean southExists(Player P)
	{
		int n = P.getY() + 1;
		if(n >= 0 && n <= 99 && map[P.getX()][n] != null)
			return true;
		return false;
	}
	public boolean eastExists(Player P)
	{
		int n = P.getX() + 1;
		if(n >=0 && n <= 99 && map[n][P.getY()] != null)
			return true;
		return false;
	}
	public boolean westExists(Player P)
	{
		int n = P.getX() - 1;
		if(n >=0 && n <= 99 && map[n][P.getY()] != null)
			return true;
		return false;
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////
	//shows the directions a player can move.
	//////////////////////////////////////////////////////////////////////////////////////////////////
	public String directions(Player P)
	{
		String str = "Directions: ";
		if(northExists(P))
			str += "North ";
		if(southExists(P))
			str += "South ";
		if(eastExists(P))
			str += "East ";
		if(westExists(P))
			str += "West ";
		
		return(str);
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////
	//decides what characters the player can interact with
	//////////////////////////////////////////////////////////////////////////////////////////////////
	public String getInfo(int x, int y)
	{
		NPC[] a = map[x][y].getNPCArray();
		String str = "";
		for(int i = 0; i < a.length ; i ++)
		{
			if(a[i] != null)
			{
				str += a[i].getName() + " ";
			}
		}
		return str;
	}
	public int getLength()
	{
		return(map.length);
	}
	public int getWidth()
	{
		return(map[0].length);
	}
}
