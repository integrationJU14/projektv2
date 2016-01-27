package se.arole.datalayer.serviceImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.arole.datalayer.entity.TeamJPA;
import se.arole.datalayer.entity.UserJPA;
import se.arole.datalayer.entity.WorkItemJPA;
import se.arole.datalayer.repository.WorkItemRepository;
import se.arole.datalayer.service.WorkItemService;

@Service
public class WorkItemServiceImpl implements WorkItemService {
	@Autowired
	private WorkItemRepository repository;

	public WorkItemJPA createWorkItem(WorkItemJPA workItem) {
		return repository.save(workItem);
	}

	public void changeStatusWorkItem(String status, Integer workItemId) {
		WorkItemJPA workItem = repository.findByItemId(workItemId);
		workItem.setStatus(status);
		repository.save(workItem);
	}

	public void addWorkItemToUser(WorkItemJPA workItem, UserJPA user) {
		workItem.setSolver(user);
		repository.save(workItem);
	}

	public void addWorkItemToUser(Integer id, UserJPA user) {
		WorkItemJPA workItem = findByItemId(id);
		workItem.setSolver(user);
		repository.save(workItem);
	}

	public List<WorkItemJPA> workItemByStatus(String status) {
		return repository.findByStatus(status);

	}

	public List<WorkItemJPA> workItembyUser(UserJPA user) {
		return repository.findBySolver(user);
	}

	public List<WorkItemJPA> workItemsByTeam(TeamJPA team) {
		List<WorkItemJPA> workItems = new ArrayList<>();
		for (UserJPA user : team.getUsers()) {
			workItems.addAll(repository.findBySolver(user));
		}
		return workItems;
	}

	public List<WorkItemJPA> workItemByDescription(String description) {
		List<WorkItemJPA> workItems = new ArrayList<>();
		for (WorkItemJPA workItem : repository.findAll()) {
			if (workItem.getDescription().contains(description))
				workItems.add(workItem);
		}
		return workItems;
	}

	public WorkItemJPA findByItemId(int itemId) {
		return repository.findByItemId(itemId);
	}

	@Override
	public List<WorkItemJPA> getAll() {
		return (List<WorkItemJPA>) repository.findAll();
	}

	@Override
	public void deleteWorkItem(Integer itemId) {
		WorkItemJPA findByItemId = findByItemId(itemId);
		repository.delete(findByItemId);
	}
}
