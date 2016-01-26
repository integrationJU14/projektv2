package se.arole.webapi.adapter;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import se.arole.api.resource.WorkItemVO;


public final class WorkItemAdapter 
implements JsonSerializer<WorkItemVO>, JsonDeserializer<WorkItemVO> {
	
	JsonObjectMapper mapper;

	// JsonSerializer
	@Override
	public JsonElement serialize(WorkItemVO workItemVO, Type typeOfSrc, JsonSerializationContext context) {
		return	mapper.workItemVOToJson(workItemVO);	
		 
	}

	// JsonDeserializer
	@Override
	public WorkItemVO deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {

		return mapper.jsonToWorkItemVO(json);

		
	}
}