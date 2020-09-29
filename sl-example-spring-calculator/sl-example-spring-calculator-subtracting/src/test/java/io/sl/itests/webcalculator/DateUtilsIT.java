package io.sl.itests.webcalculator;

import org.junit.Test;

import java.util.Date;

public class DateUtilsIT {

    @Test
    public void shouldGetDay() {
        new DateUtils().getDay(new Date());
    }
}