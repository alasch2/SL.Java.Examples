package listener;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.IResultMap;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import listener.TestClassRunInfo.TestClassRunResult;
import lombok.Getter;

/**
 * Iterative test status
 * @author AlaSchneider
 *
 */
public abstract class TestFlowListener extends TestListenerAdapter implements IInvokedMethodListener {
	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        System.out.println(getClass().getName()+".beforeInvocation of method:"+ method.getTestMethod().getQualifiedName());
	}

	@Getter
	private ConcurrentMap<String, TestClassRunResult> testClasses = new ConcurrentHashMap<>();


	@Override
	public void onStart(ITestContext testContext) {
		// add tia stuff here - to exclude the test?
        System.out.println(getClass().getName()+".onStart of suite:"+ testContext.getSuite().getName() + ", tests:" + toString(testContext));
		super.onStart(testContext);
	}

	@Override
	public void onFinish(ITestContext testContext) {
        System.out.println(getClass().getName()+".onFinish of suite:"+ testContext.getSuite().getName());
        // createTestEvents
        for (String testClass : testClasses.keySet()) {
        	System.out.println(String.format("class '%s' : result: %s", testClass, testClasses.get(testClass)));
        }
		super.onFinish(testContext);
	}
	
	protected void addTestClass(ITestResult tr) {
		testClasses.putIfAbsent(resolveTestFlowName(tr), new TestClassRunResult(tr.getStartMillis()));
	}
	
	protected void updateTestClassRunInfo(ITestResult tr) {
		String key = resolveTestFlowName(tr);
		TestClassRunResult runInfo = testClasses.get(key);
		runInfo.setEndResult(tr.getEndMillis(), tr.getStatus());
		testClasses.put(key, runInfo);		
	}
	
	protected String toString(ITestContext testContext) {
		return String.format("all methods:%s%npassedTests:%s", 
				Arrays.asList(testContext.getAllTestMethods()).stream().map(ITestNGMethod::getQualifiedName)
				.collect(Collectors.toList()).toString(), toString(testContext.getPassedTests()));
	}
	
	protected String resolveTestFlowName(ITestResult tr) {
		System.out.println(String.format("The ITestResult:tr.getTestContext().getName()=%s, getInstanceName()=%s", 
				tr.getTestContext().getName(), tr.getInstanceName()));
		return tr.getTestName() != null ? tr.getTestName() : tr.getInstanceName();
	}
	
	protected String toString(ITestResult tr) {
		return String.format("suite:%s, instanceName:%s, testMethod:%s, started: %d, ended:%d", 
				tr.getTestContext().getName(), tr.getInstanceName(), tr.getName(), tr.getStartMillis(), tr.getEndMillis());
		
	}

	protected String toString(IResultMap results) {
//		return results.getAllResults().stream().map(ITestResult::getInstanceName).distinct().collect(Collectors.toList()).toString();
		return results.getAllResults().stream().map(e -> {
			return new TestClassRunInfo(e.getTestName(), new TestClassRunResult(e.getStartMillis(), e.getEndMillis(), e.getStatus()));
		})
				.collect(Collectors.toList()).toString();
	}
	
}
