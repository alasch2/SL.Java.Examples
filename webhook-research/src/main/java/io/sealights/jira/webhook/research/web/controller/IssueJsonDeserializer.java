package io.sealights.jira.webhook.research.web.controller;

import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import io.sealights.jira.webhook.research.entity.JiraModel.ChangeLog;
import io.sealights.jira.webhook.research.entity.JiraModel.JiraIssue;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class IssueJsonDeserializer {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    public static class Keys {
    	public static final String ISSUE = "issue";
		public static final String CHANGE_LOG = "changelog";
		public static final String STATUS = "status";
    }
	
	private String issueJson;
	private String changeLogJson;
	
	public JiraIssue getIssue(String labIdFieldId, String testStageFieldId) {
		JiraIssue issue = GSON.fromJson(issueJson, JiraIssue.class);
		JsonElement issueElement = GSON.toJsonTree(asMap());
		JsonElement fieldsElement = getMember(issueElement, "fields");
		issue.setIssueType(getType(fieldsElement));
		issue.setStatus(getStatus(fieldsElement));
		issue.setSlLabId(getStringFieldValue(fieldsElement, labIdFieldId));
		issue.setSlTestStage(getStringFieldValue(fieldsElement, testStageFieldId));
		log.debug("{}", issue);
		return issue;
	}
	
	public ChangeLog getChangeLog() {
		ChangeLog changeLog = GSON.fromJson(changeLogJson, ChangeLog.class);
		return changeLog;
	}
	
	private String getType(JsonElement fieldsElement) {
		JsonElement issuetTypeElement = getMember(fieldsElement, "issuetype");
		return getMember(issuetTypeElement, "name").getAsString();
	}
	
	private String getStatus(JsonElement fieldsElement) {
		JsonElement issuetTypeElement = getMember(fieldsElement, "status");
		return getMember(issuetTypeElement, "name").getAsString();
	}
	
	private String getStringFieldValue(JsonElement fieldsElement, String fieldName) {
		JsonElement element = getMember(fieldsElement, fieldName);
		return element != null ? element.getAsString() : null;
	}
	
	private Map<String,Object> asMap() {
		 Gson GSON = new GsonBuilder().setPrettyPrinting().create();
		 Type mapType = new TypeToken<Map<String, Object>>() {}.getType();
		 return GSON.fromJson(issueJson, mapType);
	}
	
	private JsonElement getMember(JsonElement element, String memberName) {
		JsonElement member = element.getAsJsonObject().get(memberName);
		log.debug("{}:{}", memberName, member);
		return member;
	}
}
