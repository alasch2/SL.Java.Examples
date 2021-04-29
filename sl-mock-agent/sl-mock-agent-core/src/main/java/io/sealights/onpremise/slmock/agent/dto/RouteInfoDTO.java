package io.sealights.onpremise.slmock.agent.dto;
import lombok.Data;

@Data
public class RouteInfoDTO {
	private String httpMethod;
	private String path;
	private String methodName;

	public RouteInfoDTO(String httpMethod, String path, String methodName){
		this.httpMethod = httpMethod;
		this.path = path;
		this.methodName = methodName;
	}
}


