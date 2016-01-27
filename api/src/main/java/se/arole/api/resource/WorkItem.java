package se.arole.api.resource;

import java.util.List;

public class WorkItem {

	int workItemId;
	String description, header;
	List<Issue> assignedIssues;
	User assignedUser;

	public WorkItem(Integer workItemId, String decsription, String header, User assignedUser, List<Issue> issues) {
		this.workItemId = workItemId;
		this.description = decsription;
		this.header = header;
		this.assignedUser = assignedUser;
		this.assignedIssues = issues;

	}

	public WorkItem(int workItemId, String description, String header, List<Issue> assignedIssues) {
		this.workItemId = workItemId;
		this.description = description;
		this.header = header;
		this.assignedIssues = assignedIssues;
	}

	public long getWorkItemId() {
		return workItemId;
	}

	public void setWorkItemId(Integer workItemId) {
		this.workItemId = workItemId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public List<Issue> getAssignedIssues() {
		return assignedIssues;
	}

	public void setAssignedIssues(List<Issue> assignedIssues) {
		this.assignedIssues = assignedIssues;
	}

	public User getAssignedUser() {
		return assignedUser;
	}

	public void setAssignedUser(User assignedUser) {
		this.assignedUser = assignedUser;
	}

}
