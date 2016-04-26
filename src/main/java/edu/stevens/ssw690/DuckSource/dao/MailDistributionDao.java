package edu.stevens.ssw690.DuckSource.dao;

import java.util.List;

import edu.stevens.ssw690.DuckSource.model.MailDistribution;

/**
 * 
 * @author susan
 *
 */
public interface MailDistributionDao  {

	/**
	 * Saves the mail distribution to the database (managed) 
	 * @param mailDistribution
	 */
    public void persist(MailDistribution mailDistribution);
    
    /**
     * Saves the mail distribution to the database (unmanaged)
     * @param mailDistribution
     */
    public void merge(MailDistribution mailDistribution);
    
    /**
     * Gets a list of all Mail Distributions
     * @return List of Mail Distributions
     */
	public List<MailDistribution> getAll();
	
	/**
	 * Gets a list of all mail distribution foe a message by id
	 * @param id
	 * @return List of Mail Distributions
	 */
	public List<MailDistribution> getByMessageId(Integer id);
	
	/**
	 * Get a message distribution by id
	 * @param id
	 * @return Mail Distribution
	 */
	public MailDistribution findById(Integer id);
	
}
