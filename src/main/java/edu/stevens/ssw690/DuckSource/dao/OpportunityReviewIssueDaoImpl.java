package edu.stevens.ssw690.DuckSource.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.Query;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.stevens.ssw690.DuckSource.model.Opportunity;
import edu.stevens.ssw690.DuckSource.model.OpportunityRegistered;
import edu.stevens.ssw690.DuckSource.model.OpportunityReviewIssue;
import edu.stevens.ssw690.DuckSource.model.OpportunitytReviewIssueExtended;

@Repository
@Transactional
@Component
public class OpportunityReviewIssueDaoImpl implements OpportunityReviewIssueDao {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;
	
	public void persist(OpportunityReviewIssue opportunityReviewIssue) {
        em.persist(opportunityReviewIssue);
    }
    
    public void merge(OpportunityReviewIssue opportunityReviewIssue) {
        em.merge(opportunityReviewIssue);
        em.flush();
    }
    
    public void remove(OpportunityReviewIssue opportunityReviewIssue) {
        em.remove(em.merge(opportunityReviewIssue));
        em.flush();
    }
    
    public OpportunityReviewIssue findById(Integer id) {
    	return em.find(OpportunityReviewIssue.class, id);
	}
    
    public List<OpportunityReviewIssue> getByOpportunitySubmitted(Integer opportunitySubmittedId) {
    	TypedQuery<OpportunityReviewIssue> query = em.createQuery(
    			"FROM OpportunityReviewIssue r where r.opportunitySubmittedId = :opportunitySubmittedId)", OpportunityReviewIssue.class);
    	query.setParameter("opportunitySubmittedId", opportunitySubmittedId);
    	return query.getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public List<OpportunitytReviewIssueExtended> getByOpportunitySubmittedExtended(Integer opportunitySubmittedId) {
    	Query q = em.createQuery("SELECT NEW edu.stevens.ssw690.DuckSource.model.OpportunitytReviewIssueExtended(r.id, r.creationDate, r.issueId," +
    			" r.comment, r.resolutionDate, (select issueTitle from ReviewIssue i where i.id = r.issueId)) FROM OpportunityReviewIssue r where r.opportunitySubmittedId = :opportunitySubmittedId");
    	q.setParameter("opportunitySubmittedId", opportunitySubmittedId);  
    	List<OpportunitytReviewIssueExtended> orie = q.getResultList();
    	return orie;
	 }
}
