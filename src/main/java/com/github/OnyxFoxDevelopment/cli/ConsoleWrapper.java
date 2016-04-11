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

package com.github.OnyxFoxDevelopment.cli;

import java.io.Console;
import java.util.Scanner;

/**
 * A wrapper around the {@link Console} that substitutes calls to {@link System#out} when {@link System#console()} is null
 *
 * @author linuxdemon
 * @since 0.1.9
 */
@SuppressWarnings ("WeakerAccess")
public class ConsoleWrapper
{
	private final Console console = System.console();

	/**
	 * Formats {@code format} using the values in {@code args}
	 *
	 * @param format String defining format
	 * @param args   Substitution values
	 */
	public void printf(String format, Object... args)
	{
		if (console == null)
		{
			System.out.printf(format, (java.lang.Object[]) args);
		}
		else
		{
			console.printf(format, (java.lang.Object[]) args);
		}
	}

	/**
	 * Reads a line from the terminal, and uses {@link Console#readPassword()} to hide user input, if available
	 *
	 * @return typed password
	 */
	public String readPassword()
	{
		return console == null ? readLine() : new String(console.readPassword());
	}

	/**
	 * Reads a single line from the system input and returns it
	 *
	 * @return input line
	 */
	public String readLine()
	{
		if (console != null)
		{
			return console.readLine();
		}
		Scanner s = new Scanner(System.in);
		return s.nextLine();
	}
}
