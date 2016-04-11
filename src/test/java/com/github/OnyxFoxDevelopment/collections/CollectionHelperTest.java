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

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class CollectionHelperTest
{
	@Test
	public void randomElement() throws Exception
	{
		List<Integer> testCollection = Arrays.asList(0, 1, 1, 2, 4, 4, 185, 1651, 2, 8, 2, 1651, 21, 531, 51, 231, 53, 15, 15, 1);

		List<Integer> randomElements = (List<Integer>) CollectionHelper.randomElement(testCollection, 10, true);
		assert randomElements.size() == 10;
	}
	
}