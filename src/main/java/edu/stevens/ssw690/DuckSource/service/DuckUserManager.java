package edu.stevens.ssw690.DuckSource.service;

import java.util.List;

import edu.stevens.ssw690.DuckSource.model.DuckUser;
/**
 * 
 * @author susan
 *
 */
public interface DuckUserManager {

	/**
	 * Gets a list of all users 
	 * @return List of users
	 */
	public List<DuckUser> getAll();
	
	/**
     * Saves the user to the database (ummanaged)
     * @param user
     */
	public void merge(DuckUser user); 
	
	/**
	 * Gets the user object with id
	 * @param id
	 * @return user
	 */
    public DuckUser findById(Integer id);
    
    /**
	 * Gets the user object with id
	 * @param id
	 * @return user
	 */
    public DuckUser getById(Integer id);
    
    /**
     * Saves the user to the database (managed)
     * @param user
     */
    public void persist(DuckUser user);
    
    /**
     * Gets a user by username and password
     * @param username
     * @param password
     * @return user
     */
    public DuckUser getDuckUser(String username, String password);
    
    /**
     * Gets a user by username
     * @param username
     * @return user
     */
    public DuckUser getDuckUser(String username);
    
    /**
     * Determines if username exists in database
     * @param username
     * @return true/false
     */
    public boolean getUsernameExists(String username);
    
    /**
     * Gets a list of all usernames in database
     * @return List of usernames
     */
    public List<String> getAllUserNames();
}
