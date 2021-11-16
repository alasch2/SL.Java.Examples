package listener;

import listener.TestClassRunInfo.TestClassRunResult;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.testng.*;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * Iterative test status
 *
 * @author AlaSchneider
 */
@Slf4j
public abstract class AbstractTestFlowListener extends TestListenerAdapter {
    @Getter
    private ConcurrentMap<String, TestClassRunResult> testClasses = new ConcurrentHashMap<>();


    @Override
    public void onStart(ITestContext testContext) {
        // add tia stuff here - to exclude the test?
        log.info(getClass().getName() + ".onStart of suite:" + testContext.getName() + ", tests:" + toString(testContext));
        super.onStart(testContext);
    }

    @Override
    public void onFinish(ITestContext testContext) {
        log.info(getClass().getName() + ".onFinish of suite:" + testContext.getName());
        // createTestEvents
        for (String testClass : testClasses.keySet()) {
            log.info(String.format("class '%s' : result: %s", testClass, testClasses.get(testClass)));
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
