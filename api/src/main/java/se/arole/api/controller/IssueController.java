package se.arole.api.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import se.arole.api.adapter.IssueAdapter;
import se.arole.api.adapter.UserAdapter;
import se.arole.api.resource.Issue;
import se.arole.api.resource.WorkItem;
import se.arole.datalayer.entity.IssueJPA;
import se.arole.datalayer.entity.WorkItemJPA;
import se.arole.datalayer.service.IssueService;

public final class IssueController {

	private IssueService issueService;

	public IssueController(IssueService issueService) {
		this.issueService = issueService;
	}

	public Collection<Issue> getAll() {
		
		List<WorkItemJPA> workItemList = issueService.workItemsWithIssues();
		List<IssueJPA> issueListTemp = new ArrayList<IssueJPA>();
		
		for(WorkItemJPA workItem : workItemList){
			
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

	public List<WorkItemJPA> workItemsWithIssues(){
		
		List<WorkItemJPA> workItemList = issueService.workItemsWithIssues();
		List<Issue> issueList = new ArrayList<Issue>();
		
		//does this work?
		for(WorkItemJPA workItem : workItemList){
			
			if(workItem.getIssue().isEmpty())
				workItemList.remove(workItem);
			
		}
		
		return workItemList;
	}

	public void addIssueToWorkItem(WorkItemJPA workItem, IssueJPA issue) {

		List<IssueJPA> issueList = workItem.getIssue();

		issueList.add(issue);

		workItem.setIssue(issueList);
	}

	public Issue getIssue(Integer id) {
		List<WorkItemJPA> workItemList = issueService.workItemsWithIssues();
		List<IssueJPA> issueList = new ArrayList<IssueJPA>();

		for (WorkItemJPA workItem : workItemList) {

			for (IssueJPA issue : workItem.getIssue()) {

				if (id == issue.getIssueId())
					return IssueAdapter.fromIssueDb(issue);

			}

		}
		// fix later
		return null;
	}
	/*
	 * public IssueVO getIssue(String issueName) { Issue issueByIssuename =
	 * issueService.getIssueByIssuename(issueName);
	 * 
	 * return IssueAdapter.fromIssueDb(issueByIssuename); }
	 */
}
