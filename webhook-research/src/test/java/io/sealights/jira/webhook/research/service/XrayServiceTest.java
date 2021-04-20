package io.sealights.jira.webhook.research.service;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

import io.sealights.jira.webhook.research.entity.GraphQLModel.JiraTestExecution;

public class XrayServiceTest {
	
	@Test
	public void testQueryStringOk() {
		String query = XrayService.buildGetExecutionQuery("10001");
		System.out.println(query);
	}

	@Test
	public void testGetToken() {
		String token = new XrayService().getToken();
		System.out.println(token);
	}

	@Test
	public void testGetExecution() {
		JiraTestExecution execution = new XrayService().getExecution("11010");
		assertNotNull("execution is null", execution);
		System.out.println(execution);
	}
}
