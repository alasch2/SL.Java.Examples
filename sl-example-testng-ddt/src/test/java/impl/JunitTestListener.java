package impl;

import org.junit.runner.Description;
import org.junit.runner.notification.RunListener;

public class JunitTestListener extends RunListener {

    @Override
    public void testRunStarted(Description description) throws Exception {
        super.testRunStarted(description);
    }
}
