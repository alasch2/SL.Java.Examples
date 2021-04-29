package io.sealights.onpremise.slmock.agent.dto;

import java.util.HashMap;
import java.util.Map;

//import io.sealights.onpremise.agents.apianalytics.footprints.APAMethodFootprint;
//import io.sealights.onpremise.agents.apianalytics.footprints.APAMethodFootprint.RouteInfo;
//import io.sealights.onpremise.agents.infra.utils.ToStringFormatter;
import lombok.Data;

@Data
public class ClassDiscoveryDTO {
	
	private String className;
	private Class<?> classToDiscover;
	// Map of methods->routes; method is a unique method descriptor
	private Map<String, RouteInfoDTO> routes;
	
	public ClassDiscoveryDTO() {
	}

	public ClassDiscoveryDTO(Class<?> classToDiscover) {
		this.classToDiscover = classToDiscover;
		this.className = classToDiscover.getName();
	}

	public void addRoute(String methodId, String methodName, String path, String httpMethod) {
		initRoutes();
		routes.put(methodId, new RouteInfoDTO(httpMethod, path, methodName));
	}
	
	public boolean isEmpty() {
		return routes==null || routes.isEmpty();
	}
	

	protected void initRoutes() {
		if (routes == null) {
			routes = new HashMap<String, RouteInfoDTO>();
		}
	}

	@Override
	public String toString() {
		String apiRoutesInfo = "";
		if (routes != null) {
			for (String key: routes.keySet()) {
				apiRoutesInfo += String.format("'%s':'%s', ", key, routes.get(key));
			}
		}
		return String.format("className='%s', apiRoutesInfo:%s", className, apiRoutesInfo);
	}
}
