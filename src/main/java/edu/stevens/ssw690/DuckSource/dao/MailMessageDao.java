package edu.stevens.ssw690.DuckSource.dao;

import java.math.BigInteger;
import java.util.List;

import edu.stevens.ssw690.DuckSource.model.MailMessage;

public interface MailMessageDao {

	public void persist(MailMessage mailMessage);
    public void merge(MailMessage mailMessage);
	public List<MailMessage> getAll();
	public MailMessage findById(BigInteger id);
}
