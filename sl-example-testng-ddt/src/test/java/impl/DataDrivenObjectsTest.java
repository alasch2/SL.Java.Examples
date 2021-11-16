package impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import listener.DataDrivenTestListener;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.beans.Transient;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import static impl.DataDrivenTestCommon.DATA_PROVIDER_DATA;

//@Listeners({DataDrivenTestListener.class})
public class DataDrivenObjectsTest {

    @Test(dataProvider = "dataProvider")
    public void test(TestData testData) {
        DataDrivenTestCommon.test(testData.input, testData.expected);
    }

    @DataProvider
    public static Object[][] dataProvider() throws FileNotFoundException {
        Object[][] result = new Object[DATA_PROVIDER_DATA.length][];
        for (int i = 0; i < DATA_PROVIDER_DATA.length; i++) {
            result[i] = new Object[]{new TestData(DATA_PROVIDER_DATA[i][0], DATA_PROVIDER_DATA[i][1])};
        }
        return result;
    }

    @AllArgsConstructor
    @Getter
    @Setter
    @NoArgsConstructor
    public static class TestData1 {

        private String expected;
        @JsonIgnore
        private String comment;
        private String[] stringArray;
        private FileInputStream inputStream;
        private String input;


        @Transient
        public String getExpected() {
            return expected;
        }

        public TestData1(Object input, Object expected) throws FileNotFoundException {
            this(String.valueOf(expected), "some-comment", new String[]{"val1", "val2"}, new FileInputStream("/tmp/somefile.txt"), String.valueOf(input));
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class TestData {
        private String input;
        private String expected;
        private String comment;
        private String[] stringArray;

        private FileInputStream inputStream;

        public TestData(Object input, Object expected) throws FileNotFoundException {
            this(String.valueOf(input), String.valueOf(expected), "some-comment", new String[]{"val1", "val2"}, new FileInputStream("/tmp/somefile.txt"));
        }
    }
}
