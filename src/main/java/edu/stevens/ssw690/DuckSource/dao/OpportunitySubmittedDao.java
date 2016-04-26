package edu.stevens.ssw690.DuckSource.dao;

import java.util.List;

import edu.stevens.ssw690.DuckSource.model.OpportunitySubmitted;

/**
 * 
 * @author susan
 *
 */
public interface OpportunitySubmittedDao {
	
	/**
	 * Save the submitted opportunity to the database (managed)
	 * @param opportunitySubmitted
	 */
	public void persist(OpportunitySubmitted opportunitySubmitted);
	
	/**
	 * Save the submitted opportunity to the database (unmanaged)
	 * @param opportunitySubmitted
	 */
	public void merge(OpportunitySubmitted opportunitySubmitted); 
	
	/**
	  * Get the submitted opportunity by its id
	  * @param id
	  * @return Opportunity Submitted
	  */
	public OpportunitySubmitted findById(Integer id);
	
	/**
	  * Get a list of all submitted opportunities for a user by id
	  * @param userId
	  * @return List of Opportunity Submitted
	  */
	public List<OpportunitySubmitted> getBySubmitted(Integer userId);
	
	/**
	  * Get a submitted opportunity by its opportunity id and user id
	  * @param userId
	  * @param opportunityId
	  * @return Opportunity Submitted
	  */
	public OpportunitySubmitted getBySubmittedOpportunity(Integer userId, Integer opportunityId);
	
	/**
	  * Get a list of all submitted opportunities for an opportunity by id
	  * @param opportunityId
	  * @return List of Opportunity Submitted
	  */
	public List<OpportunitySubmitted> getByOpportunity(Integer opportunityId);
	
}
