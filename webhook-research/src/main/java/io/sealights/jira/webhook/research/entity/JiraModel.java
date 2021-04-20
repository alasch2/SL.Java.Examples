package io.sealights.jira.webhook.research.entity;

import java.util.List;

import lombok.Data;

public class JiraModel {
	
	@Data
	public static class JiraIssue {
		private String id;
		private String key;
		private String self;
		private String issueType;
		private String status;
		private String slTestStage;
		private String slLabId;
	}
    
    @Data
    public static class ChangeLog {
    	private List<ChangeLogItem> items;

    	public boolean containsChangeOf(String field) {
    	    return getChangeOf(field) != null;
        }

        public ChangeLogItem getChangeOf(String field) {
            for (ChangeLogItem item : items) {
                if (field.equalsIgnoreCase(item.getField())) {
                    return item;
                }
            }
            return null;
        }
    }
    
    @Data
    public static class ChangeLogItem {
    	private String field;
    	private String fromString;
    	private String toString;
    }
}
