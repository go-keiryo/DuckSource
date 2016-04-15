package edu.stevens.ssw690.DuckSource.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.stevens.ssw690.DuckSource.dao.OpportunitySubmittedDao;
import edu.stevens.ssw690.DuckSource.model.OpportunitySubmitted;

@Service
public class OpportunitySubmittedManagerImpl implements OpportunitySubmittedManager {


	@Autowired
	OpportunitySubmittedDao opportunitySubmittedDao;

	public void persist(OpportunitySubmitted opportunitySubmitted) {
		opportunitySubmittedDao.persist(opportunitySubmitted);
		
	}

	public void merge(OpportunitySubmitted opportunitySubmitted) {
		opportunitySubmittedDao.merge(opportunitySubmitted);
		
	}

	public List<OpportunitySubmitted> getBySubmitted(Integer userId) {
		return opportunitySubmittedDao.getBySubmitted(userId);
	}

	public OpportunitySubmitted getBySubmittedOpportunity(Integer userId, Integer opportunityId) {
		return opportunitySubmittedDao.getBySubmittedOpportunity(userId, opportunityId);
	}

	public OpportunitySubmitted findById(Integer id) {
		return opportunitySubmittedDao.findById(id);
	}

	public List<OpportunitySubmitted> getByOpportunity(Integer opportunityId) {
		return opportunitySubmittedDao.getByOpportunity(opportunityId);
	}

}
