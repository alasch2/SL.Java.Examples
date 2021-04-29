package io.sealights.onpremise.slmock.api;

import io.sealights.onpremise.slmock.internal.MockApiRequestToFile;
import io.sealights.onpremise.slmock.internal.TestSpecificationImpl;

public class SLMockApi {
	private MockApiRequestToFile requestService;
	
	public SLMockApi() {
		this.requestService = new MockApiRequestToFile();
	}
	
	protected SLMockApi(MockApiRequestToFile requestService) {
		this.requestService = requestService;
	}
	
	public TestSpecification given() {
		return createTestSpecification();
	}
	
	public TestSpecification when() {
		return createTestSpecification();
	}
	
	public void mock(TestSpecification testSpecification) {
		requestService.sendMockRequest(testSpecification);
	}
	
	private TestSpecification createTestSpecification() {
		return new TestSpecificationImpl(this);
	}
	
}
