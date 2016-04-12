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

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A collection of utility methods for collections and all types that implement {@link Collection}
 */
@SuppressWarnings ("WeakerAccess")
public class CollectionHelper
{
	/**
	 * Selects pseudo-random elements from a collection
	 *
	 * @param c      Collection to select from
	 * @param n      Number of elements to select
	 * @param unique Whether the selection should be unique
	 * @param <E>    Type of element in collection
	 * @return Random element(s) from collection
	 */
	public static <E> Collection<E> randomElement(Collection<E> c, int n, boolean unique)
	{
		List<E> out = new ArrayList<>();
		List<E> l = new ArrayList<>(c);

		while (out.size() < n)
		{
			E e = l.get(ThreadLocalRandom.current().nextInt(0, l.size()));
			out.add(e);
			if (unique) l.remove(e);
		}
		return out;
	}

	/**
	 * Sorts a collection using a comparator and returns it as a {@link LinkedList}
	 *
	 * @param c       Collection to be sorted
	 * @param k       Comparator to sort by
	 * @param reverse Whether to reverse the sort order
	 * @param <E>     Element type
	 * @return a {@link LinkedList} of the sorted elements
	 * @throws IllegalAccessException when unable to access the comparator class
	 * @throws InstantiationException when unable to instantiate to comparator class
	 */
	public static <E> LinkedList<E> sortByCompare(Collection<E> c, Class<? extends Comparator<E>> k, boolean reverse) throws IllegalAccessException, InstantiationException
	{
		Comparator<E> comp = k.newInstance();
		int moves = 0;
		boolean firstRun = true;
		LinkedList<E> l = new LinkedList<>(c);

		while (moves > 0 || firstRun)
		{
			firstRun = false;
			moves = 0;

			for (int i = 1; i < l.size(); i++)
			{
				E a = l.get(i - 1);
				E b = l.get(i);
				if (reverse ? comp.compare(a, b) < 0 : comp.compare(a, b) > 0)
				{
					l.set(i - 1, b);
					l.set(i, a);
					moves++;
				}
			}
		}
		return l;
	}

	/**
	 * Returns a slice of a collection, up to maxLen in size
	 *
	 * @param c      the collection to cut
	 * @param maxLen the length of the slice
	 * @param <E>    collection element type
	 * @return a slice of {@code c} {@code maxLen} in size, if {@code maxLen} is less than {@code c.size()}, otherwise, return {@code c}
	 */
	public static <E> Collection<E> cutMaxLen(Collection<E> c, int maxLen)
	{
		if (maxLen < c.size())
		{
			return ((List<E>) c).subList(0, maxLen);
		}
		return c;
	}
}
