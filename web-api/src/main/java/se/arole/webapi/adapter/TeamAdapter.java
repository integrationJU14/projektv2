package se.arole.webapi.adapter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import se.arole.api.resource.Team;
import se.arole.api.resource.User;

public class TeamAdapter implements JsonSerializer<Team>, JsonDeserializer<Team> {

	private final static JsonObjectMapper JSON_MAPPER = new JsonObjectMapper();

	@Override
	public Team deserialize(JsonElement json, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
		JsonObject jsonObject = json.getAsJsonObject();
		Integer teamId = jsonObject.get("teamId").getAsInt();
		String teamName = jsonObject.get("teamName").getAsString();

		List<User> teamUsers = new ArrayList<>();
		jsonObject.get("teamUsers").getAsJsonArray().forEach(tu -> {
			teamUsers.add(JSON_MAPPER.jsonToUser(tu));
		});

		return new Team(teamId, teamName, teamUsers);
	}

	@Override
	public JsonElement serialize(Team team, Type arg1, JsonSerializationContext arg2) {
		JsonObject json = new JsonObject();
		json.addProperty("teamId", team.getTeamId());
		json.addProperty("teamName", team.getTeamName());
		
		JsonArray usersJson = new JsonArray();
		team.getTeamUsers().forEach(u -> usersJson.add(JSON_MAPPER.userToJason(u)));
		json.add("teamUsers", usersJson);
		
		return json;
	}

}
