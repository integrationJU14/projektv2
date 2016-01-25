package se.arole.datalayer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	@Column
	private String name;
	@Column
	private Integer userId;
	@Column
	private boolean isActive;
	
	
	
	public User() {
		
	}

	public User(String name, Integer userId, boolean isActive) {
		super();
		this.name = name;
		this.userId = userId;
		this.isActive = isActive;
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	public String getName() {
		return name;
	}
	
}