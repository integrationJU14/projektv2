package se.arole.datalayer.service;

import java.util.List;

import se.arole.datalayer.entity.TeamJPA;
import se.arole.datalayer.entity.User;

public interface TeamService {

	TeamJPA createTeam(TeamJPA team);

	TeamJPA updateTeam(TeamJPA team, int teamId);

	List<TeamJPA> getAllTeams();

	void addUserToTeam(User user, int teamId);

}
