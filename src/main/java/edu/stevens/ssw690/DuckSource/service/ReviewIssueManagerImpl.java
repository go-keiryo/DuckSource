package edu.stevens.ssw690.DuckSource.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.stevens.ssw690.DuckSource.dao.ReviewIssueDao;
import edu.stevens.ssw690.DuckSource.model.ReviewIssue;


@Service
public class ReviewIssueManagerImpl implements ReviewIssueManager {
	
	@Autowired
	ReviewIssueDao reviewIssueDao;

	public void persist(ReviewIssue issue) {
		reviewIssueDao.persist(issue);
		
	}

	public void merge(ReviewIssue issue) {
		reviewIssueDao.merge(issue);
		
	}

	
	public List<ReviewIssue> getAll() {
		return reviewIssueDao.getAll();
	}

	
	public ReviewIssue getById(Integer id) {
		return reviewIssueDao.getById(id);
	}

	public ReviewIssue findById(Integer id) {	
		return reviewIssueDao.findById(id);
	}
}
