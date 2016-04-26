package edu.stevens.ssw690.DuckSource.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

/**
 * 
 * @author susan
 * 
 *
 */

@Entity
@Table(name = "mail_distribution")
public class MailDistribution implements Serializable {

private static final long serialVersionUID = 1L;
	
	// Constructors:
    public  MailDistribution() {
    }
    
    // Persistent Fields:
    @Id 
    @Column(name="mail_distribution_id")
    @GeneratedValue
    Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mail_message_id", nullable = false)
    private MailMessage mailMessage;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private DuckUser user;
    @Formula("(select mail_message_id from mail_message m where m.mail_message_id = message_id)")
    private Integer messageId;
    @Formula("(select user_id from duck_user d where d.user_id = user_id)")
    private Integer userId;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public MailMessage getMailMessage() {
		return mailMessage;
	}
	public void setMailMessage(MailMessage mailMessage) {
		this.mailMessage = mailMessage;
	}
	public DuckUser getUser() {
		return user;
	}
	public void setUser(DuckUser user) {
		this.user = user;
	}
	public Integer getMessageId() {
		return messageId;
	}
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
    
    
}
