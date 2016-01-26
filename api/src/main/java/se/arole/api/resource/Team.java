package se.arole.api.resource;

import java.util.List;

public class Team {
	
	private Integer teamId;
	private String teamName;
	private List<UserVO> teamUsers;
	
	public Team(Integer teamId, String teamName) {
		this.teamId = teamId;
		this.teamName = teamName;
	}
	
	public Team(Integer teamId, String teamName, List<UserVO> members) {
		this.teamId = teamId;
		this.teamName = teamName;
		this.teamUsers = members;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public List<UserVO> getTeamUsers() {
		return teamUsers;
	}
	
	

}
