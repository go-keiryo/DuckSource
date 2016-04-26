package edu.stevens.ssw690.DuckSource.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.stevens.ssw690.DuckSource.dao.MailMessageDao;
import edu.stevens.ssw690.DuckSource.model.MailMessage;

/**
 * 
 * @author susan
 * @see MailMessageManager
 *
 */
@Service
public class MailMessageManagerImpl implements MailMessageManager{

	@Autowired
	MailMessageDao mailMessageDao;
	
	public void persist(MailMessage mailMessage) {
       mailMessageDao.persist(mailMessage);
    }
    
    public void merge(MailMessage mailMessage) {
    	mailMessageDao.merge(mailMessage);
    }
    
	public List<MailMessage> getAll() {
	    return mailMessageDao.getAll();
	 }
	
	public MailMessage findById(Integer id) {
    	return mailMessageDao.findById(id);
	}
}
