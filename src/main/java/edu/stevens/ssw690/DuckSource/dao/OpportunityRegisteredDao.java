package edu.stevens.ssw690.DuckSource.dao;

import edu.stevens.ssw690.DuckSource.model.OpportunityRegistered;

public interface OpportunityRegisteredDao {
	
	 public void persist(OpportunityRegistered opportunityRegistered);
	 public void merge(OpportunityRegistered opportunityRegistered); 

}
