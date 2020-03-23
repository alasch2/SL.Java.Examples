package io.sl.ex.webcalculator;

import org.junit.Test;

import io.sl.ex.webcalculator.DateUtils;

import java.util.Date;

public class DateUtilsIT {

    @Test
    public void shouldGetDay() {
        new DateUtils().getDay(new Date());
    }
}