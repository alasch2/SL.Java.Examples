package listener;

import org.testng.ITestContext;
import org.testng.ITestResult;

/**
 * Iterative test status
 *
 * @author AlaSchneider
 */
public class TestFlowListener1 extends AbstractTestFlowListener {

    @Override
    public void onFinish(ITestContext testContext) {
        super.onFinish(testContext);
    }

    @Override
    public void onTestStart(ITestResult tr) {
        addTestClass(tr);
        super.onTestStart(tr);
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        System.out.println(getClass().getName() + ".onTestSuccess of " + toString(tr));
        updateTestClassRunInfo(tr);
        super.onTestSuccess(tr);
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        System.out.println(getClass().getName() + ".onTestFailure of " + toString(tr));
        updateTestClassRunInfo(tr);
        super.onTestFailure(tr);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult tr) {
        updateTestClassRunInfo(tr);
        super.onTestFailedButWithinSuccessPercentage(tr);
    }

}
