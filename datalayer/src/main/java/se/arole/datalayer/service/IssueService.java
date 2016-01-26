package se.arole.datalayer.service;

import java.util.List;
import se.arole.datalayer.entity.Issue;
import se.arole.datalayer.entity.WorkItemJPA;

public interface IssueService {

	Issue createIssue(Issue issue);

	Issue updateIssue(Issue issue, int issueId);

	List<WorkItemJPA> workItemsWithIssues();

	void addIssueToWorkItem(WorkItemJPA workItemJPA, Issue issue);

}
