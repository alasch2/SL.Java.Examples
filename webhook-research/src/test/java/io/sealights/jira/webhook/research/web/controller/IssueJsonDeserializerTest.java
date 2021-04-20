package io.sealights.jira.webhook.research.web.controller;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

import io.sealights.jira.webhook.research.entity.JiraModel.ChangeLog;
import io.sealights.jira.webhook.research.entity.JiraModel.JiraIssue;
import static io.sealights.jira.webhook.research.web.controller.IssueJsonDeserializer.Keys.STATUS;

public class IssueJsonDeserializerTest {

	@Test
	public void testIssueCreate() {
		IssueJsonDeserializer deserializer = new IssueJsonDeserializer(ISSUE_JSON, CHANGE_LOG_JSON);
		JiraIssue issue = deserializer.getIssue("customfield_10038", "customfield_10039");
		assertNotNull(issue);
	}
	
	@Test
	public void testChangeLogCreate() {
		IssueJsonDeserializer deserializer = new IssueJsonDeserializer(ISSUE_JSON, CHANGE_LOG_JSON);
		ChangeLog changeLog = deserializer.getChangeLog();
		assertNotNull(changeLog);
	}
	
	@Test
	public void testExecutionStateChangeFound() {
		IssueJsonDeserializer deserializer = new IssueJsonDeserializer(ISSUE_JSON, CHANGE_LOG_JSON);
		ChangeLog changeLog = deserializer.getChangeLog();
		assertNotNull(changeLog);
		assertNotNull(changeLog.getChangeOf(STATUS));
	}
	
