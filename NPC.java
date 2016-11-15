//Default Non-Combat NPC
public abstract class NPC {
	//////////////////////////////////////////////////////////////////////////////////////////////////
	// attributes
	//////////////////////////////////////////////////////////////////////////////////////////////////
	private String name;
	private int age;

	//////////////////////////////////////////////////////////////////////////////////////////////////
	// constructor, no default
	//////////////////////////////////////////////////////////////////////////////////////////////////
	public NPC(String n, int a) {
		name = n;
		age = a;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////
	// get name
	//////////////////////////////////////////////////////////////////////////////////////////////////
	public String getName() {
		return name;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////
	// equals
	//////////////////////////////////////////////////////////////////////////////////////////////////
	public boolean equals(String n) {
		return (name.equalsIgnoreCase(n));
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////
	// get age, height
	//////////////////////////////////////////////////////////////////////////////////////////////////
	public int getAge() {
		return age;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////
	// abstracts: attack, talkto, inspect
	//////////////////////////////////////////////////////////////////////////////////////////////////
	public abstract String inspect();

	public abstract String attack();

	public abstract String talkTo();
}
