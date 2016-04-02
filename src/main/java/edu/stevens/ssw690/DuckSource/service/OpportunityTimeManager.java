package edu.stevens.ssw690.DuckSource.service;

import java.util.Date;
import java.util.List;

import edu.stevens.ssw690.DuckSource.model.OpportunityTime;

public interface OpportunityTimeManager {
	
	public void persist(OpportunityTime opportunityTime);
	public void merge(OpportunityTime opportunityTime); 
	public void remove(OpportunityTime opportunityTime);
	public OpportunityTime findById(Integer id);
	public List<OpportunityTime> getByUser(Integer userId);
	public List<OpportunityTime> getByOpportunity(Integer userId, Integer opportunityId);
	public List<OpportunityTime> getByDate(Integer userId, Integer opportunityId, Date startDate, Date endDate);
	public void clearTime(Integer userId,Integer oppId,Date startDate, Date endDate);
}
