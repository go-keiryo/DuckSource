package edu.stevens.ssw690.DuckSource.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.stevens.ssw690.DuckSource.dao.OpportunitySubmittedDao;
import edu.stevens.ssw690.DuckSource.model.OpportunitySubmitted;

@Service
public class OpportunitySubmittedManagerImpl implements OpportunitySubmittedManager {


	@Autowired
	OpportunitySubmittedDao dao;

	public void persist(OpportunitySubmitted opportunitySubmitted) {
		dao.persist(opportunitySubmitted);
		
	}

	public void merge(OpportunitySubmitted opportunitySubmitted) {
		dao.merge(opportunitySubmitted);
		
	}

	public List<OpportunitySubmitted> getBySubmitted(Integer userId) {
		return dao.getBySubmitted(userId);
	}

	public OpportunitySubmitted getBySubmittedOpportunity(Integer userId, Integer opportunityId) {
		return dao.getBySubmittedOpportunity(userId, opportunityId);
	}

	public OpportunitySubmitted findById(Integer id) {
		return dao.findById(id);
	}

	public List<OpportunitySubmitted> getByOpportunity(Integer opportunityId) {
		return dao.getByOpportunity(opportunityId);
	}

}
