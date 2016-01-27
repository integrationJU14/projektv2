package se.arole.datalayer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Holstad
 *
 */
@Entity
@Table(name="users")
public class UserJPA {
	
	@Id
	@GeneratedValue
	private Long id;
	@Column
	private String userName;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private Integer userId;
	@Column
	private boolean isActive;
	
	
	
public UserJPA() {}
	
	public UserJPA(String userName, String firstName, String lastName, Integer userId, boolean isActive) {
		super();
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userId = userId;
		this.isActive = isActive;
	}
	public Integer getUserId() {
		return userId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public Long getId() {
		return id;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	@Override
	public String toString() {
		return "User [userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName + ", userId="
				+ userId + ", isActive=" + isActive + "]";

	}
	
}