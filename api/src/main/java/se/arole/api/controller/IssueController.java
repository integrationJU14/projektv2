package se.arole.api.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import se.arole.api.adapter.IssueAdapter;
import se.arole.api.resource.Issue;
import se.arole.datalayer.entity.IssueJPA;
import se.arole.datalayer.entity.WorkItemJPA;
import se.arole.datalayer.service.IssueService;

public final class IssueController {

	@Autowired
	private IssueService issueService;

	public IssueController(IssueService issueService) {
		this.issueService = issueService;
	}

	public Collection<Issue> getAll() {

		List<IssueJPA> issueListTemp = new ArrayList<IssueJPA>();

		for (WorkItemJPA workItem : issueService.workItemsWithIssues()) {

			issueListTemp.addAll(workItem.getIssue());

		}

		List<Issue> issueList = IssueAdapter.fromDbIssueList(issueListTemp);

		return issueList;
	}

	public Issue create(Issue issue) {
		IssueJPA issueDb = IssueAdapter.toIssueDb(issue);
		IssueJPA createdIssue = issueService.createIssue(issueDb);

		return IssueAdapter.fromIssueDb(createdIssue);
	}

	public Issue update(Integer id, Issue issue) {
		IssueJPA issueDb = IssueAdapter.toIssueDb(issue);
		IssueJPA updatedIssue = issueService.updateIssue(issueDb, id);

		return IssueAdapter.fromIssueDb(updatedIssue);
	}

	public void addIssueToWorkItem(Integer workId, Integer issueId) {
		issueService.addIssueToWorkItem(workId, issueId);
	}

	public Issue getIssue(Integer id) {
		IssueJPA issue = issueService.getIssue(id);
		return IssueAdapter.fromIssueDb(issue);
	}
}
