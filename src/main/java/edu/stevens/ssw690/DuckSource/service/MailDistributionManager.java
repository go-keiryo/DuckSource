package edu.stevens.ssw690.DuckSource.service;

import java.math.BigInteger;
import java.util.List;

import edu.stevens.ssw690.DuckSource.model.MailDistribution;

public interface MailDistributionManager {
	
	public void persist(MailDistribution mailDistribution);
    public void merge(MailDistribution mailDistribution);
	public List<MailDistribution> getAll();
	public List<MailDistribution> getByMessageId(BigInteger id);
	public MailDistribution findById(BigInteger id);

}
