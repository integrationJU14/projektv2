package se.arole.datalayer.serviceImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.arole.datalayer.entity.Team;
import se.arole.datalayer.entity.User;
import se.arole.datalayer.repository.IssueRepository;
import se.arole.datalayer.repository.TeamRepository;
import se.arole.datalayer.repository.WorkItemRepository;
import se.arole.datalayer.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {

	
	@Autowired
	private TeamRepository teamRepository;

	@Override
	public Team createTeam(Team team) {

		return teamRepository.save(team);
	}

	@Override
	public Team updateTeam(Team team, int teamId) {
		Team teamToUpdate = teamRepository.findByTeamId(teamId);
		teamToUpdate.setName(team.getName());
		teamToUpdate.setTeamId(team.getTeamId());
		teamToUpdate.setUsers(team.getUsers());
		
		return teamRepository.save(teamToUpdate);
	}

	@Override
	public List<Team> getAllTeams() {

		return (List<Team>) teamRepository.findAll();
	}

	@Override
	public void addUserToTeam(User user, int teamId) {
		List<User> users;

		Team team = teamRepository.findByTeamId(teamId);

		if (team.getUsers() != null)
			users = team.getUsers();
		else
			users = new ArrayList<User>();

		users.add(user);
		team.setUsers(users);
		teamRepository.save(team);

	}

}
