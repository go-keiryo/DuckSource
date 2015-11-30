package edu.stevens.ssw690.DuckSource.dao;

import java.util.List;

import edu.stevens.ssw690.DuckSource.model.OpportunitySubmitted;

public interface OpportunitySubmittedDao {
	
	public void persist(OpportunitySubmitted opportunitySubmitted);
	public void merge(OpportunitySubmitted opportunitySubmitted); 
	public OpportunitySubmitted findById(Integer id);
	public List<OpportunitySubmitted> getBySubmitted(Integer userId);
	public OpportunitySubmitted getBySubmittedOpportunity(Integer userId, Integer opportunityId);
	public List<OpportunitySubmitted> getByOpportunity(Integer opportunityId);
	
}
