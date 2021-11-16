package listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.SkipException;
import tia.TIA;
import utils.hash.CrcHashFunction;
import utils.hash.HashFunction;
import utils.jackson.ObjectMapperProvider;

import java.util.Arrays;
import java.util.HashMap;

@Slf4j
public class DataDrivenTestListener extends AbstractTestFlowListener {
    private ObjectMapper customObjectMapper = ObjectMapperProvider.getCustomObjectMapper();

    static {
        TIA.addExcludedMethod("impl.DataDrivenObjectsTest.test.e25ca0df");
    }

    public DataDrivenTestListener() {
        try {
            customObjectMapper.writeValueAsString(new HashMap<>());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        handleTestStart(result);
        super.onTestStart(result);
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        super.onTestSuccess(tr);
    }

    private void handleTestStart(ITestResult result) {
        ITestNGMethod testMethod = result.getMethod();
        String qualifiedName = testMethod.getQualifiedName();
        HashFunction hashFunction = new CrcHashFunction();
        Object[] parameters = result.getParameters();
        String testId;
        if (parameters.length > 0) {
            String jsonParametersStr = getJsonString(parameters);
            String hash = hashFunction.getHash(jsonParametersStr);
            testId = qualifiedName + "." + hash;
            if (jsonParametersStr.contains("skip")) {
                String msg = "need skip because parameters json representation contains skip";
                log.warn(msg);
                throw new SkipException(msg);
            }
            String parametersStr = Arrays.toString(parameters);
            log.info("test: " + qualifiedName + " parametersStr:" + parametersStr + " jsonParametersStr: " + jsonParametersStr + " hash: " + hash);
        } else {
            testId = qualifiedName;
            log.info("test: " + qualifiedName);
        }
        if (TIA.saysSkip(testId)) {
            String msg = "need to skip because of TIA";
            log.warn(msg);
            throw new SkipException(msg);
        }
    }

    private String getJsonString(Object[] parameters) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        String jsonParametersStr;
        try {
            jsonParametersStr = customObjectMapper.writeValueAsString(parameters);
        } catch (JsonProcessingException e) {
            jsonParametersStr = "";
            log.error(e.getMessage(), e);
        }
        stopWatch.stop();
        log.info("jsonParametersStr: {}, stopWatch: {}", jsonParametersStr, stopWatch);
        return jsonParametersStr;
    }
}
