package se.arole.datalayer.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import se.arole.datalayer.entity.TeamJPA;

public interface TeamRepository extends PagingAndSortingRepository<TeamJPA, Long> {
	TeamJPA findByTeamId(Integer teamId);
}
