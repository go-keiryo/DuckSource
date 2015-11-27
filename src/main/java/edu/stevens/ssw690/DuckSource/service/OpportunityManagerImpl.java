package edu.stevens.ssw690.DuckSource.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.stevens.ssw690.DuckSource.dao.OpportunityDao;
import edu.stevens.ssw690.DuckSource.model.Opportunity;


@Service
public class OpportunityManagerImpl implements OpportunityManager {

	@Autowired
	OpportunityDao dao;
	
	public void persist(Opportunity opportunity) {
		dao.persist(opportunity);
	}

	public List<Opportunity> getAllOpportunities() {
		return dao.getAllOpportunities();
	}

	public Opportunity getOpportunity(String title) {
		return dao.getOpportunity(title);
	}

	public List<Opportunity> getByCreator(Integer creator) {
		return dao.getByCreator(creator);
	}

	public List<Opportunity> getByType(String oppType) {
		return  dao.getByType(oppType);
	}

	public List<Opportunity> getByOtherThanCreator(Integer creator) {
		return dao.getByOtherThanCreator(creator);
	}
	public List<Opportunity> getByOtherThanCreatorByType(Integer creator, String oppType) {
		return dao.getByOtherThanCreatorByType(creator, oppType);
	}

	public Opportunity findById(Integer id) {
		return dao.findById(id);
	}

	public void merge(Opportunity opportunity) {
		dao.merge(opportunity);
		
	}

	public List<Opportunity> getByRegistered(Integer userId) {
		return dao.getByRegistered(userId);
	}

	
	public Opportunity getById(Integer id) {
		return dao.getById(id);
	}
 
}
