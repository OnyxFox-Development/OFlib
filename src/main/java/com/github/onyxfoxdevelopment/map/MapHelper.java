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

package com.github.onyxfoxdevelopment.map;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * A collection of utility methods for Maps and all types that implement {@link Map}
 */
@SuppressWarnings ({"WeakerAccess", "unused"})
public class MapHelper
{
	public static <K, V extends Comparable> LinkedHashMap<K, V> sortByValues(Map<K, V> map)
	{
		return sortByValues(map, false);
	}

	/**
	 * Sorts {@link Map} {@code map} by it's values
	 *
	 * @param map     Map to be sorted
	 * @param reverse Should order be reverse
	 * @return Sorted Map
	 */
	public static <K, V extends Comparable> LinkedHashMap<K, V> sortByValues(Map<K, V> map, boolean reverse)
	{
		int moves = 0;
		boolean firstRun = true;

		List<Map.Entry<K, V>> entrySet = new ArrayList<>(map.entrySet());
		while (moves > 0 || firstRun)
		{
			moves = 0;
			firstRun = false;

			for (int i = 1; i < entrySet.size(); i++)
			{
				Map.Entry<K, V> entryA = entrySet.get(i - 1);
				Map.Entry<K, V> entryB = entrySet.get(i);
				if (reverse ? entryA.getValue().compareTo(entryB.getValue()) < 0 : entryA.getValue().compareTo(entryB.getValue()) > 0)
				{
					entrySet.set(i - 1, entryB);
					entrySet.set(i, entryA);
					moves++;
				}
			}
		}
		LinkedHashMap<K, V> sorted = new LinkedHashMap<>();
		for (Map.Entry<K, V> entry : entrySet)
		{
			sorted.put(entry.getKey(), entry.getValue());
		}

		return sorted;
	}
}
