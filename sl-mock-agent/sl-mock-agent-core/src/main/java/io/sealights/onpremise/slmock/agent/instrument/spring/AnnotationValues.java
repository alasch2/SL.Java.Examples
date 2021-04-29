package io.sealights.onpremise.slmock.agent.instrument.spring;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnnotationValues {
	public static final String DEFAULT_METHOD = "GET";
	
	String path = null;
	String method = null;
	
	public AnnotationValues(String path) {
		this.path = path;
	}
	
	@Override
	public String toString() {
		return String.format("AnnotationValues [path=%s, method=%s]", path, method);
	}
}