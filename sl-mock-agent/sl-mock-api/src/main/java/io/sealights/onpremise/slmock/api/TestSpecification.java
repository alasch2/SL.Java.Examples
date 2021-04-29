package io.sealights.onpremise.slmock.api;

import java.util.concurrent.TimeUnit;

public interface TestSpecification {
	TestSpecification service(String service);
	TestSpecification api(Method method, String path);
	TestSpecification get(String path);
	TestSpecification post(String path);
	TestSpecification put(String path);
	TestSpecification patch(String path);
	TestSpecification delete(String path);
	TestSpecification requestBodyAsJson(String bodyAsJson);
	TestSpecification responseStatus(int responseStatus);
	TestSpecification responseBodyAsJson(String bodyAsJson);
	TestSpecification withDelay(long delay, TimeUnit timeUnit);
	TestSpecification thenReturn();
	void mock();
	
	static enum Method {
		GET, POST, PUT, PATCH, DELETE
	}
}
