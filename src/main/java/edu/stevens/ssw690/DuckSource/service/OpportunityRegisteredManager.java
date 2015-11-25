package edu.stevens.ssw690.DuckSource.service;

import edu.stevens.ssw690.DuckSource.dao.OpportunityRegisteredDao;
import edu.stevens.ssw690.DuckSource.model.OpportunityRegistered;

public interface OpportunityRegisteredManager {
	void persist(OpportunityRegistered opportunityRegistered);
}
