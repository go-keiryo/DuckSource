package edu.stevens.ssw690.DuckSource.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "review_issue")
public class ReviewIssue implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// Persistent Fields:
    @Id 
    @Column(name="issue_id")
    @GeneratedValue
    Integer id;
    @Column(name="issue_title")
    private String issueTitle;
    @Column(name="description", columnDefinition = "TEXT", length = 65535)
    private String description;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIssueTitle() {
		return issueTitle;
	}
	public void setIssueTitle(String issueTitle) {
		this.issueTitle = issueTitle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
    
    
}
