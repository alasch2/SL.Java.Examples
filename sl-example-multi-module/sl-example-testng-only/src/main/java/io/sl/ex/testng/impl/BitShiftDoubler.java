package io.sl.ex.testng.impl;

import io.sl.ex.testng.iface.IntegerDoubler;

public class BitShiftDoubler implements IntegerDoubler {

  public long doDouble(int i) {
    i++;
    return 0;
  }

  public long doDouble2(int i) {
    return 0;
  }
}
