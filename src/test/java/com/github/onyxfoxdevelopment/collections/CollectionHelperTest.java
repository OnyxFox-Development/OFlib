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

package com.github.onyxfoxdevelopment.collections;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class CollectionHelperTest
{
	@Test
	public void cutMaxLen() throws Exception
	{
		List<Integer> list = Arrays.asList(1, 1, 1, 1, 15, 15, 31, 584, 2121, 53, 1, 51);

		// Check cutting the list down to size
		for (int i = 1; i < list.size(); i++)
		{
			List<Integer> cutList = (List<Integer>) CollectionHelper.cutMaxLen(list, i);
			System.out.println(cutList.size() + "=" + i);
			assert cutList.size() == i;
		}

		// Check when the cut is longer than the list
		for (int i = list.size(); i < list.size() * 2; i++)
		{
			List<Integer> cutList = (List<Integer>) CollectionHelper.cutMaxLen(list, i);
			assert cutList.equals(list);
		}

	}

	@Test
	public void sortByCompare() throws Exception
	{
		LinkedList<Integer> unsorted = new LinkedList<>(Arrays.asList(1, 2, 7, 1, 2, 11, 12, 65, 2, 4, 6, 34, 9));
		LinkedList<Integer> expected = new LinkedList<>(Arrays.asList(1, 1, 2, 2, 2, 4, 6, 7, 9, 11, 12, 34, 65));
		List<Integer> expRever = Arrays.asList(65, 34, 12, 11, 9, 7, 6, 4, 2, 2, 2, 1, 1);

		LinkedList<Integer> sorted = CollectionHelper.sortByCompare(unsorted, IntComp.class, false);
		LinkedList<Integer> sorRev = CollectionHelper.sortByCompare(unsorted, IntComp.class, true);

		assert sorted.equals(expected);
		assert sorRev.equals(expRever);
	}

	@Test
	public void randomElement() throws Exception
	{
		List<Integer> testCollection = Arrays.asList(0, 1, 1, 2, 4, 4, 185, 1651, 2, 8, 2, 1651, 21, 531, 51, 231, 53, 15, 15, 1);

		List<Integer> randomElements = (List<Integer>) CollectionHelper.randomElement(testCollection, 10, true);
		assert randomElements.size() == 10;
	}

	public static class IntComp implements Comparator<Integer>
	{
		@Override
		public int compare(Integer integer, Integer t1)
		{
			return integer.compareTo(t1);
		}
	}
	
}