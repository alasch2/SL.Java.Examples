package listener;

import org.testng.*;
import tia.TIA;

import java.util.*;

public class DepenentTestListener extends TestListenerAdapter {
    Map<String, List<TestMethod>> dependencyMapping;

    static {
        TIA.addExcludedMethod("FabulousStuffTest.testFoo");
        TIA.addExcludedMethod("FabulousStuffTest.testBaz");
        TIA.addExcludedMethod("pkg.GorgeousStuff.testY");
    }

    @Override
    public void onStart(ITestContext testContext) {
        super.onStart(testContext);

        List<ITestNGMethod> testMethods = Arrays.asList(testContext.getAllTestMethods());

        dependencyMapping = getDependencyMapping(testMethods);
        System.out.println(getClass().getName() + ".onStart");
    }

    @Override
    public void onTestStart(ITestResult result) {
        ITestNGMethod currentMethod = result.getMethod();

        boolean areAllDependentMethodsTiaSkipped = dependencyMapping
                .getOrDefault(currentMethod.getQualifiedName(), Collections.emptyList()).stream()
                .map(TestMethod::isTiaSkipped)
                .reduce(true, (accumulated, current) -> accumulated && current);

        if (TIA.saysSkip(currentMethod.getQualifiedName()) && areAllDependentMethodsTiaSkipped) {
            throw new SkipException("this was skipped");
        }
    }

    private Map<String, List<TestMethod>> getDependencyMapping(List<ITestNGMethod> testMethods) {
        HashMap<String, List<TestMethod>> result = new HashMap<>();
        for (ITestNGMethod keyMethod : testMethods) {
            for (ITestNGMethod method : testMethods) {
                String keyMethodQualifiedName = keyMethod.getQualifiedName();
                if (Arrays.asList(method.getMethodsDependedUpon()).contains(keyMethodQualifiedName)) {
                    if (result.get(keyMethodQualifiedName) == null) {
                        ArrayList<TestMethod> maybeDependentMethod = new ArrayList<>();
                        maybeDependentMethod.add(new TestMethod(method.getQualifiedName(), TIA.saysSkip(method.getQualifiedName())));
                        result.put(keyMethodQualifiedName, maybeDependentMethod);
                    } else {
                        result.get(keyMethodQualifiedName).add(new TestMethod(method.getQualifiedName(), TIA.saysSkip(method.getQualifiedName())));
                    }
                }
            }
        }

        return result;
    }
}
