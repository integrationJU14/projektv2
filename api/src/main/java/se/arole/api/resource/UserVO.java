package se.arole.api.resource;

import java.util.List;

public class UserVO {
	
	long userId; 
	boolean isActive;
	String userName, firstName, lastName;
	List<WorkItemVO> assignedWorkItems;
	
	public UserVO(long userId, boolean isActive, String userName, String firstName, String lastName){
		this.userId = userId;
		this.isActive = isActive;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		
	}

	public long getUserId() {
		return userId;
	}

	public UserVO setUserId(long userId) {
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
