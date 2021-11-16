package impl;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

//@Listeners({TestFlowListener1.class})
@Ignore
public class FailedTest {

    @Test
    public void passed_1() {
        assertTrue(true);
    }

    @Test
    public void passed_2() {
        assertTrue(true);
    }

    @Test
    public void passed_3() {
        assertTrue(true);
    }

    @Test
    public void failed_1() {
        assertTrue(false);
    }
}
