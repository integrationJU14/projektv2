package se.arole.webapi.adapter;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import se.arole.api.resource.Issue;
import se.arole.api.resource.User;
import se.arole.api.resource.WorkItem;
import se.arole.datalayer.entity.Status;

public class JsonObjectMapper {

	public JsonElement userToJason(User user) {
		JsonObject json = new JsonObject();
		json.addProperty("userId", user.getUserId());
		json.addProperty("firstName", user.getFirstName());
		json.addProperty("lastName", user.getLastName());
		json.addProperty("userName", user.getUserName());
		json.addProperty("active", user.isActive());
		return json;
	}

	public User jsonToUser(JsonElement json) {
		JsonObject userJson = json.getAsJsonObject();
		Integer id = userJson.get("userId").getAsInt();
		String userName = userJson.get("userName").getAsString();
		String firstName = userJson.get("firstName").getAsString();
		String lastName = userJson.get("lastName").getAsString();

		boolean isActive = userJson.get("active").getAsString() != null;

		return new User(id, isActive, userName, firstName, lastName);
	}

	public JsonElement workItemVOToJson(WorkItem workItem) {

		JsonObject json = new JsonObject();
		json.addProperty("workItemId", workItem.getWorkItemId());
		json.addProperty("description", workItem.getDescription());
		json.addProperty("header", workItem.getHeader());

		User assignedUser = workItem.getAssignedUser();
		if (assignedUser != null) {
			json.add("assignedUser", userToJason(assignedUser));
		}

		JsonArray assignedIssues = new JsonArray();
		if (workItem.getAssignedIssues() != null) {
			workItem.getAssignedIssues().forEach(issue -> {
				assignedIssues.add(issueToJason(issue));
			});
		}
		json.addProperty("status", workItem.getStatus().toString());

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

	public WorkItem jsonToWorkItem(JsonElement json) {

		JsonObject workItemJson = json.getAsJsonObject();

		int workItemId = workItemJson.get("workItemId").getAsInt();
		String description = workItemJson.get("description").getAsString();
		String header = workItemJson.get("header").getAsString();

		JsonElement jsonElement = workItemJson.get("status");
		Status status = Status.TO_DO;
		if (jsonElement != null) {
			status = Status.fromString(jsonElement.getAsString());
		}

		List<Issue> assignedIssues = new ArrayList<>();
		JsonArray issuesJson = workItemJson.get("assignedIssues").getAsJsonArray();
		issuesJson.forEach(e -> {
			assignedIssues.add(jasonToIssue(e));
		});

		JsonElement json2 = workItemJson.get("assignedUser");
		if (json2 != null) {
			User assignedUser = jsonToUser(json2);
			return new WorkItem(workItemId, description, status, header, assignedUser, assignedIssues);
		}

		return new WorkItem(workItemId, description, status, header, assignedIssues);
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
