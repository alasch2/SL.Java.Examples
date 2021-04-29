package io.sealights.onpremise.slmock.agent.instrument;

public final class EndpointAnnotationDictionary {
	
	public static final String REQUEST_MAPPING = "RequestMapping";
	public static final String GET_MAPPING = "GetMapping";
	public static final String REQUEST_MAPPING_PATH = "value";
	public static final String REQUEST_MAPPING_METHOD = "method";
	
	public static boolean isSupportedAnnotation(String desc) {
		if (desc.indexOf(REQUEST_MAPPING) != -1 || desc.indexOf(GET_MAPPING) != -1) {
			return true;
		}
		return  false;
	}

	private EndpointAnnotationDictionary() {
	}

}
