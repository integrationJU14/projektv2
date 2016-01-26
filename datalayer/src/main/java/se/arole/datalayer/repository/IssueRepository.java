package se.arole.datalayer.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import se.arole.datalayer.entity.IssueJPA;

public interface IssueRepository extends PagingAndSortingRepository<IssueJPA, Long> {
	IssueJPA findByIssueId(Integer issueId);
}
