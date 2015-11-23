package edu.stevens.ssw690.DuckSource.dao;

import java.util.List;

import edu.stevens.ssw690.DuckSource.model.Opportunity;

public interface OpportunityDao {
	
	public void persist(Opportunity opportunity);
    public List<Opportunity> getAllOpportunities();
    public Opportunity getOpportunity(String title);
    public List<Opportunity> getByCreator(Integer creator);
    public List<Opportunity> getByType(String oppType);
}
