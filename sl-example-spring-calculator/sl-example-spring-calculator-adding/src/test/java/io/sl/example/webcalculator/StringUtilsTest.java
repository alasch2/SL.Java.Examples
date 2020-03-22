package io.sl.example.webcalculator;

import org.junit.Test;

import io.sl.example.webcalculator.StringUtils;

public class StringUtilsTest {

    @Test
    public void shouldJoinString() {
        StringUtils.join("a", "b");
    }
}