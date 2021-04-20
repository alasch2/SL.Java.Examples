package io.sealights.jira.webhook.research.service;

import java.util.Map;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class JiraApiServiceTest {
	private JiraApiService service = new JiraApiService();

	@Test
	public void testGetFields() {
		Map<String,String> fieldsMap = service.getCustomFieldIds();
		assertNotNull(fieldsMap);
	}
}
