package io.sealights.jira.webhook.research.entity;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum JiraTypeMapping {
	testExecution("Test Execution"),
	statusToDo("TO DO"),
	statusStart("Start"),
	statusEnd("End");
	
	@Getter
	private final String jiraName;
	private JiraTypeMapping(String jiraName) {
		this.jiraName = jiraName;
	}
	
	public static JiraTypeMapping findType(String name) {
		for (JiraTypeMapping e : values()) {
			if (e.jiraName.equalsIgnoreCase(name)) {
				return e;
			}
		}
		log.warn("Status for '{}' is not supported", name);
		return null;
	}
}