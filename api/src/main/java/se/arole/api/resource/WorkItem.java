package se.arole.api.resource;

import java.util.List;

import se.arole.datalayer.entity.Status;

public class WorkItem {

	private int workItemId;
	private String description;
	private List<Issue> assignedIssues;
	private User assignedUser;
	private Status status;

	public WorkItem(Integer workItemId, String decsription, Status status, User assignedUser,
			List<Issue> issues) {
		this.workItemId = workItemId;
		this.description = decsription;
		this.assignedUser = assignedUser;
		this.assignedIssues = issues;
		this.status = status;
	}

	public WorkItem(int workItemId, String description, Status status, List<Issue> assignedIssues) {
		this.workItemId = workItemId;
		this.description = description;
		this.assignedIssues = assignedIssues;
		this.status = status;
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

	public List<Issue> getAssignedIssues() {
		return assignedIssues;
	}

	public void setAssignedIssues(List<Issue> assignedIssues) {
		this.assignedIssues = assignedIssues;
	}

	public User getAssignedUser() {
		return assignedUser;
	}

	public Status getStatus() {
		return status;
	}

	public void setAssignedUser(User assignedUser) {
		this.assignedUser = assignedUser;
	}

}
