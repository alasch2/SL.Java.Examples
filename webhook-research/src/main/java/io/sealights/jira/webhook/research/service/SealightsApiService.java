package io.sealights.jira.webhook.research.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SealightsApiService {
	private static final String DEFAULT_TEST_STAGE = "Manual Tests";
	
	private static String[] SKIP_TESTS = {
			"CALC-9", 
	};
	
	public boolean startExecution(String executionId, String labId, String testStage) {
		Assert.notNull(executionId, "Cannot start execution without execution id");
		Assert.notNull(labId, "Cannot start execution without lab id");
		if (testStage == null) {
			testStage = DEFAULT_TEST_STAGE;
		}
		log.info("create-session API should be called for execution {}, labId:{}, testStage:{}", executionId, labId, testStage);
		return true;		
	}
	
	public void endExecution(String executionId, List<TestEvent> testEvents) {
		log.info("test-events API should be called for testEvents {}", testEvents);
		log.info("delete-session API should be called for execution id {}", executionId);
	}
	
	public List<String> getTestsToSkip() {
		return Arrays.asList(SKIP_TESTS);
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class TestEvent {
		private String name;
		private long started;
		private long ended;
		private TestStatus status;
	}
	
	public enum TestStatus {
		PASSED, FAILED, SKIPPED
	}
}
