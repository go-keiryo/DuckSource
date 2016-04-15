package edu.stevens.ssw690.DuckSource.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.stevens.ssw690.DuckSource.model.DuckUser;
import edu.stevens.ssw690.DuckSource.model.MailMessage;

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
	
	
	public MailMessage findById(BigInteger id) {
    	return em.find(MailMessage.class, id);
	}
    

}
