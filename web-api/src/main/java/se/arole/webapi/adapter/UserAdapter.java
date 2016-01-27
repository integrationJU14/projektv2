package se.arole.webapi.adapter;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import se.arole.api.resource.User;

public final class UserAdapter implements JsonSerializer<User>, JsonDeserializer<User> {

	private final static JsonObjectMapper MAPPER = new JsonObjectMapper();
	
	// JsonSerializer
	@Override
	public JsonElement serialize(User user, Type typeOfSrc, JsonSerializationContext context) {

		return MAPPER.userToJason(user);
	}

	// JsonDeserializer
	@Override
	public User deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {

//		JsonObject userJson = json.getAsJsonObject();
//		Integer id = userJson.get("userId").getAsInt();
//		String userName = userJson.get("userName").getAsString();
//		String firstName = userJson.get("firstName").getAsString();
//		String lastName = userJson.get("lastName").getAsString();
//
//		boolean isActive;
//
//		if (userJson.get("isActive").getAsString().equals("true"))
//			isActive = true;
//		else
//			isActive = false;

		return MAPPER.jsonToUser(json);
	}
}