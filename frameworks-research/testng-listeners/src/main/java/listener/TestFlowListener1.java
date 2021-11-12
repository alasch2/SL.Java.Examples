package listener;

import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

/**
 * Iterative test status
 * @author AlaSchneider
 *
 */
public class TestFlowListener1 extends TestFlowListener {
    private static final String DEFAULT_SUITE_NAME = "Surefire";

	@Override
	public void onStart(ITestContext testContext) {
		super.onStart(testContext);
	}
	
	@Override
	public void onFinish(ITestContext testContext) {
		super.onFinish(testContext);
	}
	
	@Override
	public void onTestStart(ITestResult tr) {
        System.out.println(getClass().getName() +".onTestStart: "+ toTestId(tr));
		addTestClass(tr);
		super.onTestStart(tr);
	}
	
	@Override
	public void onTestSuccess(ITestResult tr) {
        System.out.println("onTestSuccess: "+ toTestId(tr));
        updateTestClassRunInfo(tr);
		super.onTestSuccess(tr);
	}

	@Override
	public void onTestFailure(ITestResult tr) {
        System.out.println(getClass().getName() + ".onTestFailure of "+ toString(tr));
        updateTestClassRunInfo(tr);
		super.onTestFailure(tr);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult tr) {
        updateTestClassRunInfo(tr);
		super.onTestFailedButWithinSuccessPercentage(tr);
	}

    static String toTestId(ITestNGMethod method) {
    	String xmlTestName = "";
    	if (method.getXmlTest() != null) {
    		xmlTestName = method.getXmlTest().getName();    		
    		if (xmlTestName!=null && !xmlTestName.equals(DEFAULT_SUITE_NAME)) {
//    			xmlTestName = String.format(CLASS_NAME_FMT_WITH_XML_NAME, method.getXmlTest().getName());
    			System.out.println(String.format("Using xml test name '%s' for test id", xmlTestName));
    			return xmlTestName;
    		}
    	}
    	return getInstanceClassName(method);    	
    }
    
    static String getInstanceClassName(ITestNGMethod currentMethod) {
        return currentMethod.getInstance().getClass().getName();
    }
    
    static String toTestId(ITestResult result) {
        return toTestId(result.getMethod());
    }
}
