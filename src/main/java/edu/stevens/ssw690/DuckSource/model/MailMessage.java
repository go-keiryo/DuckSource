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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

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
    @Column(name="sent_id")
    private Integer sentId;
    @Column(name="subject")
    private String subject;
    @Column(name="body", columnDefinition = "LONGTEXT", length = 65535)
    private String body ;
    @Column(name="sent")
    @DateTimeFormat(pattern = "MM/dd/yyyy hh:mm")
    private Date sent;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mailMessage")
    private Set<MailDistribution> mailDistribution;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSentId() {
		return sentId;
	}
	public void setSentId(Integer sentId) {
		this.sentId = sentId;
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

 
}
