package edu.stevens.ssw690.DuckSource.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.stevens.ssw690.DuckSource.model.OpportunitySubmitted;

/**
 * 
 * @author susan
 * @see  OpportunitySubmittedDao
 *
 */
@Repository
@Transactional
@Component
public class OpportunitySubmittedDaoImpl implements OpportunitySubmittedDao {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;
 
    public void persist(OpportunitySubmitted opportunitySubmitted) {
        em.persist(opportunitySubmitted);
    }
    
    public void merge(OpportunitySubmitted opportunitySubmitted) {
        em.merge(opportunitySubmitted);
        em.flush();
    }
    
    public OpportunitySubmitted findById(Integer id) {
    	return em.find(OpportunitySubmitted.class, id);
	}
    
    public List<OpportunitySubmitted> getByOpportunity(Integer opportunityId) {
    	TypedQuery<OpportunitySubmitted> query = em.createQuery(
    			"FROM OpportunitySubmitted s where s.opportunityId = :opportunityId)", OpportunitySubmitted.class);
    	query.setParameter("opportunityId", opportunityId);  
    	return query.getResultList();
    	
	 }
    
    public List<OpportunitySubmitted> getBySubmitted(Integer userId) {
    	TypedQuery<OpportunitySubmitted> query = em.createQuery(
    			"Select s FROM OpportunitySubmitted s where s.userId = :userId)", OpportunitySubmitted.class);
    	query.setParameter("userId", userId);  
    	return query.getResultList();
    	
	 }
    
    public OpportunitySubmitted getBySubmittedOpportunity(Integer userId, Integer opportunityId) {
    	OpportunitySubmitted opportunitySubmitted = null;
    	TypedQuery<OpportunitySubmitted> query = em.createQuery(
    			"FROM OpportunitySubmitted s where s.user.id = :userId and s.opportunityId = :opportunityId)", OpportunitySubmitted.class);
    	query.setParameter("userId", userId); 
    	query.setParameter("opportunityId", opportunityId);
    	List<OpportunitySubmitted> list = (List<OpportunitySubmitted>) query.getResultList();
    	if (list.size() > 0)
    		opportunitySubmitted = list.get(0);
    	return opportunitySubmitted;
    	
	 }
}
