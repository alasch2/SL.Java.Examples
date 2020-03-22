package io.sl.example.webcalculator;

import org.junit.Test;

import io.sl.example.webcalculator.DateUtils;

import java.util.Date;

public class DateUtilsIT {

    @Test
    public void shouldGetDay() {
        new DateUtils().getDay(new Date());
    }
}