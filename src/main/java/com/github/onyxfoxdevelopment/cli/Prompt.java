/*
 * Copyright (c) 2016.
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

package com.github.onyxfoxdevelopment.cli;

import java.io.Console;
import java.util.LinkedList;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Generic methods for prompting for user input
 */
@SuppressWarnings ({"WeakerAccess", "unused"})
public class Prompt
{
	/**
	 * System console (null when used in an IDE)
	 */
	private static final ConsoleWrapper console = new ConsoleWrapper();

	/**
	 * Prompts the user for an input, and returns {@code def} if no input was entered
	 *
	 * @param title Text to prompt the user with
	 * @param valid Pattern matching expected input
	 * @param def   Default value
	 * @return User input
	 */
	public static String prompt(String title, Pattern valid, String def)
	{
		String s = prompt(title, valid);
		if (s.trim().isEmpty())
		{
			return def;
		}
		return s;
	}

	/**
	 * Prompts the user for an input, and if it matches {@code valid}, returns it
	 *
	 * @param title Text to prompt user with
	 * @param valid Pattern matching expected input
	 * @return User input
	 */
	public static String prompt(String title, Pattern valid)
	{
		String l;
		console.printf(title);
		while (!valid.matcher(l = console.readLine()).matches())
		{
			console.printf("Invalid Value!!%n");
			console.printf(title);
		}
		return l;
	}

	/**
	 * Prompts the user for an integer value
	 *
	 * @param title Prompt text
	 * @param def   Default value
	 * @return User input
	 */
	public static int promptInt(String title, int def)
	{
		String l = prompt(title, Pattern.compile(" ?[0-9]*"));
		return l.isEmpty() || l.equals(" ") ? def : Integer.parseInt(l);
	}

	/**
	 * Prompts the user with a question, expecting yes or no as an input
	 *
	 * @param title Prompt text
	 * @return true on yes or blank input, false otherwise
	 */
	public static boolean promptYesNo(String title)
	{
		Pattern affirm = Pattern.compile("[Yy ]?");
		String l = prompt(title, Pattern.compile("[YyNn ]?"));
		return affirm.matcher(l).matches();
	}

	/**
	 * Prompts the user with a list of options
	 *
	 * @param title Prompt text
	 * @param opts  Option map in format {@code option name, option value}
	 * @return option value selected by user
	 */
	public static String promptList(String title, Map<String, String> opts)
	{
		LinkedList<String> l = new LinkedList<>();
		console.printf("%s%n", title);
		for (Map.Entry<String, String> entry : opts.entrySet())
		{
			l.add(entry.getValue());
			console.printf("%d) %s%n", l.size(), entry.getKey());
		}
		int i = promptInt("Option?: ");
		while (i > l.size())
		{
			console.printf("Invalid Option");
			i = promptInt("Option?: ");
		}
		return l.get(i - 1);
	}

	/**
	 * Returns an integer input from the user
	 *
	 * @param title Prompt text
	 * @return User input
	 */
	public static int promptInt(String title)
	{
		return Integer.parseInt(prompt(title, Pattern.compile("[0-9]+")));
	}

	/**
	 * Prompts the user for a password, and if available, uses the {@link Console#readPassword()} method, to hide user input
	 *
	 * @param title Prompt text
	 * @param valid Pattern restricting password input
	 * @return password entered by user
	 */
	public static String password(String title, Pattern valid)
	{
		console.printf(title);
		String p;
		while (!valid.matcher(p = console.readPassword()).matches())
		{
			console.printf("Invalid Value!!%n");
			console.printf(title);
		}
		return p;
	}
}
