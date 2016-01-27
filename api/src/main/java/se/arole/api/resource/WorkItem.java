package se.arole.api.resource;

import java.util.List;

public class WorkItem {
	
	int workItemId;
	String description, header;
	List<User> users;
	List<Issue> assignedIssues;
	User assignedUser;
	
	public WorkItem(Integer workItemId, String decsription, String header, User assignedUser, List<User> users, List<Issue> issues){
		this.workItemId = workItemId;
		this.description = decsription;
		this.header = header;
		this.assignedUser = assignedUser;
		this.users = users;
		this.assignedIssues = issues;
		
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
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
