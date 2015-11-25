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

}
