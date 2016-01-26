package se.arole.api.resource;

public class Issue {

	int issueId;
	String description, header;
	boolean isSolved;
	
	
	public Issue(int issueId, String description, String header, boolean isSolved) {
		
		this.issueId = issueId;
		this.description = description;
		this.header = header;
		this.isSolved = isSolved;
		
	}


	public int getIssueId() {
		return issueId;
	}


	public void setIssueId(int issueId) {
		this.issueId = issueId;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getHeader() {
		return header;
	}


	public void setHeader(String header) {
		this.header = header;
	}


	public boolean isSolved() {
		return isSolved;
	}


	public void setSolved(boolean isSolved) {
		this.isSolved = isSolved;
	}

	
//	
}
