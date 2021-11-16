package impl;

import org.testng.Assert;

import java.util.Locale;

public class DataDrivenTestCommon {
    public static final Object[][] DATA_PROVIDER_DATA = {
            new Object[]{"hello", "HELLO"},
            new Object[]{"world", "WORLD"},
            new Object[]{"world1", "WORLD1"},
            new Object[]{"world2", "WORLD2"},
            new Object[]{"rewrite-parameter", "REWRITE-PARAMETER"},
            new Object[]{"skip", "SKIP"}
    };

    public static void test(String input, String expected) {
        String actual = input.toUpperCase(Locale.ROOT);
        Assert.assertEquals(actual, expected);
    }
}
