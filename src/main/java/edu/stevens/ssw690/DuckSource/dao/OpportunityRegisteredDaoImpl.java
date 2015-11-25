package edu.stevens.ssw690.DuckSource.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
