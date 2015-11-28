package edu.stevens.ssw690.DuckSource.service;

import java.util.List;

import edu.stevens.ssw690.DuckSource.model.OpportunitySubmitted;

public interface OpportunitySubmittedManager {
	
	public void persist(OpportunitySubmitted opportunitySubmitted);
	public void merge(OpportunitySubmitted opportunitySubmitted); 
	public OpportunitySubmitted findById(Integer id);
	public List<OpportunitySubmitted> getBySubmitted(Integer userId);
	public OpportunitySubmitted getBySubmittedOpportunity(Integer userId, Integer opportunityId);

}
