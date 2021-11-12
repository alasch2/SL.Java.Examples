package listener;

import java.util.Set;

import org.testng.ITestContext;
import org.testng.ITestResult;

/**
 * Iterative test status
 * @author AlaSchneider
 *
 */
public class TestFlowListener2 extends TestFlowListener {

	@Override
	public void onFinish(ITestContext testContext) {
        handleResults(testContext.getPassedTests().getAllResults());
        handleResults(testContext.getFailedTests().getAllResults());
        handleResults(testContext.getFailedButWithinSuccessPercentageTests().getAllResults());
		super.onFinish(testContext);
	}
	
	private void handleResults(Set<ITestResult> results) {
		for (ITestResult tr : results) {
			addTestClass(tr);
			updateTestClassRunInfo(tr);
		}
	}

}
