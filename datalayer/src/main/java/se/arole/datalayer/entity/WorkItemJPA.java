package se.arole.datalayer.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class WorkItemJPA implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	@Column(unique = true)
	Integer itemId;
	String description;
	String status;
	@ManyToOne
	@JoinColumn(name = "fk_user_id")
	UserJPA solver;
	@OneToMany
	@JoinColumn(name = "fk_workItem_id")
	List<IssueJPA> issue;

	public WorkItemJPA() {

	}

	public WorkItemJPA(Integer itemId, String description, String status) {
		this.itemId = itemId;
		this.description = description;
		this.status = status;
	}

	public WorkItemJPA(Integer itemId, String description, String status, List<IssueJPA> issue) {
		this.itemId = itemId;
		this.description = description;
		this.status = status;
		this.issue = issue;
	}

	public WorkItemJPA(Integer itemId, String description, String status, UserJPA solver, List<IssueJPA> issue) {
		this.itemId = itemId;
		this.description = description;
		this.status = status;
		this.solver = solver;
		this.issue = issue;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UserJPA getSolver() {
		return solver;
	}

	public void setSolver(UserJPA solver) {
		this.solver = solver;
	}

	public List<IssueJPA> getIssue() {
		return new ArrayList<IssueJPA>(issue);
	}

	public void setIssue(List<IssueJPA> issue) {
		this.issue = issue;
	}

}
