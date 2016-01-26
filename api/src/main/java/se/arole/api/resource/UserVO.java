package se.arole.api.resource;

import java.util.List;

public class UserVO {
	
	Integer userId; 
	boolean isActive;
	String userName, firstName, lastName;
	List<WorkItem> assignedWorkItems;
	
	public UserVO(Integer userId, boolean isActive, String userName, String firstName, String lastName){
		this.userId = userId;
		this.isActive = isActive;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		
	}

	public long getUserId() {
		return userId;
	}

	public UserVO setUserId(Integer userId) {
		this.userId = userId;
		
		return this;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
