package io.sealights.onpremise.slmock.internal;

import java.io.BufferedWriter;
import java.io.FileWriter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.sealights.onpremise.slmock.api.MockApiRequestService;
import io.sealights.onpremise.slmock.api.TestSpecification;
import lombok.SneakyThrows;

public class MockApiRequestToFile implements MockApiRequestService {
	
	@Override
	public boolean sendMockRequest(TestSpecification testSpecification) {
		try {
			String json = new ObjectMapper().writeValueAsString(testSpecification);
			createFile(SLMockConfiguration.getRequestFilePath(), json);
			return true;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SneakyThrows
	protected void createFile(String path, String content) {
		try (
				FileWriter fw = new FileWriter(path); 
				BufferedWriter bw = new BufferedWriter(fw)
				) {
			bw.write(content);
		}
	}
}
