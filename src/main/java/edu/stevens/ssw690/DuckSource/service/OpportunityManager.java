package edu.stevens.ssw690.DuckSource.service;

import java.util.List;

import edu.stevens.ssw690.DuckSource.model.Opportunity;

public interface OpportunityManager {
	
	/**
	 * Saves an opportunity to the database (managed)
	 * @param opportunity
	 */
	public void persist(Opportunity opportunity);
	
	/**
	 * Saves an opportunity to the database (unmanaged)
	 * @param opportunity
	 */
	public void merge(Opportunity opportunity);
	
	/**
     * Gets an opportunity by its id
     * @param id
     * @return Opportunity
     */
	public Opportunity findById(Integer id);
	
	/**
     * Gets an opportunity by its id
     * @param id
     * @return Opportunity
     */
	public Opportunity getById(Integer id);
	
	/**
	 * Gets a list of all opportunities
	 * @return List of all opportunities
	 */
    public List<Opportunity> getAllOpportunities();
    
    /**
     * Gets an opportunity by its title
     * @param title
     * @return Opportunity
     */
    public Opportunity getOpportunity(String title);
    
    /**
     * Gets a list of opportunities by the user id of the user that created it
     * @param creator
     * @return List of Opportunities
     */
    public List<Opportunity> getByCreator(Integer creator);
    
    /**
     * Gets a list of opportunities by the user id that the user registered for
     * @param userId
     * @return List of Opportunities
     */
    public List<Opportunity> getByRegistered(Integer userId);
    
    /**
     * Gets a list of opportunities by the user id that the user submitted
     * @param userId
     * @return List of Opportunities
     */
    public List<Opportunity> getBySubmitted(Integer userId);
    
    /**
     * Gets an opportunity by its id and the user id that submitted it
     * @param userId
     * @param opportunityId
     * @return Opportunity
     */
    public Opportunity getBySubmittedOpportunity(Integer userId, Integer opportunityId);
    
    /**
     * Gets a list of opportunities by type
     * @param oppType
     * @return List of Opportunities
     */
    public List<Opportunity> getByType(String oppType);
    
    /**
     * Gets a list of opportunities the user didn't create by user id
     * @param userId
     * @return List of Opportunities
     */
    public List<Opportunity> getByOtherThanCreator(Integer userId);
    
    /**
     * Gets a list of opportunities the user didn't create by user id by type
     * @param userId
     * @param oppType
     * @return List of Opportunities
     */
    public List<Opportunity> getByOtherThanCreatorByType(Integer userId, String oppType);
    
    /**
     * Gets a list of all opportunities to export to Excel
     * @return List of Opportunities
     */
    public List<Opportunity> getAllOpportunitiesForExcelExport();

}
