package edu.stevens.ssw690.DuckSource.service;

import java.math.BigInteger;
import java.util.List;

import edu.stevens.ssw690.DuckSource.model.Mailbox;

public interface MailboxManager {
	
	public void persist(Mailbox mailbox);
    public void merge(Mailbox mailbox);
	public List<Mailbox> getAll();
	public List<Mailbox> getByUserId(Integer id);
	public List<Mailbox> getUserInbox(Integer id);
	public List<Mailbox> getUserSent(Integer id);
	public Mailbox findById(BigInteger id);
	public Mailbox getByMessageId(Integer id);
	public void remove(Mailbox mailbox);
	public int getUnreadCount(Integer id);

}
