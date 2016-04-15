package edu.stevens.ssw690.DuckSource.dao;

import java.util.Date;
import java.util.List;

import edu.stevens.ssw690.DuckSource.model.OpportunityTime;

/**
 * 
 * @author susan
 *
 *
 */

//Interface exposed to service
public interface OpportunityTimeDao {

	/** 
	 * Saves instance of OpportunityTime to database (managed)
	 * @param opportunityTime
	 */
	public void persist(OpportunityTime opportunityTime);
	
	/**
	 * Saves instance of OpportunityTime to database (unmanaged)
	 * @param opportunityTime
	 */
	public void merge(OpportunityTime opportunityTime); 
	
	/**
	 * Deletes instance of OpportunityTime from database
	 * @param opportunityTime
	 */
	public void remove(OpportunityTime opportunityTime);
	
	/** 
	 * Gets instance of OpportunityTime with id
	 * @param id
	 * @return OpportunityTime or null if doesn't exist
	 */
	public OpportunityTime findById(Integer id);
	
	/** 
	 * Gets all hours worked by user
	 * @param userId
	 * @return List of date/times worked
	 */
	public List<OpportunityTime> getByUser(Integer userId);
	
	/** 
	 * Gets a list of all hours worked by user for opportunity
	 * @param userId
	 * @param opportunityId
	 * @return List of date/times worked
	 */
	public List<OpportunityTime> getByOpportunity(Integer userId, Integer opportunityId);
	
	/**
	 * Gets a list of all hours worked by user for opportunity and date range
	 * @param userId
	 * @param opportunityId
	 * @param startDate
	 * @param endDate
	 * @return  List of date/times worked
	 */
	public List<OpportunityTime> getByDate(Integer userId, Integer opportunityId, Date startDate, Date endDate);
	
	/**
	 * Deletes hours worked by user for opportunity and date range
	 * @param userId
	 * @param oppId
	 * @param startDate
	 * @param endDate
	 */
	public void clearTime(Integer userId,Integer oppId,Date startDate, Date endDate);
}
