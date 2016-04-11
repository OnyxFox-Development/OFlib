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

package com.github.OnyxFoxDevelopment.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
}
