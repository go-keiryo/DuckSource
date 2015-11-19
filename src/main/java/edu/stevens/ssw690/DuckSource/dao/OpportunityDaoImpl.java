package edu.stevens.ssw690.DuckSource.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.stevens.ssw690.DuckSource.model.DuckUser;
import edu.stevens.ssw690.DuckSource.model.Opportunity;

@Repository
@Transactional
@Component
public class OpportunityDaoImpl implements OpportunityDao {
	
    @PersistenceContext 
    private EntityManager em;
 
    public void persist(Opportunity opportunity) {
        em.persist(opportunity);
    }
 
    // Retrieves all the Opportunities:
    public List<Opportunity> getAllOpportunities() {
        TypedQuery<Opportunity> query = em.createQuery(
            "SELECT o FROM  Opportunity o ORDER BY o.id",  Opportunity.class);
        return query.getResultList();
    }
    
    // Retrieves the Opportunity:
    public Opportunity getOpportunity(String title) {
    	 Opportunity opp = null;;
    	TypedQuery<Opportunity> query = em.createQuery(
    		"SELECT o FROM  Opportunity o WHERE o.title IS NULL OR LOWER(u.opportunity_title) = LOWER(:title)",  Opportunity.class);
    	query.setParameter("title", title);
    	List<Opportunity> list =  query.getResultList();
    	int size = list.size();
    	if (size > 0)
    		opp = list.get(0);
    	return opp;
    }
    
    public List<Opportunity> getByCreator(Integer creator) {
    
    	TypedQuery<Opportunity> query = em.createQuery(
                "FROM  Opportunity o WHERE o.creatorId=:creator",  Opportunity.class);
    	query.setParameter("creator", creator);  
    	return query.getResultList();
    	
	 }


}