package se.arole.api.adapter;

import java.util.ArrayList;
import java.util.List;

import se.arole.api.resource.Issue;
import se.arole.datalayer.entity.IssueJPA;

public class IssueAdapter {

	public static IssueJPA toIssueDb(Issue issue) {

		Integer issueId = issue.getIssueId();
		String issueDescription = issue.getDescription();
		String issueHeader = issue.getHeader();
		boolean isSolved = issue.isSolved();

		return new IssueJPA(issueId, issueDescription, issueHeader, isSolved);
	}

	public static Issue fromIssueDb(IssueJPA issueDb) {

		Integer issueId = issueDb.getIssueId();
		String issueDescription = issueDb.getDescription();
		String issueHeader = null;
		boolean isSolved = issueDb.isSolved();

		return new Issue(issueId, issueDescription, issueHeader, isSolved);
	}

	public static List<IssueJPA> toDbIssueList(List<Issue> issueVOList) {
		List<IssueJPA> issueListToDb = new ArrayList<IssueJPA>();
		issueVOList.forEach(u -> {
			issueListToDb.add(toIssueDb(u));
		});
		return issueListToDb;
	}

	public static List<Issue> fromDbIssueList(List<IssueJPA> issueList) {
		List<Issue> issueListFromDb = new ArrayList<Issue>();
		issueList.forEach(u -> {
			issueListFromDb.add(fromIssueDb(u));
		});
		return issueListFromDb;
	}
}
//