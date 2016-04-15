package edu.stevens.ssw690.DuckSource.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.stevens.ssw690.DuckSource.dao.MailDistributionDao;
import edu.stevens.ssw690.DuckSource.model.MailDistribution;

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
	
	public List<MailDistribution> getByMessageId(BigInteger id) {
    	return  mailDistributionDao.getByMessageId(id);
	}
	
	public MailDistribution findById(BigInteger id) {
    	return  mailDistributionDao.findById(id);
	}
}
