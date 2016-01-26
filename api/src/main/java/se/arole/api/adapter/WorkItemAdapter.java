package se.arole.api.adapter;

import java.util.List;

import se.arole.api.resource.IssueVO;
import se.arole.api.resource.Team;
import se.arole.api.resource.UserVO;
import se.arole.api.resource.WorkItemVO;
import se.arole.datalayer.entity.Issue;
import se.arole.datalayer.entity.TeamJPA;
import se.arole.datalayer.entity.User;
import se.arole.datalayer.entity.WorkItem;


public class WorkItemAdapter {
	
	
public WorkItem toWorkItemDb(WorkItemVO workItemVO){
		//TODO status & issue ???
	Integer itemId=(int) workItemVO.getWorkItemId();
	String description=workItemVO.getDescription();
	String status = null;
	User solver=UserAdapter.toUserDb(workItemVO.getAssignedUser());
	List<Issue> issue=null;
	
		return new WorkItem(itemId,description,status,solver,issue);
		}
	
	
	public WorkItemVO fromWorkItemDb(WorkItem workItem){
		//TODO header & users & ussues ???
		int workItemId=workItem.getItemId();
		String description=workItem.getDescription();
		String header=null;
		List<UserVO> users = null;
		List<IssueVO> assignedIssues = null;
		UserVO assignedUser=UserAdapter.fromUserDb(workItem.getSolver());
		return new WorkItemVO(workItemId, description,
				header,assignedUser,users,assignedIssues);
		}
}
