package se.arole.datalayer.serviceImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.arole.datalayer.entity.TeamJPA;
import se.arole.datalayer.entity.UserJPA;
import se.arole.datalayer.entity.WorkItemJPA;
import se.arole.datalayer.repository.TeamRepository;
import se.arole.datalayer.repository.UserRepository;
import se.arole.datalayer.repository.WorkItemRepository;
import se.arole.datalayer.service.WorkItemService;

@Service
public class WorkItemServiceImpl implements WorkItemService {
	@Autowired
	private WorkItemRepository repository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TeamRepository teamRepository;

	public WorkItemJPA createWorkItem(WorkItemJPA workItem) {
		return repository.save(workItem);
	}

	public void changeStatusWorkItem(String status, Integer workItemId) {
		WorkItemJPA workItem = repository.findByItemId(workItemId);
		workItem.setStatus(status);
		repository.save(workItem);
	}

	public void addWorkItemToUser(WorkItemJPA workItem, UserJPA user) {
		UserJPA updateMe = userRepository.findByUserId(user.getUserId());
		WorkItemJPA saveMe = repository.findByItemId(workItem.getItemId());
		saveMe.setSolver(updateMe);
		repository.save(saveMe);
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
		UserJPA findByMe = userRepository.findByUserId(user.getUserId());
		return repository.findBySolver(findByMe);
	}

	public List<WorkItemJPA> workItemsByTeam(TeamJPA team) {
		TeamJPA searchInMe = teamRepository.findByTeamId(team.getTeamId());
		List<WorkItemJPA> workItems = new ArrayList<>();
		for (UserJPA user : searchInMe.getUsers()) {
			UserJPA findByUserId = userRepository.findByUserId(user.getUserId());
			workItems.addAll(repository.findBySolver(findByUserId));
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
