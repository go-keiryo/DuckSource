package edu.stevens.ssw690.DuckSource.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.stevens.ssw690.DuckSource.dao.OpportunityRegisteredDao;
import edu.stevens.ssw690.DuckSource.model.OpportunityRegistered;

@Service
public class OpportunityRegisteredManagerImpl implements OpportunityRegisteredManager {

	@Autowired
	OpportunityRegisteredDao dao;

	public void persist(OpportunityRegistered opportunityRegistered) {
		dao.persist(opportunityRegistered);;
		
	}

	public void merge(OpportunityRegistered opportunityRegistered) {
		dao.merge(opportunityRegistered);
		
	}
}
