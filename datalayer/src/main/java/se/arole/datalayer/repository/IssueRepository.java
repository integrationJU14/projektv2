package se.arole.datalayer.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import se.arole.datalayer.entity.Issue;

public interface IssueRepository extends PagingAndSortingRepository<Issue, Long> {
	Issue findByIssueId(Integer issueId);
}
