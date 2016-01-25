package se.arole.datalayer.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Issue implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	@Column(unique=true)
	Integer issueId;
	String description, header;
	boolean isSolved;
	@ManyToOne
    @JoinColumn(name = "fk_workItem_id")
	WorkItem workItem;

	public Issue() {

	}

	public Issue(Integer issueId, String description, String header, boolean isSolved) {
		super();
		this.issueId = issueId;
		this.description=description;
		this.header=header;
		this.isSolved = isSolved;
	}

	public Integer getIssueId() {
		return issueId;
	}

	public void setIssueId(Integer issueId) {
		this.issueId = issueId;
	}

	public boolean isSolved() {
		return isSolved;
	}

	public void setSolved(boolean isSolved) {
		this.isSolved = isSolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String message) {
		this.description = message;
	}

	public WorkItem getWorkItem() {
		return workItem;
	}

	public void setWorkItem(WorkItem workItem) {
		this.workItem = workItem;
	}

	public String getHeader() {
		// TODO Auto-generated method stub
		return header;
	}
	
	public void setHeader(String header) {
		this.header = header;
	}

}
