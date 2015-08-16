/*
 * Copyright (c) 2015.
 * This file is part of OFlib.
 *
 * OFlib is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OFlib is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OFlib.  If not, see <http://www.gnu.org/licenses/>.
 */

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
