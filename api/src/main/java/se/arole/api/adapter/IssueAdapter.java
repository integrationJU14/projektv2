package se.arole.api.adapter;

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
		String issueHeader = issueDb.getHeader();
		boolean isSolved = issueDb.isSolved();

		return new IssueVO(issueId, issueDescription, issueHeader, isSolved);
	}
}
//