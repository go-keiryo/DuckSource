package edu.stevens.ssw690.DuckSource.service;

import edu.stevens.ssw690.DuckSource.model.OpportunityRegistered;

public interface OpportunityRegisteredManager {
	public void persist(OpportunityRegistered opportunityRegistered);
	public void merge(OpportunityRegistered opportunityRegistered);
}
