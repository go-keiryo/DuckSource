package edu.stevens.ssw690.DuckSource.model;

import java.util.Date;

/**
 * 
 * @author susan
 * 
 *
 */

public class OpportunitytReviewIssueExtended {
	
	private int id;
	private Date creationDate;
	private int issueId;
	private String comment;
	private Date resolutionDate;
	private String issueTitle;
	private OpportunitySubmitted opportunitySubmitted;
	
	
	public OpportunitytReviewIssueExtended(int id, Date creationDate, int issueId, String comment, Date resolutionDate, String issueTitle) {
		this.id = id;
		this.creationDate = creationDate;
		this.issueId = issueId;
		this.comment = comment;
		this.resolutionDate = resolutionDate;
		this.issueTitle = issueTitle;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Date getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}


	public int getIssueId() {
		return issueId;
	}


	public void setIssueId(int issueId) {
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


	public String getIssueTitle() {
		return issueTitle;
	}


	public void setIssueTitle(String issueTitle) {
		this.issueTitle = issueTitle;
	}


	public OpportunitySubmitted getOpportunitySubmitted() {
		return opportunitySubmitted;
	}


	public void setOpportunitySubmitted(OpportunitySubmitted opportunitySubmitted) {
		this.opportunitySubmitted = opportunitySubmitted;
	}
	
	

}
