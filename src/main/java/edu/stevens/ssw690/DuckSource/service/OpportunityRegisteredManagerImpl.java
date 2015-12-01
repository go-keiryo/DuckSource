package edu.stevens.ssw690.DuckSource.service;

import java.util.List;

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

	public List<OpportunityRegistered> getByRegistered(Integer userId) {
		return dao.getByRegistered(userId);
	}

	public OpportunityRegistered getByRegisteredOpportunity(Integer userId, Integer opportunityId) {
		return dao.getByRegisteredOpportunity(userId, opportunityId);
	}

	public void remove(OpportunityRegistered opportunityRegistered) {
		dao.remove(opportunityRegistered);
		
	}

	public OpportunityRegistered findById(Integer id) {
		return dao.findById(id);
	}

	public OpportunityRegistered getByRegisteredUserOpportunity(Integer userId, Integer opportunityId) {
		return dao.getByRegisteredUserOpportunity(userId, opportunityId);
	}
}
