package se.arole.webapi.adapter;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import se.arole.api.resource.WorkItem;

public final class WorkItemAdapter implements JsonSerializer<WorkItem>, JsonDeserializer<WorkItem> {

	private static final JsonObjectMapper mapper = new JsonObjectMapper();

	// JsonSerializer
	@Override
	public JsonElement serialize(WorkItem workItem, Type typeOfSrc, JsonSerializationContext context) {
		return mapper.workItemVOToJson(workItem);

	}

	// JsonDeserializer
	@Override
	public WorkItem deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {

		return mapper.jsonToWorkItem(json);

	}
}