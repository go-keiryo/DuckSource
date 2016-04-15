package edu.stevens.ssw690.DuckSource.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.stevens.ssw690.DuckSource.model.Mailbox;

@Repository
@Transactional
@Component
public class MailboxDaoImpl implements MailboxDao {

	@PersistenceContext 
	private EntityManager em;
	 
    public void persist(Mailbox mailbox) {
        em.persist(mailbox);
    }
    
    public void merge(Mailbox mailbox) {
    	em.merge(mailbox);
    	em.flush();
    }
    
	public List<Mailbox> getAll() {
	    List<Mailbox> result = em.createQuery("from Mailbox", Mailbox.class).getResultList();
	    return result;
	 }
	
	public List<Mailbox> getByUserId(Integer id) {
    	Query query = em.createQuery("from Mailbox d WHERE d.userId = :id)");
    	query.setParameter("id", id);
		@SuppressWarnings("unchecked")
		List<Mailbox> list = (List<Mailbox>) query.getResultList();
    	return list;
	}
	
	public List<Mailbox> getUserInbox(Integer id) {
    	Query query = em.createQuery("from Mailbox d WHERE d.userId = :id and d.folder = 'Inbox')");
    	query.setParameter("id", id);
		@SuppressWarnings("unchecked")
		List<Mailbox> list = (List<Mailbox>) query.getResultList();
    	return list;
	}
	
	public List<Mailbox> getUserSent(Integer id) {
    	Query query = em.createQuery("from Mailbox d WHERE d.userId = :id and d.folder = 'Sent')");
    	query.setParameter("id", id);
		@SuppressWarnings("unchecked")
		List<Mailbox> list = (List<Mailbox>) query.getResultList();
    	return list;
	}
	
	public Mailbox findById(BigInteger id) {
    	return em.find(Mailbox.class, id);
	}
}
