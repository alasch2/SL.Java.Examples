package io.sl.ex.junittestng.impl;

import io.sl.ex.junittestng.iface.IntegerDoubler;

public class MultiplicationDoubler implements IntegerDoubler {

  public long doDouble(int i) {
    return i * 2;
  }
}
