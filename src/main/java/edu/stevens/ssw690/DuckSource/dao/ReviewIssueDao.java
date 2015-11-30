package edu.stevens.ssw690.DuckSource.dao;

import java.util.List;

import edu.stevens.ssw690.DuckSource.model.ReviewIssue;

public interface ReviewIssueDao {
	
	public void persist(ReviewIssue issue);
	public void merge(ReviewIssue issue);
	public List<ReviewIssue> getAll();
	public ReviewIssue getById(Integer id);
	public ReviewIssue findById(Integer id);

}
