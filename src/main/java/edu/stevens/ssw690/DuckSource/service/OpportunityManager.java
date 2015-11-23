package edu.stevens.ssw690.DuckSource.service;

import java.util.List;

import edu.stevens.ssw690.DuckSource.model.Opportunity;

public interface OpportunityManager {
	
	public void persist(Opportunity opportunity);
    public List<Opportunity> getAllOpportunities();
    public Opportunity getOpportunity(String title);
    public List<Opportunity> getByCreator(Integer creator);
    public List<Opportunity> getByType(String oppType);

}
