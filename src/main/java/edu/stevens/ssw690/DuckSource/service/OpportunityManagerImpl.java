package edu.stevens.ssw690.DuckSource.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.stevens.ssw690.DuckSource.dao.OpportunityDao;
import edu.stevens.ssw690.DuckSource.model.Opportunity;

/**
 * 
 * @author susan
 * @see OpportunityManager
 *
 */
@Service
public class OpportunityManagerImpl implements OpportunityManager {

	@Autowired
	OpportunityDao opportunityDao;
	
	public void persist(Opportunity opportunity) {
		opportunityDao.persist(opportunity);
	}

	public List<Opportunity> getAllOpportunities() {
		return opportunityDao.getAllOpportunities();
	}

	public Opportunity getOpportunity(String title) {
		return opportunityDao.getOpportunity(title);
	}

	public List<Opportunity> getByCreator(Integer creator) {
		return opportunityDao.getByCreator(creator);
	}

	public List<Opportunity> getByType(String oppType) {
		return  opportunityDao.getByType(oppType);
	}

	public List<Opportunity> getByOtherThanCreator(Integer userId) {
		return opportunityDao.getByOtherThanCreator(userId);
	}
	public List<Opportunity> getByOtherThanCreatorByType(Integer userId, String oppType) {
		return opportunityDao.getByOtherThanCreatorByType(userId, oppType);
	}

	public Opportunity findById(Integer id) {
		return opportunityDao.findById(id);
	}

	public void merge(Opportunity opportunity) {
		opportunityDao.merge(opportunity);
		
	}

	public List<Opportunity> getByRegistered(Integer userId) {
		return opportunityDao.getByRegistered(userId);
	}

	
	public Opportunity getById(Integer id) {
		return opportunityDao.getById(id);
	}

	public List<Opportunity> getBySubmitted(Integer userId) {
		return opportunityDao.getBySubmitted(userId);
	}

	@Override
	public Opportunity getBySubmittedOpportunity(Integer userId, Integer opportunityId) {
		return opportunityDao.getBySubmittedOpportunity(userId, opportunityId);
	}
	
	public List<Opportunity> getAllOpportunitiesForExcelExport() {
	   return opportunityDao.getAllOpportunitiesForExcelExport();
	}
 
}
