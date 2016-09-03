public class Town extends Area
{
	
	public Town()
	{
		super("town",10);
		townconstructor();
	}
	public void townconstructor()
	{
		super.setNPC(0, "knight", "Ulfric", 24);
		super.setNPC(1, "knight", "Geralt", 30);
	}
}