package se.arole.api.adapter;

import java.util.List;

import se.arole.api.resource.Team;
import se.arole.api.resource.User;
import se.arole.datalayer.entity.TeamJPA;
import se.arole.datalayer.entity.UserJPA;;

public class TeamAdapter {

	public static TeamJPA toTeamDb(Team team) {
		String teamName = team.getTeamName();
		Integer teamId = team.getTeamId();
		List<UserJPA> teamMembers = UserAdapter.toDbUserList(team.getTeamUsers());
		return new TeamJPA(teamName, teamId, teamMembers);
	}

	public static Team fromTeamDb(TeamJPA teamJPA) {
		String teamName = teamJPA.getName();
		Integer teamId = teamJPA.getTeamId();
		List<User> teamMembers = UserAdapter.fromDbUserList(teamJPA.getUsers());
		return new Team(teamId, teamName, teamMembers);
	}

}
