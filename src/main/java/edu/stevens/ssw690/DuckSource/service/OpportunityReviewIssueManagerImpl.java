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
	OpportunityReviewIssueDao dao;

	public void persist(OpportunityReviewIssue opportunityReviewIssue) {
		dao.persist(opportunityReviewIssue);
		
	}

	
	public void merge(OpportunityReviewIssue opportunityReviewIssue) {
		dao.merge(opportunityReviewIssue);
		
	}


	public void remove(OpportunityReviewIssue opportunityReviewIssue) {
		dao.remove(opportunityReviewIssue);
		
	}


	public OpportunityReviewIssue findById(Integer id) {
		return dao.findById(id);
	}


	public List<OpportunitytReviewIssueExtended> getByOpportunitySubmittedExtended(Integer opportunitySubmittedId) {
		return dao.getByOpportunitySubmittedExtended(opportunitySubmittedId);
	}


	public List<OpportunityReviewIssue> getByOpportunitySubmitted(Integer opportunitySubmittedId) {
		return dao.getByOpportunitySubmitted(opportunitySubmittedId);
	};

}
