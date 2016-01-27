package se.arole.api.adapter;

import java.util.ArrayList;
import java.util.List;

import se.arole.api.resource.Issue;
import se.arole.api.resource.UserVO;
import se.arole.api.resource.WorkItem;
import se.arole.datalayer.entity.IssueJPA;
import se.arole.datalayer.entity.User;
import se.arole.datalayer.entity.WorkItemJPA;

public class WorkItemAdapter {

	public static WorkItemJPA toWorkItemDb(WorkItem workItem) {
		// TODO status & issue ???
		Integer itemId = (int) workItem.getWorkItemId();
		String description = workItem.getDescription();
		String status = "";
		User solver = new User("", 0, false);
		List<IssueJPA> issue = new ArrayList<IssueJPA>();

		if (workItem.getAssignedUser() != null)
			solver = UserAdapter.toUserDb(workItem.getAssignedUser());

		return new WorkItemJPA(itemId, description, status, solver, issue);
	}

	public static WorkItem fromWorkItemDb(WorkItemJPA workItemJPA) {
		// TODO header & users & ussues ???
		int workItemId = workItemJPA.getItemId();
		String description = workItemJPA.getDescription();
		String header = "";
		List<UserVO> users = new ArrayList<UserVO>();
		List<Issue> assignedIssues = null;
		UserVO assignedUser = new UserVO(0, false, "", "", "");
		if (workItemJPA.getSolver() != null)
			assignedUser = UserAdapter.fromUserDb(workItemJPA.getSolver());
		return new WorkItem(workItemId, description, header, assignedUser, users, assignedIssues);
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
