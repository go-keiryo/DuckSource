package edu.stevens.ssw690.DuckSource.service;

import java.util.List;

import edu.stevens.ssw690.DuckSource.model.Mailbox;

/**
 * 
 * @author susan
 *
 */
public interface MailboxManager {
	
	/**
	 * Saves a mailbox item to the database (managed)
	 * @param mailbox
	 */
	public void persist(Mailbox mailbox);
	
	/**
	 * Saves a mailbox item to the database (unmanaged)
	 * @param mailbox
	 */
    public void merge(Mailbox mailbox);
    
    /**
     * Gets a list of all mailboxes
     * @return List of mailbox objects
     */
	public List<Mailbox> getAll();
	
	/**
	 * Get the mailbox for a user id
	 * @param id
	 * @return mailbox
	 */
	public List<Mailbox> getByUserId(Integer id);
	
	/**
	 * Gets all mailbox objects for a user id where folder is Inbox
	 * @param id
	 * @return List of mailbox objects
	 */
	public List<Mailbox> getUserInbox(Integer id);
	
	/**
	 * Gets a list of mailbox objects the for the user id  where the folder is Sent
	 * @param id
	 * @return
	 */
	public List<Mailbox> getUserSent(Integer id);
	
	/**
	 * Get a mailbox object by its id
	 * @param id
	 * @return mailbox
	 */
	public Mailbox findById(Integer id);
	
    /**
     * Get a mailbox object by the id of its message
     * @param id
     * @return mailbox
     */
	public Mailbox getByMessageId(Integer id);
	
	/**
	 * delete a mailbox object from the database
	 * @param mailbox
	 */
	public void remove(Mailbox mailbox);
	
	/**
	 * get the number of unread messages by user id
	 * @param id
	 * @return
	 */
	public int getUnreadCount(Integer id);

}
