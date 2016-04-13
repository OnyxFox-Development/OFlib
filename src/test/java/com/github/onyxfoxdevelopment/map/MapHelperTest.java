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

import org.junit.Test;

import java.util.LinkedHashMap;

@SuppressWarnings ({"RedundantThrows", "unused"})
public class MapHelperTest
{
	@Test
	public void sortByValues() throws Exception
	{
		LinkedHashMap<String, Integer> expected = new LinkedHashMap<>();
		expected.put("a", 1);
		expected.put("b", 2);
		expected.put("c", 3);
		expected.put("d", 4);
		expected.put("e", 5);

		LinkedHashMap<String, Integer> expectedReversed = new LinkedHashMap<>();
		expectedReversed.put("e", 5);
		expectedReversed.put("d", 4);
		expectedReversed.put("c", 3);
		expectedReversed.put("b", 2);
		expectedReversed.put("a", 1);

		LinkedHashMap<String, Integer> unsorted = new LinkedHashMap<>();
		unsorted.put("e", 5);
		unsorted.put("a", 1);
		unsorted.put("c", 3);
		unsorted.put("b", 2);
		unsorted.put("d", 4);

		LinkedHashMap sorted = MapHelper.sortByValues(unsorted, false);
		assert sorted.get("a") instanceof Integer;
		assert sorted.equals(expected);

		LinkedHashMap sortedReverse = MapHelper.sortByValues(unsorted, true);
		assert sortedReverse.get("a") instanceof Integer;
		assert sortedReverse.equals(expectedReversed);
	}
	
}