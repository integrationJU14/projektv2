package se.arole.api.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import se.arole.api.adapter.IssueAdapter;
import se.arole.api.resource.IssueVO;
import se.arole.api.resource.WorkItemVO;
import se.arole.datalayer.entity.Issue;
import se.arole.datalayer.entity.WorkItem;
import se.arole.datalayer.service.IssueService;

public final class IssueController {

	private IssueService issueService;

	public IssueController(IssueService issueService) {
		this.issueService = issueService;
	}

	public Collection<IssueVO> getAll() {
		return Collections.emptyList();
	}

	public IssueVO create(IssueVO issue) {
		Issue issueDb = IssueAdapter.toIssueDb(issue);
		Issue createdIssue = issueService.createIssue(issueDb);

		return IssueAdapter.fromIssueDb(createdIssue);
	}

	public IssueVO update(Integer id, IssueVO issue) {
		Issue issueDb = IssueAdapter.toIssueDb(issue);
		Issue updatedIssue = issueService.updateIssue(issueDb, id);

		return IssueAdapter.fromIssueDb(updatedIssue);
	}

	public List<WorkItem> workItemsWithIssues(){
		
		List<WorkItem> workItemList = issueService.workItemsWithIssues();
		List<Issue> issueList = new ArrayList<Issue>();
		
		//does this work?
		for(WorkItem workItem : workItemList){
			
			if(workItem.getIssue().isEmpty())
				workItemList.remove(workItem);
			
		}
		
		return workItemList;
	}

	public void addIssueToWorkItem(WorkItem workItem, Issue issue) {

		List<Issue> issueList = workItem.getIssue();

		issueList.add(issue);

		workItem.setIssue(issueList);
	}

	public IssueVO getIssue(Integer id) {
		List<WorkItem> workItemList = issueService.workItemsWithIssues();
		List<Issue> issueList = new ArrayList<Issue>();

		for (WorkItem workItem : workItemList) {

			for (Issue issue : workItem.getIssue()) {

				if (id == issue.getIssueId()) {

					// fix
					// IssueVO tempIssue = new IssueVO(issue.getIssueId(),
					// issue.getDescription(), issue.getHeader(),
					// issue.isSolved());

					return IssueAdapter.fromIssueDb(issue);
				}

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
