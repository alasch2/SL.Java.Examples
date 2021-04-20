package io.sealights.jira.webhook.research.web.controller;

import static io.sealights.jira.webhook.research.web.controller.IssueJsonDeserializer.Keys.CHANGE_LOG;
import static io.sealights.jira.webhook.research.web.controller.IssueJsonDeserializer.Keys.ISSUE;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.sealights.jira.webhook.research.service.JiraApiService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/sl/execution")
@Slf4j
public class WebhookController {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

	private static final String SL_LAB_ID = "SL_LAB_ID";
	private static final String SL_TEST_STAGE = "SL_TEST_STAGE";
    
	@Autowired
    private final JiraIssueHandler issueHandler;
	
	@Autowired
	private final JiraApiService jiraApiService;
	
	private String labIdFieldId;
	private String testStageFieldId;
	
    @Autowired
    public WebhookController(JiraApiService jiraApiService, JiraIssueHandler issueService) {
    	this.jiraApiService = jiraApiService;
        this.issueHandler = issueService;
        initSealightsFieldsIds();
    }

    @RequestMapping(value = "/webhook", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String onExecutionIssueUpdated(@RequestBody Map<String, Object> request) {
    	if (request.containsKey(ISSUE)) {
    		log.debug("INVOKED webhook {} from Jira ", request.get("webhookEvent"));
    		log.debug("\nbody:\n{}\n", GSON.toJson(request));
    		handleIssueUpdate(request);
    	}
    	else {
    		log.info("Ignore webhook {} from Jira - no issue was found", request.get("webhookEvent"), GSON.toJson(request));
    	}
        return "{\"result\": \"ok\"}";
    }

    public void handleIssueUpdate(Map<String, Object> request) {
        IssueJsonDeserializer deserializer = new IssueJsonDeserializer(GSON.toJson(request.get(ISSUE)), GSON.toJson(request.get(CHANGE_LOG)));
        issueHandler.handleIssueUpdate(deserializer.getIssue(labIdFieldId, testStageFieldId), deserializer.getChangeLog());
    }

    protected void initSealightsFieldsIds() {
    	Map<String,String> customFields = jiraApiService.getCustomFieldIds();
    	labIdFieldId = customFields.get(SL_LAB_ID);
    	testStageFieldId = customFields.get(SL_TEST_STAGE);
    }
    
}