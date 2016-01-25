package se.arole.api.resource;

import java.util.List;

public class TeamVO {
	
	long teamId;
	String teamName;
	List<UserVO> teamUsers;
	
	public TeamVO(long teamId, String teamName) {
		this.teamId = teamId;
		this.teamName = teamName;
	}

}
