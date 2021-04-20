package io.sealights.jira.webhook.research.service;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JiraApiService {
	
	private static final String ROOT = "https://sealightsdevelop.atlassian.net/rest/api/3";
	private static final String FIELD = "/field";
	private static final String USER = "ala.schneider@sealights.io";
	private static final String API_TOKEN = "Pe0ZwrV8Yd9oWE5xFGis68D0";
	private static final String AUTH_HEADER = "Authorization";
	private static final String TOKEN = "Basic YWxhLnNjaG5laWRlckBzZWFsaWdodHMuaW86UGUwWndyVjhZZDlvV0U1eEZHaXM2OEQw";
	
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Type LIST_TYPE = new TypeToken<List<Field>>() {}.getType();
    
    private final Executor executor;
    
	public JiraApiService() {
		executor = Executor.newInstance();
	}
	
	public Map<String, String> getCustomFieldIds() {
		try {
			String fieldsJson = executor.execute(Request.Get(ROOT + FIELD)
					.addHeader(AUTH_HEADER, TOKEN)
					)
					.returnContent().asString();
			log.debug("Recieved response:{}", fieldsJson);
			List<Field> fields = GSON.fromJson(fieldsJson, LIST_TYPE);
			Map<String, String> fieldsMap = fields.stream().filter(f->f.isCustom())
					.collect(Collectors.toMap(Field::getName, Field::getId));
			log.debug("Mapped fields:{}", fieldsMap);
			return fieldsMap;
		}
		catch(Exception e) {
			log.error("Failes to get fields scheme", e);
			return new HashMap<String,String>();
		}
	}
	
	protected void getIssueFields() {
		
	}
	
	@Data
	static class Field {
		private String id;
		private String name;
		private boolean custom;
	}

}
