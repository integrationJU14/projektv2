package se.arole.webapi.adapter;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import se.arole.api.resource.Issue;

public final class IssueAdapter implements JsonSerializer<Issue>, JsonDeserializer<Issue> {

	private final static JsonObjectMapper jsonMapper = new JsonObjectMapper();
	
	// JsonSerializer
	@Override
	public JsonElement serialize(Issue issue, Type typeOfSrc, JsonSerializationContext context) {

		return jsonMapper.issueToJason(issue);
		 
	}

	// JsonDeserializer
	@Override
	public Issue deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

		return jsonMapper.jasonToIssue(json);
		
	}
}