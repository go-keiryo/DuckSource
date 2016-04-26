package edu.stevens.ssw690.DuckSource.service;

import java.util.List;

import edu.stevens.ssw690.DuckSource.model.ReviewIssue;

public interface ReviewIssueManager {
	
	/**
	 * Saves a review issue to the database (managed)
	 * @param issue
	 */
	public void persist(ReviewIssue issue);
	
	/**
	 * Saves a review issue to the database (unmanaged)
	 * @param issue
	 */
	public void merge(ReviewIssue issue);
	
	/**
	 * Gets a list of all review issues
	 * @return List of Review Issues
	 */
	public List<ReviewIssue> getAll();
	
	/**
	 * Gets a review issues by its id
	 * @param id
	 * @return Review Issue
	 */
	public ReviewIssue getById(Integer id);
	
	/**
	 * Gets a review issues by its id
	 * @param id
	 * @return Review Issue
	 */
	public ReviewIssue findById(Integer id);


}
