package se.arole.webapi.adapter;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import se.arole.api.resource.Issue;
import se.arole.api.resource.User;
import se.arole.api.resource.WorkItem;

public class JsonObjectMapper {

	public JsonElement userToJason(User user) {
		JsonObject json = new JsonObject();
		json.addProperty("userId", user.getUserId());
		json.addProperty("firstName", user.getFirstName());
		json.addProperty("lastName", user.getLastName());
		json.addProperty("userName", user.getUserName());
		json.addProperty("isActive", user.isActive());
		return json;
	}

	public User jsonToUser(JsonElement json) {
		JsonObject userJson = json.getAsJsonObject();
		Integer id = userJson.get("userId").getAsInt();
		String userName = userJson.get("userName").getAsString();
		String firstName = userJson.get("firstName").getAsString();
		String lastName = userJson.get("lastName").getAsString();

		boolean isActive = userJson.get("isActive").getAsString() != null;

		return new User(id, isActive, userName, firstName, lastName);
	}

	public JsonElement workItemVOToJson(WorkItem workItemVO) {

		JsonObject json = new JsonObject();
		json.addProperty("workItemId", workItemVO.getWorkItemId());
		json.addProperty("description", workItemVO.getDescription());
		json.addProperty("header", workItemVO.getHeader());

		User assignedUser = workItemVO.getAssignedUser();

		json.add("assignedUser", userToJason(assignedUser));

		JsonArray assignedIssues = new JsonArray();
		workItemVO.getAssignedIssues().forEach(issue -> {
			assignedIssues.add(issueToJason(issue));
		});

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
		User assignedUser = jsonToUser(workItemJson.get("assignedUser"));

		List<Issue> assignedIssues = new ArrayList<>();
		JsonArray issuesJson = workItemJson.get("assignedIssues").getAsJsonArray();
		issuesJson.forEach(e -> {
			assignedIssues.add(jasonToIssue(e));
		});

		return new WorkItem(workItemId, description, header, assignedUser, assignedIssues);
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
