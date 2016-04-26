package edu.stevens.ssw690.DuckSource.service;

import java.util.List;

import edu.stevens.ssw690.DuckSource.model.OpportunityRegistered;

public interface OpportunityRegisteredManager {
	
	/**
	 * Save the registered opportunity to the database (managed)
	 * @param opportunityRegistered
	 */
	public void persist(OpportunityRegistered opportunityRegistered);
	
	/**
	 * Save the registered opportunity to the database (unmanaged)
	 * @param opportunityRegistered
	 */
	public void merge(OpportunityRegistered opportunityRegistered);
	
	/**
	  * Delete the registered opportunity from the database
	  * @param opportunityRegistered
	  */
	public void remove(OpportunityRegistered opportunityRegistered);
	
	/**
	  * Get the registered opportunity by its id
	  * @param id
	  * @return Opportunity Registered
	  */
	public OpportunityRegistered findById(Integer id);
	
	/**
	  * Get a list of all registered opportunities for a user by id
	  * @param userId
	  * @return List of Opportunity Registered
	  */
	public List<OpportunityRegistered> getByRegistered(Integer userId);
	
	/**
	  * Get a registered opportunity by its opportunity id and user id
	  * @param userId
	  * @param opportunityId
	  * @return Opportunity Registered
	  */
	public OpportunityRegistered getByRegisteredOpportunity(Integer userId, Integer opportunityId);
}

