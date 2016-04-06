package edu.stevens.ssw690.DuckSource.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.stevens.ssw690.DuckSource.dao.OpportunityTimeDao;
import edu.stevens.ssw690.DuckSource.model.OpportunityTime;

/**
 * 
 * @author susan
 * @see interface (OpportunityTimeManager)
 * 
 */
@Service
// Not to be called directly
public class OpportunityTimeManagerImpl implements OpportunityTimeManager {

	@Autowired
	OpportunityTimeDao dao;
	
	public void persist(OpportunityTime opportunityTime) {
		dao.persist(opportunityTime);;
	}

	public void merge(OpportunityTime opportunityTime) {
		dao.merge(opportunityTime);
	}

	public void remove(OpportunityTime opportunityTime) {
		dao.remove(opportunityTime);
		
	}

	public OpportunityTime findById(Integer id) {
		return dao.findById(id);
	}

	public List<OpportunityTime> getByUser(Integer userId) {
		return dao.getByUser(userId);
	}

	public List<OpportunityTime> getByOpportunity(Integer userId, Integer opportunityId) {
		
		return dao.getByOpportunity(userId, opportunityId);
		
	}

	public List<OpportunityTime> getByDate(Integer userId, Integer opportunityId, Date startDate, Date endDate) {
		
		return dao.getByDate(userId, opportunityId, startDate, endDate);
		
	}
	
	 public void clearTime(Integer userId,Integer oppId,Date startDate, Date endDate) {
		 dao.clearTime(userId, oppId, startDate, endDate);
	 }
	

}
