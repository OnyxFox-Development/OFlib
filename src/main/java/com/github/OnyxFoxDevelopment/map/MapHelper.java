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

package com.github.OnyxFoxDevelopment.map;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class MapHelper
{
	public static LinkedHashMap<String, Integer> sortByValues(HashMap<String, Integer> map)
	{
		LinkedHashMap<String, Integer> sorted = new LinkedHashMap<>(map);

		while (map.size() > 0)
		{
			String maxKey = (String) map.keySet().toArray()[0];

			for (String key : map.keySet())
			{
				if (map.get(key) > map.get(maxKey))
				{
					maxKey = key;
				}
			}

			sorted.put(maxKey, map.get(maxKey));
			map.remove(maxKey);
		}

		return sorted;
	}
}
