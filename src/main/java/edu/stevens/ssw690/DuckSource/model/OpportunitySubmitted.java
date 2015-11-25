package edu.stevens.ssw690.DuckSource.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    @Column(name="opportunity_id")
    Integer opportunity_id;
    @Column(name="user_id")
    Integer user_id;
    @Column(name="submitted_date")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date submittedDate;
    @Column(name="file_path")
    private String filePath;
    @Column(name="accepted_date")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date acceptedDate;
    @Column(name="status")
    private String status;
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOpportunity_id() {
		return opportunity_id;
	}
	public void setOpportunity_id(Integer opportunity_id) {
		this.opportunity_id = opportunity_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Date getSubmittedDate() {
		return submittedDate;
	}
	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
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

	@Column(name="comment", columnDefinition = "TEXT", length = 65535)
    private String comment;

}
