package edu.stevens.ssw690.DuckSource.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "opportunity_review_issue")
public class OpportunityReviewIssue implements Serializable {

    private static final long serialVersionUID = 1L;
	
	// Constructors:
    public OpportunityReviewIssue() {
    }
    
    // Persistent Fields:
    @Id 
    @Column(name="opportunity_review_issue_id")
    @GeneratedValue
    Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "opportunity_submitted_id", nullable = false)
    private OpportunitySubmitted opportunitySubmitted;
    @Column(name="creation_date")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date creationDate;
    @Column(name="issue_id")
    Integer issueId;
    @Column(name="comment", columnDefinition = "TEXT", length = 65535)
    private String comment;
    @Column(name="resolution_date")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date resolutionDate;
    @Formula("(select opportunity_submitted_Id from opportunity_submitted o where o.opportunity_submitted_id = opportunity_submitted_id)")
    private Integer opportunitySubmittedId;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public OpportunitySubmitted getOpportunitySubmitted() {
		return opportunitySubmitted;
	}
	public void setOpportunitySubmitted(OpportunitySubmitted opportunitySubmitted) {
		this.opportunitySubmitted = opportunitySubmitted;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Integer getIssueId() {
		return issueId;
	}
	public void setIssueId(Integer issueId) {
		this.issueId = issueId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getResolutionDate() {
		return resolutionDate;
	}
	public void setResolutionDate(Date resolutionDate) {
		this.resolutionDate = resolutionDate;
	}
	public Integer getOpportunitySubmittedId() {
		return opportunitySubmittedId;
	}
	public void setOpportunitySubmittedId(Integer opportunitySubmittedId) {
		this.opportunitySubmittedId = opportunitySubmittedId;
	}

}
