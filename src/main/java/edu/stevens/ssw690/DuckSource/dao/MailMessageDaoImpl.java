package edu.stevens.ssw690.DuckSource.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.stevens.ssw690.DuckSource.model.MailMessage;

/**
 * 
 * @author susan
 * @see MailMessageDao
 *
 */
@Repository
@Transactional
@Component
public class MailMessageDaoImpl implements MailMessageDao {
	
	@PersistenceContext 
	private EntityManager em;
	 
    public void persist(MailMessage mailMessage) {
        em.persist(mailMessage);
    }
    
    public void merge(MailMessage mailMessage) {
    	em.merge(mailMessage);
    	em.flush();
    }
    
	public List<MailMessage> getAll() {
	    List<MailMessage> result = em.createQuery("from MailMessage", MailMessage.class).getResultList();
	    return result;
	 }
	
	
	public MailMessage findById(Integer id) {
    	return em.find(MailMessage.class, id);
	}
    

}
