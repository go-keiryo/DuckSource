package edu.stevens.ssw690.DuckSource.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.stevens.ssw690.DuckSource.dao.OpportunityReviewIssueDao;
import edu.stevens.ssw690.DuckSource.model.OpportunityReviewIssue;
import edu.stevens.ssw690.DuckSource.model.OpportunitytReviewIssueExtended;

@Service
public class OpportunityReviewIssueManagerImpl implements OpportunityReviewIssueManager {
	
	@Autowired
	OpportunityReviewIssueDao opportunityReviewIssueDao;

	public void persist(OpportunityReviewIssue opportunityReviewIssue) {
		opportunityReviewIssueDao.persist(opportunityReviewIssue);
		
	}

	
	public void merge(OpportunityReviewIssue opportunityReviewIssue) {
		opportunityReviewIssueDao.merge(opportunityReviewIssue);
		
	}


	public void remove(OpportunityReviewIssue opportunityReviewIssue) {
		opportunityReviewIssueDao.remove(opportunityReviewIssue);
		
	}


	public OpportunityReviewIssue findById(Integer id) {
		return opportunityReviewIssueDao.findById(id);
	}


	public List<OpportunitytReviewIssueExtended> getByOpportunitySubmittedExtended(Integer opportunitySubmittedId) {
		return opportunityReviewIssueDao.getByOpportunitySubmittedExtended(opportunitySubmittedId);
	}


	public List<OpportunityReviewIssue> getByOpportunitySubmitted(Integer opportunitySubmittedId) {
		return opportunityReviewIssueDao.getByOpportunitySubmitted(opportunitySubmittedId);
	}


	public List<OpportunityReviewIssue> getByOpportunity(Integer opportunityId) {
		return opportunityReviewIssueDao.getByOpportunity(opportunityId);
	};

}
