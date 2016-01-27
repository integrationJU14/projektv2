package se.arole.datalayer.service;

import java.util.List;

import se.arole.datalayer.entity.Status;
import se.arole.datalayer.entity.TeamJPA;
import se.arole.datalayer.entity.User;
import se.arole.datalayer.entity.WorkItemJPA;

public interface WorkItemService {

	WorkItemJPA createWorkItem(WorkItemJPA workItem);

	void changeStatusWorkItem(String status, Integer workItemId);

	void addWorkItemToUser(WorkItemJPA workItem, User user);

	List<WorkItemJPA> workItemByStatus(String status);

	List<WorkItemJPA> workItembyUser(User user);

	List<WorkItemJPA> workItemsByTeam(TeamJPA team);

	List<WorkItemJPA> workItemByDescription(String description);

	List<WorkItemJPA> getAll();

	WorkItemJPA findByItemId(int itemId);
}