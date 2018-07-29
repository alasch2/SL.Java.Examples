package io.sl.ex.junittestng.impl;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import io.sl.ex.junittestng.iface.IntegerDoubler;
import io.sl.ex.junittestng.impl.AdditionDoubler;
import io.sl.ex.junittestng.impl.BitShiftDoubler;
import io.sl.ex.junittestng.impl.MultiplicationDoubler;

@Test
public class TestDoublerTestNG {
    public void additionDoubler() {
        IntegerDoubler doubler = new AdditionDoubler();

        assertEquals(10, doubler.doDouble(5));
        assertEquals(20, doubler.doDouble(10));
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