	private static String ISSUE_JSON = 
			"{" + 
					"  \"id\": \"11007\"," + 
					"  \"self\": \"https://sealightsdevelop.atlassian.net/rest/api/2/11007\"," + 
					"  \"key\": \"SLDEV-8196\"," + 
					"  \"fields\": {" + 
					"    \"statuscategorychangedate\": \"2021-03-22T14:35:11.318+0200\"," + 
					"    \"issuetype\": {" + 
					"      \"self\": \"https://sealightsdevelop.atlassian.net/rest/api/2/issuetype/10001\"," + 
					"      \"id\": \"10001\"," + 
					"      \"description\": \"Functionality or a feature expressed as a user goal.\"," + 
					"      \"iconUrl\": \"https://sealightsdevelop.atlassian.net/secure/viewavatar?size\\u003dmedium\\u0026avatarId\\u003d10315\\u0026avatarType\\u003dissuetype\"," + 
					"      \"name\": \"Story\"," + 
					"      \"subtask\": false," + 
					"      \"avatarId\": 10315" + 
					"    }," + 
					"   \"customfield_10038\": [" +
					"                      \"PROD_A\"" +
					"                    ]," +
					"   \"customfield_10039\": [" +
					"                      \"MANUAL_TESTS\"" +
					"                   ]," +
                    "    \"project\": {" + 
					"      \"self\": \"https://sealightsdevelop.atlassian.net/rest/api/2/project/10002\"," + 
					"      \"id\": \"10002\"," + 
					"      \"key\": \"SLDEV\"," + 
					"      \"name\": \"SLDEV\"," + 
					"      \"projectTypeKey\": \"software\"," + 
					"      \"simplified\": false," + 
					"      \"avatarUrls\": {" + 
					"        \"48x48\": \"https://sealightsdevelop.atlassian.net/secure/projectavatar?pid\\u003d10002\\u0026avatarId\\u003d10400\"," + 
					"        \"24x24\": \"https://sealightsdevelop.atlassian.net/secure/projectavatar?size\\u003dsmall\\u0026s\\u003dsmall\\u0026pid\\u003d10002\\u0026avatarId\\u003d10400\"," + 
					"        \"16x16\": \"https://sealightsdevelop.atlassian.net/secure/projectavatar?size\\u003dxsmall\\u0026s\\u003dxsmall\\u0026pid\\u003d10002\\u0026avatarId\\u003d10400\"," + 
					"        \"32x32\": \"https://sealightsdevelop.atlassian.net/secure/projectavatar?size\\u003dmedium\\u0026s\\u003dmedium\\u0026pid\\u003d10002\\u0026avatarId\\u003d10400\"" + 
					"      }" + 
					"    }," + 
					"    \"fixVersions\": []," + 
					"    \"workratio\": -1," + 
					"    \"lastViewed\": \"2021-03-22T14:37:24.512+0200\"," + 
					"    \"watches\": {" + 
					"      \"self\": \"https://sealightsdevelop.atlassian.net/rest/api/2/issue/SLDEV-8196/watchers\"," + 
					"      \"watchCount\": 1," + 
					"      \"isWatching\": true" + 
					"    }," + 
					"    \"issuerestriction\": {" + 
					"      \"issuerestrictions\": {}," + 
					"      \"shouldDisplay\": false" + 
					"    }," + 
					"    \"created\": \"2021-03-22T14:35:10.867+0200\"," + 
					"    \"priority\": {" + 
					"      \"self\": \"https://sealightsdevelop.atlassian.net/rest/api/2/priority/3\"," + 
					"      \"iconUrl\": \"https://sealightsdevelop.atlassian.net/images/icons/priorities/medium.svg\"," + 
					"      \"name\": \"Medium\"," + 
					"      \"id\": \"3\"" + 
					"    }," + 
					"    \"labels\": []," + 
					"    \"customfield_10026\": []," + 
					"    \"customfield_10018\": {" + 
					"      \"hasEpicLinkFieldDependency\": false," + 
					"      \"showField\": false," + 
					"      \"nonEditableReason\": {" + 
					"        \"reason\": \"PLUGIN_LICENSE_ERROR\"," + 
					"        \"message\": \"The Parent Link is only available to Jira Premium users.\"" + 
					"      }" + 
					"    }," + 
					"    \"customfield_10019\": \"0|i00cdz:\"," + 
					"    \"versions\": []," + 
					"    \"issuelinks\": []," + 
					"    \"updated\": \"2021-03-22T15:50:09.458+0200\"," + 
					"    \"status\": {" + 
					"      \"self\": \"https://sealightsdevelop.atlassian.net/rest/api/2/status/10000\"," + 
					"      \"description\": \"\"," + 
					"      \"iconUrl\": \"https://sealightsdevelop.atlassian.net/\"," + 
					"      \"name\": \"To Do\"," + 
					"      \"id\": \"10000\"," + 
					"      \"statusCategory\": {" + 
					"        \"self\": \"https://sealightsdevelop.atlassian.net/rest/api/2/statuscategory/2\"," + 
					"        \"id\": 2," + 
					"        \"key\": \"new\"," + 
					"        \"colorName\": \"blue-gray\"," + 
					"        \"name\": \"New\"" + 
					"      }" + 
					"    }," + 
					"    \"components\": []," + 
					"    \"description\": \"edit description\"," + 
					"    \"timetracking\": {}," + 
					"    \"attachment\": []," + 
					"    \"summary\": \"test-sl-webhook\"," + 
					"    \"creator\": {" + 
					"      \"self\": \"https://sealightsdevelop.atlassian.net/rest/api/2/user?accountId\\u003d557058%3Ad763bbfe-527c-470f-b40f-5cea6c440162\"," + 
					"      \"accountId\": \"557058:d763bbfe-527c-470f-b40f-5cea6c440162\"," + 
					"      \"avatarUrls\": {" + 
					"        \"48x48\": \"https://avatar-management--avatars.us-west-2.prod.public.atl-paas.net/557058:d763bbfe-527c-470f-b40f-5cea6c440162/c0d035a4-61a9-4c85-a62b-08cbbb89016b/48\"," + 
					"        \"24x24\": \"https://avatar-management--avatars.us-west-2.prod.public.atl-paas.net/557058:d763bbfe-527c-470f-b40f-5cea6c440162/c0d035a4-61a9-4c85-a62b-08cbbb89016b/24\"," + 
					"        \"16x16\": \"https://avatar-management--avatars.us-west-2.prod.public.atl-paas.net/557058:d763bbfe-527c-470f-b40f-5cea6c440162/c0d035a4-61a9-4c85-a62b-08cbbb89016b/16\"," + 
					"        \"32x32\": \"https://avatar-management--avatars.us-west-2.prod.public.atl-paas.net/557058:d763bbfe-527c-470f-b40f-5cea6c440162/c0d035a4-61a9-4c85-a62b-08cbbb89016b/32\"" + 
					"      }," + 
					"      \"displayName\": \"Ala Schneider\"," + 
					"      \"active\": true," + 
					"      \"timeZone\": \"Asia/Jerusalem\"," + 
					"      \"accountType\": \"atlassian\"" + 
					"    }," + 
					"    \"subtasks\": []," + 
					"    \"reporter\": {" + 
					"      \"self\": \"https://sealightsdevelop.atlassian.net/rest/api/2/user?accountId\\u003d557058%3Ad763bbfe-527c-470f-b40f-5cea6c440162\"," + 
					"      \"accountId\": \"557058:d763bbfe-527c-470f-b40f-5cea6c440162\"," + 
					"      \"avatarUrls\": {" + 
					"        \"48x48\": \"https://avatar-management--avatars.us-west-2.prod.public.atl-paas.net/557058:d763bbfe-527c-470f-b40f-5cea6c440162/c0d035a4-61a9-4c85-a62b-08cbbb89016b/48\"," + 
					"        \"24x24\": \"https://avatar-management--avatars.us-west-2.prod.public.atl-paas.net/557058:d763bbfe-527c-470f-b40f-5cea6c440162/c0d035a4-61a9-4c85-a62b-08cbbb89016b/24\"," + 
					"        \"16x16\": \"https://avatar-management--avatars.us-west-2.prod.public.atl-paas.net/557058:d763bbfe-527c-470f-b40f-5cea6c440162/c0d035a4-61a9-4c85-a62b-08cbbb89016b/16\"," + 
					"        \"32x32\": \"https://avatar-management--avatars.us-west-2.prod.public.atl-paas.net/557058:d763bbfe-527c-470f-b40f-5cea6c440162/c0d035a4-61a9-4c85-a62b-08cbbb89016b/32\"" + 
					"      }," + 
					"      \"displayName\": \"Ala Schneider\"," + 
					"      \"active\": true," + 
					"      \"timeZone\": \"Asia/Jerusalem\"," + 
					"      \"accountType\": \"atlassian\"" + 
					"    }," + 
					"    \"aggregateprogress\": {" + 
					"      \"progress\": 0," + 
					"      \"total\": 0" + 
					"    }," + 
					"    \"customfield_10000\": \"{}\"," + 
					"    \"progress\": {" + 
					"      \"progress\": 0," + 
					"      \"total\": 0" + 
					"    }," + 
					"    \"votes\": {" + 
					"      \"self\": \"https://sealightsdevelop.atlassian.net/rest/api/2/issue/SLDEV-8196/votes\"," + 
					"      \"votes\": 0," + 
					"      \"hasVoted\": false" + 
					"    }" + 
					"  }" + 
					"}" + 
					"";

