package edu.stevens.ssw690.DuckSource.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.stevens.ssw690.DuckSource.dao.OpportunityRegisteredDao;
import edu.stevens.ssw690.DuckSource.model.OpportunityRegistered;

/**
 * 
 * @author susan
 * @see OpportunityRegisteredManager
 */
@Service
public class OpportunityRegisteredManagerImpl implements OpportunityRegisteredManager {

	@Autowired
	OpportunityRegisteredDao opportunityRegisteredDao;

	public void persist(OpportunityRegistered opportunityRegistered) {
		opportunityRegisteredDao.persist(opportunityRegistered);;
	}

	public void merge(OpportunityRegistered opportunityRegistered) {
		opportunityRegisteredDao.merge(opportunityRegistered);
	}

	public List<OpportunityRegistered> getByRegistered(Integer userId) {
		return opportunityRegisteredDao.getByRegistered(userId);
	}

	public OpportunityRegistered getByRegisteredOpportunity(Integer userId, Integer opportunityId) {
		return opportunityRegisteredDao.getByRegisteredOpportunity(userId, opportunityId);
	}

	public void remove(OpportunityRegistered opportunityRegistered) {
		opportunityRegisteredDao.remove(opportunityRegistered);
		
	}

	public OpportunityRegistered findById(Integer id) {
		return opportunityRegisteredDao.findById(id);
	}

}
