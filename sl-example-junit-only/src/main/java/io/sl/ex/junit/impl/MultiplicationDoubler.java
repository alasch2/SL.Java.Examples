package io.sl.ex.junit.impl;

import io.sl.ex.junit.iface.IntegerDoubler;

public class MultiplicationDoubler implements IntegerDoubler {

  public long doDouble(int i) {
    return i * 2;
  }
}
