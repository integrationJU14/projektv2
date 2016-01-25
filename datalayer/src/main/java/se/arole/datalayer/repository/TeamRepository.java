package se.arole.datalayer.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import se.arole.datalayer.entity.Team;

public interface TeamRepository extends PagingAndSortingRepository<Team, Long> {
	Team findByTeamId(Integer teamId);
}
