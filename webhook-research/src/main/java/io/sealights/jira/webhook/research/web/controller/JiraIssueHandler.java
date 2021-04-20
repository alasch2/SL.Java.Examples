package io.sealights.jira.webhook.research.web.controller;

import static io.sealights.jira.webhook.research.web.controller.IssueJsonDeserializer.Keys.ISSUE;
import static io.sealights.jira.webhook.research.web.controller.IssueJsonDeserializer.Keys.STATUS;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.sealights.jira.webhook.research.entity.GraphQLModel.JiraTestExecution;
import io.sealights.jira.webhook.research.entity.GraphQLModel.JiraTestRunResult;
import io.sealights.jira.webhook.research.entity.JiraModel.ChangeLog;
import io.sealights.jira.webhook.research.entity.JiraModel.ChangeLogItem;
import io.sealights.jira.webhook.research.entity.JiraModel.JiraIssue;
import io.sealights.jira.webhook.research.entity.JiraTypeMapping;
import io.sealights.jira.webhook.research.service.SealightsApiService;
import io.sealights.jira.webhook.research.service.SealightsApiService.TestEvent;
import io.sealights.jira.webhook.research.service.SealightsApiService.TestStatus;
import io.sealights.jira.webhook.research.service.XrayService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JiraIssueHandler {
	
	@Autowired
	private final XrayService xrayService;
	@Autowired
	private final SealightsApiService sealightsApiService;
	
    @Autowired
    public JiraIssueHandler(XrayService xrayService, SealightsApiService sealightsApiService) {
    	this.xrayService = xrayService;
        this.sealightsApiService = sealightsApiService;
    }
    
    public boolean containsIssue(Map<String, Object> request) {
    	return request.containsKey(ISSUE);
    }

    public void handleIssueUpdate(JiraIssue issue, ChangeLog changeLog) {
    	// if issue - execution & status - to do (just created - get recommendations)
    	// - start execution
    	JiraTypeMapping issueType = JiraTypeMapping.findType(issue.getIssueType());
		ChangeLogItem change = changeLog.getChangeOf(STATUS);
    	if (issueType != null && change != null) {
    		log.debug("starts handling update of issue: {}, change log: {}", issue, changeLog);
    		handleTestExecutionStatusChange(issue, change);
    	}
    	else {
    		log.info("Skip not-relevant update of issue {}, changeLog:{}", issue.getKey(), changeLog);
    	}
    }

    protected void handleTestExecutionStatusChange(JiraIssue issue, ChangeLogItem change) {
		JiraTypeMapping newState = JiraTypeMapping.findType(change.getToString());
     	if (newState != null) {
			JiraTestExecution testExecution = xrayService.getExecution(issue.getId());
			log.debug("handling state '{}' of execution for {}", newState, testExecution);
			switch(newState) {
    		case statusStart:
    			log.info("Handling execution start ...");
    			new StartExecutionHandler(issue, testExecution, xrayService, sealightsApiService).execute();
    			break;
    		case statusEnd:
    			log.info("Handling execution end ...");
    			new EndExecutionHandler(issue, testExecution, xrayService, sealightsApiService).execute();
    			break;
    		default:
    			log.info("Ignore execution status {}", newState.getJiraName());
    			break;
    		}
    	}
    }
    
    static class StartExecutionHandler extends ExecutionUpdateHandler {

		public StartExecutionHandler(JiraIssue issue, JiraTestExecution testExecution, XrayService xrayService,
				SealightsApiService sealightsApiService) {
			super(issue, testExecution, xrayService, sealightsApiService);
		}

		@Override
		boolean execute() {
			getXrayService().setTestsToSkip(prepareTestsIdsToSKip());
			boolean startedOk = getSealightsApiService().startExecution(
					getTestExecution().getKey(), 
					getIssue().getSlLabId(), getIssue().getSlTestStage());
			if (startedOk) {
				log.info("Execution '{}' started", getTestExecution().getKey());
			}
			else {
				log.warn("Failed to start execution '{}'", getTestExecution().getKey());
			}
			return startedOk;
		}
		
		protected List<String> prepareTestsIdsToSKip() {
			List<String> testsToSkip = getSealightsApiService().getTestsToSkip();
			log.info("testsToSkip:{}", testsToSkip);
			Map<String, String> testNameToIdMap = getTestExecution().mapTestNameToRunId();
			return testsToSkip.stream().map(t->testNameToIdMap.get(t)).collect(Collectors.toList());
		}
    	
    }
    
    static class EndExecutionHandler extends ExecutionUpdateHandler {

		public EndExecutionHandler(JiraIssue issue, JiraTestExecution testExecution, XrayService xrayService,
				SealightsApiService sealightsApiService) {
			super(issue, testExecution, xrayService, sealightsApiService);
		}

		@Override
		boolean execute() {
			List<TestEvent> testEvents = getTestExecution().getTestRuns().getResults().stream()
					.map(result->{
						return createTestEvent(result);
					}).collect(Collectors.toList());
			getSealightsApiService().endExecution(getTestExecution().getKey(), testEvents);
			log.info("Ended execution '{}'", getTestExecution().getKey());
			return true;
		}
		
		private TestEvent createTestEvent(JiraTestRunResult result) {
			TestStatus testStatus = normalizeStatus(result);
			long started = normalizeTimestamp(testStatus, result.getStartedOn());
			long finished = normalizeTimestamp(testStatus, result.getFinishedOn());
			return  new TestEvent(
					result.getTestName(),
					started,
					finished,
					testStatus);
		}
		
		private TestStatus normalizeStatus(JiraTestRunResult result) {
			try {
				return TestStatus.valueOf(result.getStatus().getName());
			}
			catch (Exception e) {
				return TestStatus.FAILED;
			}
		}
		
		private long normalizeTimestamp(TestStatus testStatus, String timestampString) {
			if (timestampString == null || testStatus==TestStatus.SKIPPED) {
				return 0L;
			}
			else {
				return Long.parseLong(timestampString);
			}
		}
    }
    
    @AllArgsConstructor
    @Data
    static abstract class ExecutionUpdateHandler {
    	private JiraIssue issue;
    	private JiraTestExecution testExecution;
    	private XrayService xrayService;
     	private SealightsApiService sealightsApiService;
     	
     	abstract boolean execute();
    }
}
