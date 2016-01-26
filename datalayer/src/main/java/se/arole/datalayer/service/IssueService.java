package se.arole.datalayer.service;

import java.util.List;
import se.arole.datalayer.entity.IssueJPA;
import se.arole.datalayer.entity.WorkItemJPA;

public interface IssueService {

	IssueJPA createIssue(IssueJPA issue);

	IssueJPA updateIssue(IssueJPA issue, int issueId);

	List<WorkItemJPA> workItemsWithIssues();

	void addIssueToWorkItem(WorkItemJPA workItemJPA, IssueJPA issue);

}
