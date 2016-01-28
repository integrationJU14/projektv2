package se.arole.api.adapter;

import java.util.ArrayList;
import java.util.List;

import se.arole.api.resource.Issue;
import se.arole.api.resource.User;
import se.arole.api.resource.WorkItem;
import se.arole.datalayer.entity.IssueJPA;
import se.arole.datalayer.entity.Status;
import se.arole.datalayer.entity.UserJPA;
import se.arole.datalayer.entity.WorkItemJPA;

public class WorkItemAdapter {

	public static WorkItemJPA toWorkItemDb(WorkItem workItem) {
		// TODO issue ???
		Integer itemId = (int) workItem.getWorkItemId();
		String description = workItem.getDescription();
		String status = workItem.getStatus().toString();
		List<IssueJPA> issue = new ArrayList<IssueJPA>();
		if (workItem.getAssignedUser() != null) {
			UserJPA solver = UserAdapter.toUserDb(workItem.getAssignedUser());
			return new WorkItemJPA(itemId, description, status, solver, issue);
		}
		return new WorkItemJPA(itemId, description, status, issue);
	}

	public static WorkItem fromWorkItemDb(WorkItemJPA workItemJPA) {
		// TODO users & ussues ???
		int workItemId = workItemJPA.getItemId();
		String description = workItemJPA.getDescription();
		Status status = Status.fromString(workItemJPA.getStatus());
		List<Issue> assignedIssues = null;
		User assignedUser = new User(0, false, "", "", "");
		if (workItemJPA.getSolver() != null) {
			assignedUser = UserAdapter.fromUserDb(workItemJPA.getSolver());
			return new WorkItem(workItemId, description, status, assignedUser, assignedIssues);
		}
		return new WorkItem(workItemId, description, status, assignedIssues);
	}

	public static List<WorkItemJPA> toDbWorkList(List<WorkItem> workItemVOList) {
		List<WorkItemJPA> workItemListToDb = new ArrayList<WorkItemJPA>();
		workItemVOList.forEach(e -> {
			workItemListToDb.add(toWorkItemDb(e));
		});
		return workItemListToDb;
	}

	public static List<WorkItem> fromDbWorkList(List<WorkItemJPA> workItemJPAList) {
		List<WorkItem> workListFromDb = new ArrayList<WorkItem>();
		workItemJPAList.forEach(u -> {
			workListFromDb.add(fromWorkItemDb(u));
		});
		return workListFromDb;
	}
}
