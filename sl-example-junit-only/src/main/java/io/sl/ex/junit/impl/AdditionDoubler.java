package io.sl.ex.junit.impl;

import io.sl.ex.junit.iface.IntegerDoubler;

public class AdditionDoubler implements IntegerDoubler {

	public long doDouble(int i) {
		return i + i;
	}
}

