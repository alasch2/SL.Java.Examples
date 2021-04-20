package io.sealights.jira.webhook.research.service;

import java.util.List;

import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import io.sealights.jira.webhook.research.entity.GraphQLModel.GetExecutionRequestWrapper;
import io.sealights.jira.webhook.research.entity.GraphQLModel.JiraTestExecution;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class XrayService {
	private static String ROOT = "https://xray.cloud.xpand-it.com/api/v2/";
	private static String GRAPH_QL = "graphql";
	private static String AUTHENTICATE = "authenticate";
	private static String AUTH_HEADER = "Authorization";
	private static String BEARER = " Bearer ";
	private static String JIRA_CLIENT_ID = "2AB958DD1A764F9794025A95011AD016";
	private static String JIRA_CLIENT_SECRET = "e6040648b8acd6866b28fd510446ed9864179e723d541a1568314f06ef99b659";
	
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private String token = null;
	private final Executor executor;
	
	public XrayService() {
		executor = Executor.newInstance();
		getToken();
	}
	
	public String getToken() {
		if (token != null) {
			return token;
		}
		JsonObject json = new JsonObject();
		json.addProperty("client_id", JIRA_CLIENT_ID);
		json.addProperty("client_secret", JIRA_CLIENT_SECRET);
		String body = GSON.toJson(json);
		try {
			token = executor.execute(Request.Post(ROOT + AUTHENTICATE)
					.bodyString(body, ContentType.APPLICATION_JSON))
					.returnContent()
					.asString().replaceAll("\"", "");
			log.debug("token: {}", token);
			return token;
		}
		catch(Exception e) {
			log.error("Failes to get execution",e);
			return "error";
		}
	}
	
	public JiraTestExecution getExecution(String issueId) {
		String requestBody = buildGetExecutionQuery(issueId);
		try {
			String data = 
					executor.execute(
					Request.Post(ROOT+GRAPH_QL)
					.addHeader(AUTH_HEADER, BEARER + token)
					.bodyString(requestBody, ContentType.APPLICATION_JSON)
					)
					.returnContent().asString();
			log.debug("body: {}", data);
			GetExecutionRequestWrapper executionWrapper = GSON.fromJson(data, GetExecutionRequestWrapper.class);
			log.debug("execution: {}", data, executionWrapper);
			return executionWrapper.getTestExecution();
		}
		catch(Exception e) {
			log.error("Failes to get execution '{}'", issueId, e);
			return null;
		}		
	}
	
	public void setTestsToSkip(List<String> testsToSkip) {
		log.debug("setting testsToSkip:{}", testsToSkip);
		for (String id: testsToSkip) {
			updateTestRunStatus(id);
		}
	}
	
	protected void updateTestRunStatus(String testRunId) {
		log.debug("updating testRun (id:{})", testRunId);
		String requestBody = buildQuery(Queries.UPDATE_TEST_RUN_STATUS_FMT, testRunId);
		try {
			String responseBody = 
					executor.execute(
							Request.Post(ROOT + GRAPH_QL)
							.addHeader(AUTH_HEADER, BEARER + token)
							.bodyString(requestBody, ContentType.APPLICATION_JSON)
							)
					.returnContent().asString();
			log.debug("update response: {}", responseBody);
		}
		catch(Exception e) {
			log.error("Failes to get execution '{}'", testRunId, e);
		}
	}
	
	protected static String buildGetExecutionQuery(String issueId) {
		return buildQuery(Queries.GET_EXECUTION_TESTRUNS_FMT, issueId);
	}
	
	protected static String buildUpdateTestStatusQuery(String id) {
		return buildQuery(Queries.UPDATE_TEST_RUN_STATUS_FMT, id);
	}
	
	protected static String buildQuery(String format, Object... arguments) {
		JsonObject json = new JsonObject();
		json.addProperty("query", String.format(format, arguments));
		String query = new Gson().toJson(json);
		log.debug("guery:{}", query);
		return query;
	}
	
	static class Queries {
		final static String SIMPLE_Q1= "query{getTests(limit:50){total}}";
		final static String SIMPLE_Q2 = "query{getTestExecution(issueId:\"%s\"){issueId}}";
		final static String GET_EXECUTION_TESTRUNS_FMT = "query{getTestExecution(issueId:\"%s\"){issueId" + 
				"    jira (fields:[\"key\", \"status\"])" + 
				"    testRuns(limit:100) {" + 
				"      results {" + 
				"        id" + 
				"        testType {" + 
				"          name" + 
				"        }" + 
				"        status {" + 
				"          name" + 
				"        }" + 
				"        test {" + 
				"          jira (fields:[\"key\"])" + 
				"        }" + 
				"        startedOn " +
				"        finishedOn " +
				"      }" + 
				"    }" + 
				"}}";
		
		final static String GET_EXECUTION_FULL_FMT = "query{getTestExecution(issueId:\"%s\"){issueId" + 
				"    jira (fields:[\"key\", \"status\"])" + 
				"    tests(limit:100) {" + 
				"      results {" + 
				"        issueId" + 
				"        jira (fields:[\"key\"])" + 
				"      }" + 
				"    }" + 
				"    testRuns(limit:100) {" + 
				"      results {" + 
				"        id" + 
				"        testType {" + 
				"          name" + 
				"        }" + 
				"        status {" + 
				"          name" + 
				"        }" + 
				"      }" + 
				"    }" + 
				"}}";
		
		final static String GET_EXECUTION_TESTS_FMT = "query{getTestExecution(issueId:\"%s\"){issueId" + 
				"    jira (fields:[\"key\", \"status\"])" + 
				"    tests(limit:100) {" + 
				"      results {" + 
				"        issueId" + 
				"        testType {" + 
				"          name" + 
				"        }" + 
				"        status {" + 
				"          name" + 
				"        }" + 
				"      }" + 
				"    }" + 
				"}}";
		final static String UPDATE_TEST_RUN_STATUS_FMT = "mutation{updateTestRunStatus(id: \"%s\", status: \"SKIPPED\")}";
	}
	
}
