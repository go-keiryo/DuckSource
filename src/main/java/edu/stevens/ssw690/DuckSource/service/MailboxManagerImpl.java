package edu.stevens.ssw690.DuckSource.service;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.stevens.ssw690.DuckSource.dao.MailboxDao;
import edu.stevens.ssw690.DuckSource.model.Mailbox;

@Service
public class MailboxManagerImpl implements MailboxManager {

	@Autowired
	MailboxDao mailboxDao;
	
	 	public void persist(Mailbox mailbox) {
	 		mailboxDao.persist(mailbox);
	 	}
	    
	    public void merge(Mailbox mailbox) {
	    	mailboxDao.merge(mailbox);
	    }
	    
		public List<Mailbox> getAll() {
		    return mailboxDao.getAll();
		 }
		
		public List<Mailbox> getByUserId(Integer id) {
	    	return mailboxDao.getByUserId(id);
		}
		
		public List<Mailbox> getUserInbox(Integer id) {
	    	return mailboxDao.getUserInbox(id);
		}
		
		public List<Mailbox> getUserSent(Integer id) {
	    	return mailboxDao.getUserSent(id);
		}
		
		public Mailbox getByMessageId(Integer id) {
	    	return  mailboxDao.getByMessageId(id);
		}
		
		public void remove(Mailbox mailbox) {
			 mailboxDao.remove(mailbox);
		}
		
		public Mailbox findById(Integer id) {
	    	return mailboxDao.findById(id);
		}
		
		public int getUnreadCount(Integer id) {
			return mailboxDao.getUnreadCount(id);
		}
}
