package edu.stevens.ssw690.DuckSource.dao;

import java.util.List;

import edu.stevens.ssw690.DuckSource.model.Opportunity;

public interface OpportunityDao {
	
	public void persist(Opportunity opportunity);
	public void merge(Opportunity opportunity);
    public List<Opportunity> getAllOpportunities();
    public Opportunity findById(Integer id);
    public Opportunity getById(Integer id);
    public Opportunity getOpportunity(String title);
    public List<Opportunity> getByCreator(Integer creator);
    public List<Opportunity> getByRegistered(Integer userId);
    public List<Opportunity> getBySubmitted(Integer userId);
    public Opportunity getBySubmittedOpportunity(Integer userId, Integer opportunityId);
    public List<Opportunity> getByType(String oppType);
    public List<Opportunity> getByOtherThanCreator(Integer userId); 
    public List<Opportunity> getByOtherThanCreatorByType(Integer userId, String oppType);
    public List<Opportunity> getAllOpportunitiesForExcelExport();
}
