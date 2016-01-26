package se.arole.api.adapter;

import java.util.ArrayList;
import java.util.List;

import se.arole.api.resource.IssueVO;
import se.arole.api.resource.Team;
import se.arole.api.resource.UserVO;
import se.arole.api.resource.WorkItem;
import se.arole.datalayer.entity.Issue;
import se.arole.datalayer.entity.TeamJPA;
import se.arole.datalayer.entity.User;
import se.arole.datalayer.entity.WorkItemJPA;


public class WorkItemAdapter {
	
	
public static WorkItemJPA toWorkItemDb(WorkItem workItem){
		//TODO status & issue ???
	Integer itemId=(int) workItem.getWorkItemId();
	String description=workItem.getDescription();
	String status = null;
	User solver=UserAdapter.toUserDb(workItem.getAssignedUser());
	List<Issue> issue=null;
	
		return new WorkItemJPA(itemId,description,status,solver,issue);
		}
	
	
	public WorkItem fromWorkItemDb(WorkItemJPA workItemJPA){
		//TODO header & users & ussues ???
		int workItemId=workItemJPA.getItemId();
		String description=workItemJPA.getDescription();
		String header=null;
		List<UserVO> users = null;
		List<IssueVO> assignedIssues = null;
		UserVO assignedUser=UserAdapter.fromUserDb(workItemJPA.getSolver());
		return new WorkItem(workItemId, description,
				header,assignedUser,users,assignedIssues);
		}
	public static List<WorkItemJPA> toDbUserList(List<WorkItem> workItemVOList){
		List<WorkItemJPA> workItemListToDb = new ArrayList<WorkItemJPA>();
		workItemVOList.forEach(e -> {
			workItemListToDb.add(toWorkItemDb(e));
		});
		return workItemListToDb;
	}
	
//	public static List<UserVO> fromDbUserList(List<User> userList){
//		List<UserVO> userListFromDb = new ArrayList<UserVO>();
//		userList.forEach(u -> {
//			userListFromDb.add(fromUserDb(u));
//		});
//		return userListFromDb;
//	}
}
