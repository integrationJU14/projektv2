package se.arole.datalayer.service;

import java.util.List;

import se.arole.datalayer.entity.Status;
import se.arole.datalayer.entity.TeamJPA;
import se.arole.datalayer.entity.UserJPA;
import se.arole.datalayer.entity.WorkItemJPA;

public interface WorkItemService {

	WorkItemJPA createWorkItem(WorkItemJPA workItem);

	void changeStatusWorkItem(String status, Integer workItemId);

	void addWorkItemToUser(WorkItemJPA workItem, UserJPA user);

	void addWorkItemToUser(Integer id, UserJPA user);

	List<WorkItemJPA> workItemByStatus(String status);

	List<WorkItemJPA> workItembyUser(UserJPA user);

	List<WorkItemJPA> workItemsByTeam(TeamJPA team);

	List<WorkItemJPA> workItemByDescription(String description);

	List<WorkItemJPA> getAll();

	WorkItemJPA findByItemId(int itemId);

	void deleteWorkItem(Integer itemId);
}