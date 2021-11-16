package impl;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

//@Listeners({TestFlowListener1.class})
//@Listeners({DepenentTestListener.class})
public class FabulousStuffTest {
    @Test(dependsOnMethods = {"testBar"})
    public void testFoo() {
        int result = new FabulousStuff().addThings(59, 2);
        assertEquals(result, 61);
    }

    //    @Test(dependsOnMethods = {"FabulousStuffTest.testFoo", "pkg.GorgeousStuff.testY"})
    @Test(dependsOnMethods = {"FabulousStuffTest.testFoo"})
    public void testBaz() {
        int result = new FabulousStuff().addThings(59, 2);
        assertEquals(result, 61);
    }

    @Test
    public void testBar() {
        int result = new FabulousStuff().addThings(59, 2);
        assertEquals(result, 61);
    }
}