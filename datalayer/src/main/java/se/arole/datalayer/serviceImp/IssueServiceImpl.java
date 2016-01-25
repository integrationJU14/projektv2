package se.arole.datalayer.serviceImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.arole.datalayer.entity.Issue;
import se.arole.datalayer.entity.WorkItem;
import se.arole.datalayer.repository.IssueRepository;
import se.arole.datalayer.service.IssueService;

@Service
public class IssueServiceImpl implements IssueService {

	@Autowired
	private IssueRepository issueRepository;
	

	@Override
	public Issue createIssue(Issue issue) {

		return issueRepository.save(issue);
	}

	@Override
	public Issue updateIssue(Issue issue, int issueId) {

		Issue issueToUpdate = issueRepository.findByIssueId(issueId);
	
			issueToUpdate.setMessage(issue.getMessage());
			issueToUpdate.setSolved(issue.isSolved());

			issueRepository.save(issueToUpdate);
		
		
		return issueToUpdate;
	}

	@Override
	public void addIssueToWorkItem(WorkItem workItem, Issue issue) {
		Issue issueToUpdate=issueRepository.findByIssueId(issue.getIssueId());
		issueToUpdate.setWorkItem(workItem);
		issueRepository.save(issueToUpdate);

	}

	@Override
	public List<WorkItem> workItemsWithIssues() {
		List<WorkItem> workItems = new ArrayList<>();
		for (Issue issue : issueRepository.findAll()) {
			if (issue.getWorkItem()!= null){
				WorkItem workItem =issue.getWorkItem();
				workItems.add(workItem);
				}
		}
		return workItems;
	}

}
