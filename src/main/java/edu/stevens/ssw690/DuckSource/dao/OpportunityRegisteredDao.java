package edu.stevens.ssw690.DuckSource.dao;

import java.util.List;

import edu.stevens.ssw690.DuckSource.model.OpportunityRegistered;

public interface OpportunityRegisteredDao {
	
	 public void persist(OpportunityRegistered opportunityRegistered);
	 public void merge(OpportunityRegistered opportunityRegistered); 
	 public void remove(OpportunityRegistered opportunityRegistered);
	 public OpportunityRegistered findById(Integer id);
	 public List<OpportunityRegistered> getByRegistered(Integer userId);
	 public OpportunityRegistered getByRegisteredOpportunity(Integer userId, Integer opportunityId);

}
