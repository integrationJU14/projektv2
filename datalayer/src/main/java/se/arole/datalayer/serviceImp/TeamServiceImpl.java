package se.arole.datalayer.serviceImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.arole.datalayer.entity.TeamJPA;
import se.arole.datalayer.entity.UserJPA;
import se.arole.datalayer.repository.TeamRepository;
import se.arole.datalayer.repository.UserRepository;
import se.arole.datalayer.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {

	@Autowired
	private TeamRepository teamRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public TeamJPA createTeam(TeamJPA team) {
		if (teamRepository.findByTeamId(team.getTeamId()) != null) {
			throw new IllegalArgumentException("TeamId already existing");
		}
		return teamRepository.save(team);
	}

	@Override
	public TeamJPA updateTeam(TeamJPA team, int teamId) {
		TeamJPA teamToUpdate = teamRepository.findByTeamId(teamId);
		teamToUpdate.setName(team.getName());
		teamToUpdate.setTeamId(team.getTeamId());

		List<UserJPA> users = team.getUsers();
		List<UserJPA> dbUsers = new ArrayList<>();
		users.forEach(user -> {
			dbUsers.add(userRepository.findByUserId(user.getUserId()));
		});

		teamToUpdate.setUsers(dbUsers);

		return teamRepository.save(teamToUpdate);
	}

	@Override
	public List<TeamJPA> getAllTeams() {

		return (List<TeamJPA>) teamRepository.findAll();
	}

	@Override
	public void addUserToTeam(UserJPA user, int teamId) {
		List<UserJPA> users = new ArrayList<UserJPA>();

		TeamJPA team = teamRepository.findByTeamId(teamId);

		if (team.getUsers() != null)
			users = team.getUsers();

		users.add(user);
		team.setUsers(users);
		teamRepository.save(team);

	}

	@Override
	public TeamJPA getTeam(int teamId) {
		return teamRepository.findByTeamId(teamId);
	}

	@Override
	public void deleteTeam(Integer teamId) {
		TeamJPA team = teamRepository.findByTeamId(teamId);

		teamRepository.delete(team);
	}

}
