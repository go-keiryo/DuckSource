package edu.stevens.ssw690.DuckSource.service;

import java.util.List;

import edu.stevens.ssw690.DuckSource.model.OpportunityReviewIssue;
import edu.stevens.ssw690.DuckSource.model.OpportunitytReviewIssueExtended;

/**
 * 
 * @author susan
 *
 */
public interface OpportunityReviewIssueManager {
	
	/**
	 * Saves the Opportunity Review Issue to the database (managed)
	 * @param opportunityReviewIssue
	 */
	public void persist(OpportunityReviewIssue opportunityReviewIssue);
	
	/**
	 * Saves the Opportunity Review Issue to the database (unmanaged)
	 * @param opportunityReviewIssue
	 */
	public void merge(OpportunityReviewIssue opportunityReviewIssue);
	
	/**
	 * Deletes the Opportunity Review Issue from the database
	 * @param opportunityReviewIssue
	 */
	public void remove(OpportunityReviewIssue opportunityReviewIssue);
	
	/**
	 * Get an opportunity review issue by its id
	 * @param id
	 * @return Review Issue
	 */
	public OpportunityReviewIssue findById(Integer id);
	
	/**
	 * Gets a list of opportunity review issues for the submitted opportunity id
	 * @param opportunitySubmittedId
	 * @return List of Review Issues
	 */
	public List<OpportunityReviewIssue> getByOpportunitySubmitted(Integer opportunitySubmittedId);
	
	/**
	 * Gets a list of opportunity review issues for the submitted opportunity id with additional columns for display
	 * @param opportunitySubmittedId
	 * @return  List of Review Issues
	 */
	public List<OpportunitytReviewIssueExtended> getByOpportunitySubmittedExtended(Integer opportunitySubmittedId);
	
	/**
	 * Gets a list of opportunity review issues for an opportunity by id
	 * @param opportunityId
	 * @return  List of Review Issues
	 */
	public List<OpportunityReviewIssue> getByOpportunity(Integer opportunityId);

}
