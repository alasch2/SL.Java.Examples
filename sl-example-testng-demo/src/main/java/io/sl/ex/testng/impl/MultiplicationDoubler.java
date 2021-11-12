package io.sl.ex.testng.impl;

import io.sl.ex.testng.iface.IntegerDoubler;

public class MultiplicationDoubler implements IntegerDoubler {

  public long doDouble(int i) {
    return i * 2;
  }
}
