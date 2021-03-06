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

package com.github.onyxfoxdevelopment.environment;

/**
 * A collection of methods for interfacing with the runtime environment
 */
@SuppressWarnings ({"WeakerAccess", "unused"})
public class Environment
{
	/**
	 * Checks if a class is loaded in the current JVM
	 *
	 * @param binaryName Full class name including package
	 * @return true if class is loaded, false otherwise
	 */
	public static boolean isClassLoaded(String binaryName)
	{
		try
		{
			Class.forName(binaryName, false, Environment.class.getClassLoader());
		}
		catch (ClassNotFoundException e)
		{
			return false;
		}
		return true;
	}
}
