package edu.stevens.ssw690.DuckSource.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.stevens.ssw690.DuckSource.dao.ReviewIssueDao;
import edu.stevens.ssw690.DuckSource.model.ReviewIssue;


@Service
public class ReviewIssueManagerImpl implements ReviewIssueManager {
	
	@Autowired
	ReviewIssueDao dao;

	public void persist(ReviewIssue issue) {
		dao.persist(issue);
		
	}

	public void merge(ReviewIssue issue) {
		dao.merge(issue);
		
	}

	
	public List<ReviewIssue> getAll() {
		return dao.getAll();
	}

	
	public ReviewIssue getById(Integer id) {
		return dao.getById(id);
	}

	public ReviewIssue findById(Integer id) {	
		return dao.findById(id);
	}
}
