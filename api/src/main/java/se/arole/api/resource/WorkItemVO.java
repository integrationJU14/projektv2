package se.arole.api.resource;

import java.util.List;

import se.arole.datalayer.entity.Issue;

public class WorkItemVO {
	
	int workItemId;
	String description, header;
	List<UserVO> users;
	List<Issue> assignedIssues;
	UserVO assignedUser;
	
	public WorkItemVO(Integer workItemId, String decsription, String header, UserVO assignedUser, List<UserVO> users, List<Issue> issues){
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

	public List<UserVO> getUsers() {
		return users;
	}

	public void setUsers(List<UserVO> users) {
		this.users = users;
	}

	public List<Issue> getAssignedIssues() {
		return assignedIssues;
	}

	public void setAssignedIssues(List<Issue> assignedIssues) {
		this.assignedIssues = assignedIssues;
	}

	public UserVO getAssignedUser() {
		return assignedUser;
	}

	public void setAssignedUser(UserVO assignedUser) {
		this.assignedUser = assignedUser;
	}
	

}
