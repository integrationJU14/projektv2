package se.arole.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import se.arole.api.adapter.TeamAdapter;
import se.arole.api.resource.Team;
import se.arole.datalayer.entity.TeamJPA;
import se.arole.datalayer.entity.User;
import se.arole.datalayer.service.TeamService;
import se.arole.datalayer.service.UserService;
import se.arole.datalayer.serviceImp.TeamServiceImpl;

public class TeamController {

	@Autowired
	private TeamService teamService;
	@Autowired
	private UserService userService;

	public TeamController(TeamService teamService) {
		this.teamService = teamService;
	}

	public List<Team> getAll() {
		List<Team> teams = new ArrayList<>();
		teamService.getAllTeams().forEach(t -> {
			teams.add(TeamAdapter.fromTeamDb(t));
		});
		return teams;
	}

	public Team add(Team team) {
		TeamJPA createTeam = teamService.createTeam(TeamAdapter.toTeamDb(team));
		return TeamAdapter.fromTeamDb(createTeam);
	}

	public Team getTeam(Integer teamId) {
		TeamJPA team = teamService.getTeam(teamId);
		return TeamAdapter.fromTeamDb(team);
	}

	public Team updateTeam(Integer teamId, Team team) {
		TeamJPA updateTeam = teamService.updateTeam(TeamAdapter.toTeamDb(team), teamId);
		return TeamAdapter.fromTeamDb(updateTeam);
	}

	public void addUser(Integer teamId, Integer userId) {
		User user = userService.getUser(userId);
		teamService.addUserToTeam(user, teamId);
	}
}
