package io.sealights.onpremise.slmock.agent.instrument.spring;
public class RouteBuilder {
	
	private static final String SLASH = "/";
	
	public static String buildRoute(AnnotationValues classValues, AnnotationValues methodValues) {
		String rootPath = null;
		String methodPath = null;
		
		if (classValues != null) {
			rootPath = classValues.getPath();
		}
		
		if (methodValues != null) {
			methodPath = methodValues.getPath();
		}
		
		return buildRoute(rootPath, methodPath);
	}

	private static String buildRoute(String rootPath, String methodPath) {
		StringBuilder fullPath = new StringBuilder();
		if (rootPath != null && !rootPath.isEmpty() && !rootPath.equals(SLASH)) {
			appendWithHeadingSlash(fullPath, removeEndingSlash(rootPath));
		}

		if ((methodPath != null) && (!methodPath.isEmpty())) {
			appendWithHeadingSlash(fullPath, methodPath);
		}

		return removeEndingSlash(fullPath.toString());
	}

	private static void appendWithHeadingSlash(StringBuilder fullPath, String path) {
		if (!path.startsWith(SLASH)) {
			fullPath.append(SLASH).append(path);
		}
		else {
			fullPath.append(path);
		}
	}
	
	private static String removeEndingSlash(String path) {
		if (path != null && path.length() > 1) {
			if (path.endsWith(SLASH)) {
				return path.substring(0, path.length() - 1);
			}
		}
		return path;
	}
	
}