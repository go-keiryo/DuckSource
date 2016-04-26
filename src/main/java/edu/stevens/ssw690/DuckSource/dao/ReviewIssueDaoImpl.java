package edu.stevens.ssw690.DuckSource.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.stevens.ssw690.DuckSource.model.ReviewIssue;

/**
 * 
 * @author susan
 * @see  ReviewIssueDao
 *
 */
@Repository
@Transactional
@Component
public class ReviewIssueDaoImpl implements  ReviewIssueDao {
	
	@PersistenceContext 
	private EntityManager em;
	
	public void persist(ReviewIssue issue) {
        em.persist(issue);
    }
    
    public void merge(ReviewIssue issue) {
    	em.merge(issue);
    	em.flush();
    }
    
	public List<ReviewIssue> getAll() {
	    List<ReviewIssue> result = em.createQuery("from ReviewIssue", ReviewIssue.class).getResultList();
	    return result;
	}
	
	public ReviewIssue getById(Integer id) {
		ReviewIssue issue = null;
    	Query query = em.createQuery("from ReviewIssue i WHERE i.id = :id)");
    	query.setParameter("id", id);
    	@SuppressWarnings("unchecked")
		List<ReviewIssue> list = (List<ReviewIssue>) query.getResultList();
    	if (list.size() > 0)
    		issue = list.get(0);
    	return issue;
	}
	
	public ReviewIssue findById(Integer id) {
    	return em.find(ReviewIssue.class, id);
	}
    

}
