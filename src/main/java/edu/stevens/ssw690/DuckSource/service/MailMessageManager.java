package edu.stevens.ssw690.DuckSource.service;

import java.util.List;

import edu.stevens.ssw690.DuckSource.model.MailMessage;

public interface MailMessageManager {

	/**
	 * Saves the mail message to the database (managed)
	 * @param mailMessage
	 */
	public void persist(MailMessage mailMessage);
	
	/**
	 * Saves the mail message to the database (unmanaged)
	 * @param mailMessage
	 */
    public void merge(MailMessage mailMessage);
    
    /**
     * Gets a list of all mail messages
     * @return List of Mail Messages
     */
	public List<MailMessage> getAll();
	
	/**
	 * Gets a mail message by its id
	 * @param id
	 * @return Mail Message
	 */
	public MailMessage findById(Integer id);
}
