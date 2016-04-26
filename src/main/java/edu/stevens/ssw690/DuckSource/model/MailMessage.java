package edu.stevens.ssw690.DuckSource.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * 
 * @author susan
 * 
 *
 */

@Entity
@Table(name = "mail_message")
public class MailMessage implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// Constructors:
    public  MailMessage() {
    }
	
	// Persistent Fields:
    @Id
    @Column(name="mail_message_id")
    @GeneratedValue
    Integer id;
    @OneToOne
    @JoinColumn(name="user_id", nullable = false)
    DuckUser user;
    @Column(name="send_to", columnDefinition = "TEXT", length = 65535)
    private String to ;
    @Column(name="subject")
    private String subject;
    @Column(name="body", columnDefinition = "LONGTEXT", length = 65535)
    private String body ;
    @Column(name="sent")
    @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm")
    private Date sent;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mailMessage")
    private Set<MailDistribution> mailDistribution;
    @Formula("(select user_id from duck_user d where d.user_id = user_id)")
    private Integer userId;
    
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Date getSent() {
		return sent;
	}
	public void setSent(Date sent) {
		this.sent = sent;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public DuckUser getUser() {
		return user;
	}
	public void setUser(DuckUser user) {
		this.user = user;
	}
    
   
}
