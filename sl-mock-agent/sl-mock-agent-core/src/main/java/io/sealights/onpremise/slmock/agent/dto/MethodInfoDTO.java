package io.sealights.onpremise.slmock.agent.dto;
import lombok.Data;

@Data
public class MethodInfoDTO {
	private String methodName;
	private String className;

	public MethodInfoDTO(String className, String methodName){
		this.className = className;
		this.methodName = methodName;
	}
}


