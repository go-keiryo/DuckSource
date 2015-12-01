package edu.stevens.ssw690.DuckSource.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "opportunity_submitted")
public class OpportunitySubmitted implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	// Constructors:
    public  OpportunitySubmitted() {
    }
    
    // Persistent Fields:
    @Id 
    @Column(name="opportunity_submitted_id")
    @GeneratedValue
    Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "opportunity_id", nullable = false)
    private Opportunity opportunity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private DuckUser user;
    @Column(name="submission_date")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date submissionDate;
    @Column(name="file_path")
    private String filePath;
    @Column(name="accepted_date")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date acceptedDate;
    @Column(name="status")
    private String status;
    @Column(name="comment", columnDefinition = "TEXT", length = 65535)
    private String comment;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "opportunitySubmitted")
    private Set<OpportunityReviewIssue> opportunitityReviewIssues;
    @Formula("(select opportunity_Id from opportunity o where o.opportunity_id = opportunity_id)")
    private Integer opportunityId;
    @Formula("(select user_id from duck_user u where u.user_id = user_id)")
    private Integer userId;
    @Transient
    private String userName;
    @Transient
    private String fullName;
    
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Opportunity getOpportunity() {
		return opportunity;
	}
	public void setOpportunity(Opportunity opportunity) {
		this.opportunity = opportunity;
	}
	
	public Integer getOpportunityId() {
		return opportunityId;
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	public DuckUser getUser() {
		return user;
	}
	public void setUser(DuckUser user) {
		this.user = user;
	}
	public Date getSubmissionDate() {
		return submissionDate;
	}
	public void setSubmissionDate(Date submittedDate) {
		this.submissionDate = submittedDate;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Date getAcceptedDate() {
		return acceptedDate;
	}
	public void setAcceptedDate(Date acceptedDate) {
		this.acceptedDate = acceptedDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getFullName() {
		return  user.getFirstName() + " " + user.getLastName();
	}
	
	public String getUserName() {
		return user.getUserName();
	}
	public Set<OpportunityReviewIssue> getOpportunitityReviewIssues() {
		return opportunitityReviewIssues;
	}
	public void setOpportunitityReviewIssues(Set<OpportunityReviewIssue> opportunitityReviewIssues) {
		this.opportunitityReviewIssues = opportunitityReviewIssues;
	}
	public void setOpportunityId(Integer opportunityId) {
		this.opportunityId = opportunityId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
