package io.sl.ex.junit.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import io.sl.ex.junit.iface.IntegerDoubler;
import io.sl.ex.junit.impl.AdditionDoubler;
import io.sl.ex.junit.impl.BitShiftDoubler;
import io.sl.ex.junit.impl.MultiplicationDoubler;

@RunWith(PowerMockRunner.class)
public class TestDoubler {
  @Test
  public void additionDoubler() {
    IntegerDoubler doubler = new AdditionDoubler();

    assertEquals(10, doubler.doDouble(5));
    assertEquals(20, doubler.doDouble(10));
//    throw new RuntimeException("Kuku");
  }

  @Test
  public void multiplicationDoubler() {
    IntegerDoubler doubler = new MultiplicationDoubler();

    assertEquals(10, doubler.doDouble(5));
    assertEquals(20, doubler.doDouble(10));
  }

  @Test
  public void bitshiftDoubler() {
    IntegerDoubler doubler = new BitShiftDoubler();

    assertEquals(0, doubler.doDouble(5));
    assertEquals(0, doubler.doDouble(10));
  }
}
