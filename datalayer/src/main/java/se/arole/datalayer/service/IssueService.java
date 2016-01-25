package se.arole.datalayer.service;

import java.util.List;
import se.arole.datalayer.entity.Issue;
import se.arole.datalayer.entity.WorkItem;

public interface IssueService {

	Issue createIssue(Issue issue);

	Issue updateIssue(Issue issue, int issueId);

	List<WorkItem> workItemsWithIssues();

	void addIssueToWorkItem(WorkItem workItem, Issue issue);

}
