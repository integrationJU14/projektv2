package se.arole.datalayer.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class TeamJPA {
	@Id
	@GeneratedValue
	private Long id;
	@Column
	private String name;
	@Column(unique =true)
	private Integer teamId;
	@OneToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	private List <User> users;

	public TeamJPA() {

	}

	public TeamJPA(String name, Integer teamId) {
		super();
		this.name = name;
		this.teamId = teamId;

	}

	public TeamJPA(String name, Integer teamId, List<User> users) {
		super();
		this.name = name;
		this.teamId = teamId;
		this.users = users;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}