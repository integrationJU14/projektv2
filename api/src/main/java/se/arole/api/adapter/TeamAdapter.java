package se.arole.api.adapter;

import java.util.List;

import se.arole.api.resource.Team;
import se.arole.api.resource.UserVO;
import se.arole.datalayer.entity.TeamJPA;
import se.arole.datalayer.entity.User;
import se.arole.api.adapter.UserAdapter;;

public class TeamAdapter {
	
	public TeamJPA toTeamDb(Team team){
		
		String teamName = team.getTeamName();
		Integer teamId = team.getTeamId();
		List <User> teamMembers = UserAdapter.toDbUserList(team.getTeamUsers());
		return new TeamJPA(teamName,teamId,teamMembers);
		}
	
	
	public Team fromTeamDb(TeamJPA teamJPA){
		
		String teamName = teamJPA.getName();
		Integer teamId = teamJPA.getTeamId();
		List <UserVO> teamMembers = UserAdapter.fromDbUserList(teamJPA.getUsers());
		return new Team(teamId, teamName, teamMembers);
		}
}