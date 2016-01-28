package se.arole.api.controller;

import java.util.List;

import se.arole.api.adapter.TeamAdapter;
import se.arole.api.adapter.UserAdapter;
import se.arole.api.adapter.WorkItemAdapter;
import se.arole.api.resource.Team;
import se.arole.api.resource.User;
import se.arole.api.resource.WorkItem;
import se.arole.datalayer.entity.TeamJPA;
import se.arole.datalayer.entity.UserJPA;
import se.arole.datalayer.entity.WorkItemJPA;
import se.arole.datalayer.service.WorkItemService;

public class WorkItemController {

	private WorkItemService workItemService;

	public WorkItemController(WorkItemService workItemService) {
		this.workItemService = workItemService;
	}

	public WorkItemController() {
	}

	public WorkItem createWorkItem(WorkItem workItem) {

		WorkItemJPA workItemJPA = WorkItemAdapter.toWorkItemDb(workItem);
		WorkItemJPA createdWorkItem = workItemService.createWorkItem(workItemJPA);

		return WorkItemAdapter.fromWorkItemDb(createdWorkItem);

	}

	public void changeStatusWorkItem(String status, Integer workItemId) {
		workItemService.changeStatusWorkItem(status, workItemId);

	}

	public void addWorkItemToUser(WorkItem workItem, User user) {

		WorkItemJPA workJPA = WorkItemAdapter.toWorkItemDb(workItem);
		UserJPA userJPA = UserAdapter.toUserDb(user);
		workItemService.addWorkItemToUser(workJPA, userJPA);
	}

	public List<WorkItem> workItemByStatus(String status) {

		List<WorkItemJPA> workListJPA = workItemService.workItemByStatus(status);

		return WorkItemAdapter.fromDbWorkList(workListJPA);

	}

	public List<WorkItem> workItembyUser(User userVO) {

		UserJPA userJPA = UserAdapter.toUserDb(userVO);
		List<WorkItemJPA> workListJPA = workItemService.workItembyUser(userJPA);

		return WorkItemAdapter.fromDbWorkList(workListJPA);

	}

	public List<WorkItem> workItemsByTeam(Team team) {

		TeamJPA teamJPA = TeamAdapter.toTeamDb(team);
		List<WorkItemJPA> workListJPA = workItemService.workItemsByTeam(teamJPA);

		return WorkItemAdapter.fromDbWorkList(workListJPA);

	}

	public List<WorkItem> workItemByDescription(String description) {

		List<WorkItemJPA> workListJPA = workItemService.workItemByDescription(description);

		return WorkItemAdapter.fromDbWorkList(workListJPA);

	}

	public WorkItem findByItemId(int itemId) {

		WorkItemJPA workItem = workItemService.findByItemId(itemId);

		return WorkItemAdapter.fromWorkItemDb(workItem);

	}

	public List<WorkItem> getAll() {

		List<WorkItemJPA> workListJPA = workItemService.getAll();

		return WorkItemAdapter.fromDbWorkList(workListJPA);
	}

	public void remove(Integer id) {
		workItemService.deleteWorkItem(id);
	}

	public List<WorkItem> getAllWithIssues() {
		return WorkItemAdapter.fromDbWorkList(workItemService.getAllWithIssues());
	}

}
