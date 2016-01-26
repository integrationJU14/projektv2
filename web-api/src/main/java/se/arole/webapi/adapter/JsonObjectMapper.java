package se.arole.webapi.adapter;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import se.arole.api.resource.Issue;
import se.arole.api.resource.UserVO;
import se.arole.api.resource.WorkItem;

public class JsonObjectMapper {

	public JsonElement userToJason(UserVO userVO) {

		JsonObject jsonUser = new JsonObject();
		jsonUser.addProperty("id", userVO.getUserId());
		jsonUser.addProperty("username", userVO.getUserName());
		jsonUser.addProperty("isActive", userVO.isActive());

		return jsonUser;

	}

	public UserVO jasonToUser(JsonElement json) {
		return null;

	}

	public JsonElement workItemVOToJson(WorkItem workItemVO) {

		JsonObject json = new JsonObject();
		json.addProperty("workItemId", workItemVO.getWorkItemId());
		json.addProperty("description", workItemVO.getDescription());
		json.addProperty("header", workItemVO.getHeader());

		UserVO assignedUser = workItemVO.getAssignedUser();

		json.add("assignedUser", userToJason(assignedUser));

		JsonArray users = new JsonArray();
		workItemVO.getUsers().forEach(user -> {
			users.add(userToJason(user));
		});

		json.add("users", users);

		JsonArray assignedIssues = new JsonArray();
		workItemVO.getAssignedIssues().forEach(issue -> {
			assignedIssues.add(issueToJason(issue));
		});

		json.add("users", users);

		return json;

	}

	public JsonElement issueToJason(Issue issue) {
		JsonObject jsonIssue = new JsonObject();
		jsonIssue.addProperty("issueId", issue.getIssueId());
		jsonIssue.addProperty("description", issue.getDescription());
		jsonIssue.addProperty("header", issue.getHeader());
		jsonIssue.addProperty("isSolved", issue.isSolved());

		return jsonIssue;
	}
 
	public WorkItem jsonToWorkItemVO(JsonElement json) {

		JsonObject workItemJson = json.getAsJsonObject();
		int workItemId = workItemJson.get("workItemId").getAsInt();
		String description = workItemJson.get("description").getAsString();
		String header = workItemJson.get("header").getAsString();
		List<UserVO> usersVO = new ArrayList<>();
		List<Issue> assignedIssues = new ArrayList<>();
		UserVO assignedUser = jasonToUser(workItemJson.get("assignedUser"));

		JsonArray usersJson = workItemJson.get("users").getAsJsonArray();
		usersJson.forEach(e -> {

			UserVO userVO = jasonToUser(e.getAsJsonObject());
			usersVO.add(userVO);
		});

		JsonArray issuesJson = workItemJson.get("assignedIssues").getAsJsonArray();
		issuesJson.forEach(e -> {
			assignedIssues.add(jasonToIssue(e));
		});

		return new WorkItem(workItemId, description, header, assignedUser, usersVO, assignedIssues);

	}

	public Issue jasonToIssue(JsonElement e) {
		
		JsonObject issueJson = e.getAsJsonObject();
		int issueId = issueJson.get("issueId").getAsInt();
		String description = issueJson.get("description").getAsString();
		String header = issueJson.get("header").getAsString();

		boolean isSolved = issueJson.get("isSolved").getAsBoolean();

		return new Issue(issueId, description, header, isSolved);
	}
}