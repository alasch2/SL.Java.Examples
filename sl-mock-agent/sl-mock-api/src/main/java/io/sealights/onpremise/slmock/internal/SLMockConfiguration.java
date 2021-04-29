package io.sealights.onpremise.slmock.internal;

public class SLMockConfiguration {
	public static final String MOCK_REQUEST_PATH_DEFAULT = "mock-request.json";
	private static final String MOCK_REQUEST_FILEPATH_PROPERTY = "mock-path";
	
	public static String getRequestFilePath() {
		String value = System.getProperty(MOCK_REQUEST_FILEPATH_PROPERTY);
		if (value != null) {
			return value;
		}
		else {
			return MOCK_REQUEST_PATH_DEFAULT;
		}
	}
}
