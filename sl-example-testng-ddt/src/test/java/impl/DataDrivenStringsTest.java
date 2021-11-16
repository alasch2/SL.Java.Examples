package impl;

import listener.DataDrivenTestListener;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//@Listeners({DataDrivenTestListener.class})
//@Ignore
public class DataDrivenStringsTest {

    @Test(dataProvider = "dataProviderObjectsArray")
    public void testWithDataProviderObjectsArray(String input, String expected) {
        DataDrivenTestCommon.test(input, expected);
    }

    @Test(dataProvider = "dataProviderObjectsIterator")
    public void testWithDataProviderObjectsIterable(String input, String expected) {
        DataDrivenTestCommon.test(input, expected);
    }


    @DataProvider
    public static Object[][] dataProviderObjectsArray() {
        return DataDrivenTestCommon.DATA_PROVIDER_DATA;
    }

    @DataProvider
    public static Iterator<Object[]> dataProviderObjectsIterator() {
        List list = new ArrayList();
        for (Object[] dataProviderData : DataDrivenTestCommon.DATA_PROVIDER_DATA) {
            list.add(dataProviderData);
        }
        return list.iterator();
    }
}
