package net.ofd.oflib.cli;

import java.util.Scanner;

public class Cli
{
	public static void print(Object o)
	{
		print(o, true);
	}

	public static void print(Object o, boolean newline)
	{
		if (!newline)
		{
			System.out.print(o);
		}
		else
		{
			System.out.println(o);
		}
	}

	public static class Prompt
	{
		public static String basic(String s)
		{
			Scanner scanner = new Scanner(System.in);
			print(s + ": ", false);
			return scanner.nextLine();
		}
	}
}
