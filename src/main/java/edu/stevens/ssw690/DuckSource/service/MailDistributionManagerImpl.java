package edu.stevens.ssw690.DuckSource.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.stevens.ssw690.DuckSource.dao.MailDistributionDao;
import edu.stevens.ssw690.DuckSource.model.MailDistribution;

/**
 * 
 * @author susan
 * @see Interface  MailDistributionManager
 *
 */
@Service
public class MailDistributionManagerImpl implements MailDistributionManager {

	@Autowired
	MailDistributionDao mailDistributionDao;
	
	public void persist(MailDistribution mailDistribution) {
		 mailDistributionDao.persist(mailDistribution);
    }
    
    public void merge(MailDistribution mailDistribution) {
    	 mailDistributionDao.merge(mailDistribution);
    }
    
	public List<MailDistribution> getAll() {
	    return  mailDistributionDao.getAll();
	 }
	
	public List<MailDistribution> getByMessageId(Integer id) {
    	return  mailDistributionDao.getByMessageId(id);
	}
	
	public MailDistribution findById(Integer id) {
    	return  mailDistributionDao.findById(id);
	}
}
