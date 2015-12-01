package edu.stevens.ssw690.DuckSource.dao;

import java.util.List;

import edu.stevens.ssw690.DuckSource.model.OpportunityReviewIssue;
import edu.stevens.ssw690.DuckSource.model.OpportunitytReviewIssueExtended;

public interface  OpportunityReviewIssueDao {
	
	public void persist(OpportunityReviewIssue opportunityReviewIssue);
	public void merge(OpportunityReviewIssue opportunityReviewIssue);
	public void remove(OpportunityReviewIssue opportunityReviewIssue);
	public OpportunityReviewIssue findById(Integer id);
	public List<OpportunityReviewIssue> getByOpportunitySubmitted(Integer opportunitySubmittedId);
	public List<OpportunitytReviewIssueExtended> getByOpportunitySubmittedExtended(Integer opportunitySubmittedId);
	public List<OpportunityReviewIssue> getByOpportunity(Integer opportunityId);

}
