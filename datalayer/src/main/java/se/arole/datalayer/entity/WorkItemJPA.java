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
public class WorkItemJPA implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	@Column(unique=true)
	Integer itemId;
	String description;
	String status;
	@ManyToOne
    @JoinColumn(name = "fk_user_id")
	User solver;
	@OneToMany
    @JoinColumn(name = "fk_workItem_id")
	List<Issue> issue;
	public WorkItemJPA() {
		
	}
	public WorkItemJPA(Integer itemId, String description, String status) {
		super();
		this.itemId = itemId;
		this.description = description;
		this.status = status;
	}
	
	public WorkItemJPA(Integer itemId, String description, String status, User solver, List<Issue> issue) {
		super();
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
	public User getSolver() {
		return solver;
	}
	public void setSolver(User solver) {
		this.solver = solver;
	}
	public List<Issue> getIssue() {
		return new ArrayList<Issue>(issue);
	}
	public void setIssue(List<Issue> issue) {
		this.issue = issue;
	}
	
	
	
	
}
