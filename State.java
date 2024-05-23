/**
 *	The object to store US state information.
 *
 *	@author	Charles Chang
 *	@since	23 May, 2024
 */
public class State implements Comparable<State>
{
	private String name;
	private String abbreviation;
	private int population;
	private int area;
	private int reps;
	private String capital;
	private int month;
	private int day;
	private int year;
	
	public State(String n, String a, int p, int ar, int r, String c, int m, int d, int y) 
	{
		name = n;
		abbreviation = a;
		population = p;
		area = ar;
		reps = r;
		capital = c;
		month = m;
		day = d;
		year = y;
	}
	
	public int compareTo(State other) 
	{
		return this.getName().compareTo(other.getName());
	}
	
	public String getName ( )
	{
		return name;
	}
	
	public String toString() 
	{
		//	Name
		String out = name;
		int to16 = 16 - out.length();
		for (int i = 0; i < to16; i++)
			out += " ";
		//	Abbr.
		out += abbreviation;
		out += "\t";
		//	Pop.
		String pop = Integer.toString(population);
		out += pop;
		int to9 = 9 - pop.length();
		for (int i = 0; i < to9; i++)
			out += " ";
		out += "\t";
		//	Area
		String ar = Integer.toString(area);
		out += ar;
		int to8 = 8 - ar.length();
		for (int i = 0; i < to8; i++)
			out += " ";
		out += "\t";
		//	Reps
		String r = Integer.toString(reps);
		out += r;
		out += "\t";
		//	Capital
		out += capital;
		int to15 = 15 - capital.length();
		for (int i = 0; i < to15; i++)
			out += " ";
		out += "\t\t";
		//	Month
		String m = Integer.toString(month);
		out += m;
		if (m.length() == 1)
			out += " ";
		out += " ";
		//	Day
		String d = Integer.toString(day);
		out += d;
		if (d.length() == 1)
			out += " ";
		out += " ";
		//	Year
		out += Integer.toString(year);
		return out;
	}
}
