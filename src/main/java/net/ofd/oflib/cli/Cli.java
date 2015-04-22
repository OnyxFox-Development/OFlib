package net.ofd.oflib.cli;

public class Cli
{
	public static void print(Object o)
	{
		System.out.println(o);
	}

	public static void print(Object o, boolean newline)
	{
		if (!newline)
		{
			System.out.print(o);
		}
		else
		{
			print(o);
		}
	}
}