	private static String CHANGE_LOG_JSON = "{" +
			" \"id\": \"12237\", " +
			" \"items\": [ " +
				"  { " +
				"    \"field\": \"Link\", " +
				"    \"fieldtype\": \"jira\", " +
				"    \"to\": \"SLDEV-8198\", " +
				"    \"toString\": \"This issue blocks SLDEV-8198\" " +
				"  }, " +
				"  { " +
				"    \"field\": \"status\", " +
				"    \"fieldtype\": \"jira\", " +
				"    \"fieldId\": \"status\", " +
				"    \"to\": \"3\", " +
				"    \"toString\": \"In Progress\"" +
				"  } " +
				"] " +
			"}";
	private static String REQUEST_JSON = "{" +
			"  \"timestamp\": 1616424562764," +
			"  \"webhookEvent\": \"jira:issue_created\", " +
			"  \"issue_event_type_name\": \"issue_created\", " +
			"  \"user\": { " +
		"  \"self\": \"https://sealightsdevelop.atlassian.net/rest/api/2/user?accountId\u003d557058%3Ad763bbfe-527c-470f-b40f-5cea6c440162\", " +
		"  \"accountId\": \"557058:d763bbfe-527c-470f-b40f-5cea6c440162\", " +
		"  \"avatarUrls\": { " +
		"  \"48x48\": \"https://avatar-management--avatars.us-west-2.prod.public.atl-paas.net/557058:d763bbfe-527c-470f-b40f-5cea6c440162/c0d035a4-61a9-4c85-a62b-08cbbb89016b/48\", " +
		"  \"24x24\": \"https://avatar-management--avatars.us-west-2.prod.public.atl-paas.net/557058:d763bbfe-527c-470f-b40f-5cea6c440162/c0d035a4-61a9-4c85-a62b-08cbbb89016b/24\", " +
		"  \"16x16\": \"https://avatar-management--avatars.us-west-2.prod.public.atl-paas.net/557058:d763bbfe-527c-470f-b40f-5cea6c440162/c0d035a4-61a9-4c85-a62b-08cbbb89016b/16\", " +
		"  \"32x32\": \"https://avatar-management--avatars.us-west-2.prod.public.atl-paas.net/557058:d763bbfe-527c-470f-b40f-5cea6c440162/c0d035a4-61a9-4c85-a62b-08cbbb89016b/32\" " +
		"	    }, " +
		"  \"displayName\": \"Ala Schneider\", " +
		"  \"active\": true, " +
		"  \"timeZone\": \"Asia/Jerusalem\", " +
		"  \"accountType\": \"atlassian\" " +
		"	  }, " +
		ISSUE_JSON +
		" \"changelog\": { " +
		" \"id\": \"12237\", " +
		" \"items\": [ " +
			"  { " +
			"    \"field\": \"Link\", " +
			"    \"fieldtype\": \"jira\", " +
			"    \"to\": \"SLDEV-8198\", " +
			"    \"toString\": \"This issue blocks SLDEV-8198\" " +
			"  } " +
			"] " +
			"} " +
		"}";
}
