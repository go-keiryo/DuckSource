package edu.stevens.ssw690.DuckSource.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.stevens.ssw690.DuckSource.model.OpportunityRegistered;

@Repository
@Transactional
@Component
public class OpportunityRegisteredDaoImpl implements OpportunityRegisteredDao{

	@PersistenceContext 
    private EntityManager em;
 
    public void persist(OpportunityRegistered opportunityRegistered) {
        em.persist(opportunityRegistered);
    }
    
    public void merge(OpportunityRegistered opportunityRegistered) {
        em.merge(opportunityRegistered);
        em.flush();
    }
    
    public void remove(OpportunityRegistered opportunityRegistered) {
        em.remove(em.merge(opportunityRegistered));
        em.flush();
    }
    
    public OpportunityRegistered findById(Integer id) {
    	return em.find(OpportunityRegistered.class, id);
	}
    
    public List<OpportunityRegistered> getByRegistered(Integer userId) {
    	TypedQuery<OpportunityRegistered> query = em.createQuery(
    			"FROM OpportunityRegistered r where r.user.id = :userId)", OpportunityRegistered.class);
    	query.setParameter("userId", userId); 
    	return query.getResultList();
    }
    
    public OpportunityRegistered getByRegisteredOpportunity(Integer userId, Integer opportunityId) {
    	OpportunityRegistered opportunityRegistered = null;
    	TypedQuery<OpportunityRegistered> query = em.createQuery(
    			"FROM OpportunityRegistered r where r.user.id = :userId and r.opportunityId = :opportunityId)", OpportunityRegistered.class);
    	query.setParameter("userId", userId); 
    	query.setParameter("opportunityId", opportunityId);
    	List<OpportunityRegistered> list = (List<OpportunityRegistered>) query.getResultList();
    	if (list.size() > 0)
    		opportunityRegistered = list.get(0);
    	return opportunityRegistered;
    	
	 }
}
