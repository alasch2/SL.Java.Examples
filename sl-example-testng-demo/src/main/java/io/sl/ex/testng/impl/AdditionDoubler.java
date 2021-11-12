package io.sl.ex.testng.impl;

import io.sl.ex.testng.iface.IntegerDoubler;

public class AdditionDoubler implements IntegerDoubler {

	public long doDouble(int i) {
		long result = i + i;
		return result;
	}
}

