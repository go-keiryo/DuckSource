package edu.stevens.ssw690.DuckSource.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.stevens.ssw690.DuckSource.model.MailDistribution;

/**
 * 
 * @author susan
 * @see Interface  MailDistributionDao
 *
 */
@Repository
@Transactional
@Component
public class MailDistributionDaoImpl implements MailDistributionDao {

	@PersistenceContext 
	private EntityManager em;
	 
    public void persist(MailDistribution mailDistribution) {
        em.persist(mailDistribution);
    }
    
    public void merge(MailDistribution mailDistribution) {
    	em.merge(mailDistribution);
    	em.flush();
    }
    
	public List<MailDistribution> getAll() {
	    List<MailDistribution> result = em.createQuery("from MailDistribution", MailDistribution.class).getResultList();
	    return result;
	 }
	
	public List<MailDistribution> getByMessageId(Integer id) {
    	Query query = em.createQuery("from MailDistribution d WHERE d.messageId = :id)");
    	query.setParameter("id", id);
		@SuppressWarnings("unchecked")
		List<MailDistribution> list = (List<MailDistribution>) query.getResultList();
    	return list;
	}
	
	public MailDistribution findById(Integer id) {
    	return em.find(MailDistribution.class, id);
	}
	
}
