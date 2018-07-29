package io.sl.ex.junittestng.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.sl.ex.junittestng.iface.IntegerDoubler;

public class AdditionDoubler implements IntegerDoubler {
	static Logger LOG = LoggerFactory.getLogger(AdditionDoubler.class);

	public long doDouble(int i) {
		long result = i + i;
		LOG.info("duouble of {} = {}", i, result);
		return result;
	}
}

