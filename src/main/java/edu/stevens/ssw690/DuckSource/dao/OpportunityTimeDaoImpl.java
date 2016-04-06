package edu.stevens.ssw690.DuckSource.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.stevens.ssw690.DuckSource.model.OpportunityTime;

/**
 * 
 * @author susan
 * 
 * @see Interface (OpportunityTimeDao)
 *
 */

@Repository
@Transactional
@Component
// Not to be called directly
public class OpportunityTimeDaoImpl implements OpportunityTimeDao {

	@PersistenceContext 
    private EntityManager em;
 
    public void persist(OpportunityTime OpportunityTime) {
        em.persist(OpportunityTime);
    }
    
    public void merge(OpportunityTime OpportunityTime) {
        em.merge(OpportunityTime);
        em.flush();
    }
    
    public void remove(OpportunityTime OpportunityTime) {
        em.remove(em.merge(OpportunityTime));
        em.flush();
    }
    
    public void clearTime(Integer userId, Integer oppId, Date startDate, Date endDate) {
    	Query query = em.createQuery(
    		"DELETE OpportunityTime t where t.user.id = :userId and t.opportunity.id = :oppId and t.workDate BETWEEN :start and :end)");		
    	query.setParameter("userId", userId); 
    	query.setParameter("oppId", oppId);
    	query.setParameter("start", startDate);
    	query.setParameter("end", endDate);
    	query.executeUpdate();
    }
    
    public OpportunityTime findById(Integer id) {
    	return em.find(OpportunityTime.class, id);
	}
    
    public List<OpportunityTime> getByUser(Integer userId) {
    	TypedQuery<OpportunityTime> query = em.createQuery(
    			"FROM OpportunityTime t where t.user.id = :userId)", OpportunityTime.class);
    	query.setParameter("userId", userId); 
    	return query.getResultList();
    }
    
    public List<OpportunityTime> getByOpportunity(Integer userId, Integer oppId) {
    	TypedQuery<OpportunityTime> query = em.createQuery(
    			"FROM  OpportunityTime t where t.user.id = :userId and t.opportunity.id = :oppId)", OpportunityTime.class);
    	query.setParameter("userId", userId); 
    	query.setParameter("oppId", oppId);
    	return query.getResultList();
    	
	 }
    
    public List<OpportunityTime> getByDate(Integer userId, Integer oppId, Date startDate, Date endDate) {
    	TypedQuery<OpportunityTime> query = em.createQuery(
    			"FROM  OpportunityTime r where r.userId = :userId and r.opportunityId = :oppId and r.workDate BETWEEN :start and :end)", OpportunityTime.class);
    	query.setParameter("userId", userId); 
    	query.setParameter("oppId", oppId);
    	query.setParameter("start", startDate);
    	query.setParameter("end", endDate);
    	return query.getResultList();
    	
    	
	 }
}
