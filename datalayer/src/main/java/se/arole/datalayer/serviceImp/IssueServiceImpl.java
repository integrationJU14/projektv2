package se.arole.datalayer.serviceImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.arole.datalayer.entity.IssueJPA;
import se.arole.datalayer.entity.WorkItemJPA;
import se.arole.datalayer.repository.IssueRepository;
import se.arole.datalayer.repository.WorkItemRepository;
import se.arole.datalayer.service.IssueService;

@Service
public class IssueServiceImpl implements IssueService {

	@Autowired
	private IssueRepository issueRepository;
	@Autowired
	private WorkItemRepository workItemRepository;

	@Override
	public IssueJPA createIssue(IssueJPA issue) {

		return issueRepository.save(issue);
	}

	@Override
	public IssueJPA updateIssue(IssueJPA issue, int issueId) {

		IssueJPA issueToUpdate = issueRepository.findByIssueId(issueId);

		issueToUpdate.setDescription(issue.getDescription());
		issueToUpdate.setHeader(issue.getHeader());
		issueToUpdate.setSolved(issue.isSolved());

		issueRepository.save(issueToUpdate);

		return issueToUpdate;
	}

	@Override
	public List<WorkItemJPA> workItemsWithIssues() {
		List<WorkItemJPA> workItems = new ArrayList<>();

		for (WorkItemJPA workItem : workItemRepository.findAll()) {
			if (!workItem.getIssue().isEmpty()) {
				workItems.add(workItem);
			}
		}

		return workItems;
	}

	@Override
	public void addIssueToWorkItem(Integer workId, Integer issueId) {
		IssueJPA addMe = issueRepository.findByIssueId(issueId);
		WorkItemJPA updateMe = workItemRepository.findByItemId(workId);

		updateMe.addIssue(addMe);

		workItemRepository.save(updateMe);
	}

	@Override
	public IssueJPA getIssue(Integer id) {
		return issueRepository.findByIssueId(id);
	}

}
