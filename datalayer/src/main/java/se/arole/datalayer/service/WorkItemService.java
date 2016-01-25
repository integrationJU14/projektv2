package se.arole.datalayer.service;

import java.util.List;

import se.arole.datalayer.entity.Status;
import se.arole.datalayer.entity.Team;
import se.arole.datalayer.entity.User;
import se.arole.datalayer.entity.WorkItem;

public interface WorkItemService {

	WorkItem createWorkItem(WorkItem workItem);

	void changeStatusWorkItem(Status status, Integer workItemId);

	void addWorkItemToUser(WorkItem workItem, User user);

	List<WorkItem> workItemByStatus(Status status);

	List<WorkItem> workItembyUser(User user);

	List<WorkItem> workItemsByTeam(Team team);

	List<WorkItem> workItemByDescription(String description);

}