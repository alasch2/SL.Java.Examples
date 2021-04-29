package io.sealights.onpremise.slmock.internal;

import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.sealights.onpremise.slmock.api.SLMockApi;
import io.sealights.onpremise.slmock.api.TestSpecification;
import lombok.Data;

@Data 
public class TestSpecificationImpl implements TestSpecification {
	protected static long DEFAULT_NO_DELAY = -1; 
	protected static int DEFAULT_NO_STATUS = -1; 
	
	private String service;
	private String method;
	private String path;
	private String requestBody;
	private String responseBody;
	private int responseStatus = DEFAULT_NO_STATUS;
	private long delay = DEFAULT_NO_DELAY;
	private String delayUnit;
	@JsonIgnore
	private SLMockApi context;
	
	public TestSpecificationImpl(SLMockApi context) {
		this.context = context;
	}

	@Override
	public TestSpecification service(String service) {
		setService(service);
		return this;
	}

	@Override
	public TestSpecification api(Method method, String path) {
		applyParams(method, path);
		return this;
	}

	@Override
	public TestSpecification get(String path) {
		applyParams(Method.GET, path);
		return this;
	}

	@Override
	public TestSpecification post(String path) {
		applyParams(Method.POST, path);
		return this;
	}

	@Override
	public TestSpecification put(String path) {
		applyParams(Method.PUT, path);
		return this;
	}

	@Override
	public TestSpecification patch(String path) {
		applyParams(Method.PATCH, path);
		return this;
	}

	@Override
	public TestSpecification delete(String path) {
		applyParams(Method.DELETE, path);
		return this;
	}

	@Override
	public TestSpecification requestBodyAsJson(String bodyAsJson) {
		setRequestBody(bodyAsJson);
		return this;
	}

	@Override
	public TestSpecification responseStatus(int responseStatus) {
		setResponseStatus(responseStatus);
		return this;
	}

	@Override
	public TestSpecification responseBodyAsJson(String bodyAsJson) {
		setResponseBody(bodyAsJson);
		return this;
	}

	@Override
	public TestSpecification withDelay(long delay, TimeUnit unit) {
		setDelay(delay);
		setDelayUnit(unit.name().toLowerCase());
		return this;
	}

	@Override
	public TestSpecification thenReturn() {
		return this;
	}

	private void applyParams(Method method, String path) {
		this.method = method.toString().toLowerCase();
		this.path = path;
	}

	@Override
	public void mock() {
		context.mock(this);		
	}
	
}
