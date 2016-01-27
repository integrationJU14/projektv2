package se.arole.webapi.adapter;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import se.arole.api.resource.UserVO;

public final class UserAdapter implements JsonSerializer<UserVO>, JsonDeserializer<UserVO> {

	// JsonSerializer
	@Override
	public JsonElement serialize(UserVO user, Type typeOfSrc, JsonSerializationContext context) {

		JsonObject json = new JsonObject();
		json.addProperty("userId", user.getUserId());
		json.addProperty("firstName", user.getFirstName());
		json.addProperty("lastName", user.getLastName());
		json.addProperty("userName", user.getUserName());
		json.addProperty("isActive", user.isActive());

		return json;
	}

	// JsonDeserializer
	@Override
	public UserVO deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {

		JsonObject userJson = json.getAsJsonObject();
		Integer id = userJson.get("userId").getAsInt();
		String userName = userJson.get("userName").getAsString();
		String firstName = userJson.get("firstName").getAsString();
		String lastName = userJson.get("lastName").getAsString();

		boolean isActive = userJson.get("isActive").getAsString() != null;

		return new UserVO(id, isActive, userName, firstName, lastName);
	}
}