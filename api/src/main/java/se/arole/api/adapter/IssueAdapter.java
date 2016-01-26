package se.arole.api.adapter;

import java.util.ArrayList;
import java.util.List;

import se.arole.api.resource.IssueVO;
import se.arole.datalayer.entity.Issue;

public class IssueAdapter {

	public static Issue toIssueDb(IssueVO issue) {

		Integer issueId = issue.getIssueId();
		String issueDescription = issue.getDescription();
		String issueHeader = issue.getHeader();
		boolean isSolved = issue.isSolved();

		return new Issue(issueId, issueDescription, issueHeader, isSolved);
	}

	public static IssueVO fromIssueDb(Issue issueDb) {

		Integer issueId = issueDb.getIssueId();
		String issueDescription = issueDb.getDescription();
		String issueHeader = null;
		boolean isSolved = issueDb.isSolved();

		return new IssueVO(issueId, issueDescription, issueHeader, isSolved);
	}

	public static List<Issue> toDbIssueList(List<IssueVO> issueVOList) {
		List<Issue> issueListToDb = new ArrayList<Issue>();
		issueVOList.forEach(u -> {
			issueListToDb.add(toIssueDb(u));
		});
		return issueListToDb;
	}

	public static List<IssueVO> fromDbIssueList(List<Issue> issueList) {
		List<IssueVO> issueListFromDb = new ArrayList<IssueVO>();
		issueList.forEach(u -> {
			issueListFromDb.add(fromIssueDb(u));
		});
		return issueListFromDb;
	}
}
//