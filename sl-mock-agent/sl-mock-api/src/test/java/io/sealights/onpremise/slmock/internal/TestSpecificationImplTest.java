package io.sealights.onpremise.slmock.internal;

import static io.sealights.onpremise.slmock.TestConstants.BOOK_URL;
import static io.sealights.onpremise.slmock.TestConstants.REQUEST_BODY;
import static io.sealights.onpremise.slmock.TestConstants.RESPONSE_BODY;
import static io.sealights.onpremise.slmock.TestConstants.SERVICE;
import static io.sealights.onpremise.slmock.internal.TestSpecificationImpl.DEFAULT_NO_DELAY;
import static io.sealights.onpremise.slmock.internal.TestSpecificationImpl.DEFAULT_NO_STATUS;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import io.sealights.onpremise.slmock.api.SLMockApi;

public class TestSpecificationImplTest {
	
	private TestSpecificationImpl testSpecification;
	
	@BeforeEach
	public void init() {
		testSpecification = new TestSpecificationImpl(mock(SLMockApi.class));
	}

	@Test
	void test_thenReturn_returnsSameObject() {
		assertSame(testSpecification, testSpecification.thenReturn());
	}

	@Test
	void test_chain_calls_no_values() {
		testSpecification.thenReturn();
		assertNull(testSpecification.getService());
		assertNull(testSpecification.getPath());
		assertEquals(DEFAULT_NO_STATUS, testSpecification.getResponseStatus());
		assertEquals(DEFAULT_NO_DELAY, testSpecification.getDelay());
		assertNull(testSpecification.getDelayUnit());
		assertNull(testSpecification.getRequestBody());
		assertNull(testSpecification.getResponseBody());
	}

	@Test
	void test_chain_calls_with_values() {
		testSpecification.service(SERVICE).get(BOOK_URL)
			.requestBodyAsJson(REQUEST_BODY)
			.thenReturn()
			.withDelay(10, TimeUnit.MINUTES)
			.responseBodyAsJson(RESPONSE_BODY)
			.responseStatus(504);
		assertEquals(SERVICE, testSpecification.getService());
		assertEquals(BOOK_URL, testSpecification.getPath());
		assertEquals(504, testSpecification.getResponseStatus());
		assertEquals(10, testSpecification.getDelay());
		assertEquals(TimeUnit.MINUTES.name(), testSpecification.getDelayUnit().toUpperCase());
		assertEquals(REQUEST_BODY, testSpecification.getRequestBody());
		assertEquals(RESPONSE_BODY, testSpecification.getResponseBody());
	}

}
