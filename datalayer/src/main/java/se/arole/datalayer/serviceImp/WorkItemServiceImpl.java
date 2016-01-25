package se.arole.datalayer.serviceImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.arole.datalayer.entity.Status;
import se.arole.datalayer.entity.Team;
import se.arole.datalayer.entity.User;
import se.arole.datalayer.entity.WorkItem;
import se.arole.datalayer.repository.WorkItemRepository;
import se.arole.datalayer.service.WorkItemService;

@Service
public class WorkItemServiceImpl implements WorkItemService {
	@Autowired
	private WorkItemRepository repository;

	public WorkItem createWorkItem(WorkItem workItem) {

		return repository.save(workItem);

	}

	public void changeStatusWorkItem(Status status, Integer workItemId) {
		WorkItem workItem = repository.findByItemId(workItemId);
		workItem.setStatus(status.toString());
		repository.save(workItem);

	}

	public void addWorkItemToUser(WorkItem workItem, User user) {
		workItem.setSolver(user);
		repository.save(workItem);
	}

	public List<WorkItem> workItemByStatus(Status status) {
		return repository.findByStatus(status.toString());

	}

	public List<WorkItem> workItembyUser(User user) {
		return repository.findBySolver(user);
	}

	public List<WorkItem> workItemsByTeam(Team team) {
		List<WorkItem> workItems = new ArrayList<>();

		for (User user : team.getUsers()) {
			workItems.addAll(repository.findBySolver(user));
		}
		return workItems;
	}

	public List<WorkItem> workItemByDescription(String description) {
		List<WorkItem> workItems = new ArrayList<>();
		for (WorkItem workItem : repository.findAll()) {
			if (workItem.getDescription().contains(description))
				workItems.add(workItem);

		}
		return workItems;

	}

}
