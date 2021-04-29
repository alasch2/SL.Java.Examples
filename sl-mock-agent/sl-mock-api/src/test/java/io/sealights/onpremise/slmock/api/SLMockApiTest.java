package io.sealights.onpremise.slmock.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import io.sealights.onpremise.slmock.internal.MockApiRequestToFile;
import io.sealights.onpremise.slmock.internal.SLMockConfiguration;

import static io.sealights.onpremise.slmock.TestConstants.BOOK_URL;
import static io.sealights.onpremise.slmock.TestConstants.REQUEST_BODY;
import static io.sealights.onpremise.slmock.TestConstants.RESPONSE_BODY;
import static io.sealights.onpremise.slmock.TestConstants.SERVICE;

import java.io.File;
import java.util.concurrent.TimeUnit;

class SLMockApiTest {

	private SLMockApi mockApiCall;
	
	@BeforeEach
	public void init() {
		mockApiCall = new SLMockApi();
	}

	@Test
	void test_givenCreatesTestSpecification() {
		TestSpecification testSpec1 = mockApiCall.given();
		assertNotNull(testSpec1);
		TestSpecification testSpec2 = mockApiCall.given();
		assertNotSame(testSpec1, testSpec2);
	}

	@Test
	void test_mockCreatesFile() {
		mockApiCall.when().service(SERVICE).get(BOOK_URL)
		.requestBodyAsJson(REQUEST_BODY)
		.thenReturn()
		.withDelay(10, TimeUnit.MINUTES)
		.responseBodyAsJson(RESPONSE_BODY)
		.responseStatus(504)
		.mock();
		
		assertTrue(new File(SLMockConfiguration.MOCK_REQUEST_PATH_DEFAULT).exists());
	}

}
