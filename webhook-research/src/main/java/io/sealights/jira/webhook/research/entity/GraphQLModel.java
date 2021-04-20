package io.sealights.jira.webhook.research.entity;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

public class GraphQLModel {

	@Data
	public static class GetExecutionRequestWrapper {
		private GetExecutionRequest data;
		
		@JsonIgnore
		public JiraTestExecution getTestExecution() {
			return data.getGetTestExecution();
		}
	}
	
	@Data
	public static class GetExecutionRequest {
		private JiraTestExecution getTestExecution;
	}
	
	@Data
	public static class JiraTestExecution {
		private String issueId;
		private JiraTestExecutionProperties jira;
		private JiraExecutionTestRuns testRuns;
		
		public String getStatusName() {
			return jira.getStatus().getName();
		}
		
		public String getKey() {
			return jira.getKey();
		}
		
		public Map<String, String> mapTestNameToRunId() {
			return testRuns.results.stream().collect(
					Collectors.toMap(
							JiraTestRunResult::getTestName, JiraTestRunResult::getId));
		}
		
		@Override
		public String toString() {
			return String.format("JiraTestExecution [issueId=%s, %s, %s]", issueId, jira, testRuns);
		}
	}
	
	@Data
	public static class JiraTestExecutionProperties {
		private String key;
		private JiraStatus status;
		
		@Override
		public String toString() {
			return String.format("key=%s, %s", key, status);
		}
	}
	
	@Data
	public static class JiraStatus {
		private String name;

		@Override
		public String toString() {
			return String.format("status='%s'", name);
		}
	}
	
	@Data
	public static class JiraExecutionTestRuns {
		private List<JiraTestRunResult> results;
		
		@Override
		public String toString() {
			return String.format("testRuns='%s'", results);
		}
	}
	
	@Data
	public static class JiraTest {
		private JiraTestProperties jira;
		
		@Override
		public String toString() {
			return jira.toString();
		}
	}

	@Data
	public static class JiraTestProperties {
		private String key;
		
		@Override
		public String toString() {
			return key;
		}
	}
	
	@Data
	public static class JiraTestRunResult {
		private String id;
		private JiraTestType testType;
		private JiraStatus status;
		private JiraTest test;
		private String startedOn;
		private String finishedOn;
		
		public String getTestName() {
			return test.getJira().getKey();
		}

		@Override
		public String toString() {
			return String.format(
					"id=%s, %s, %s, test=%s, startedOn=%s, finishedOn=%s]", 
					id, testType, status, test, startedOn, finishedOn);
		}
		
	}

	@Data
	public static class JiraTestType {
		private String name;
		
		@Override
		public String toString() {
			return String.format("testType='%s'", name);
		}
	}
	
}
