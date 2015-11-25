package edu.stevens.ssw690.DuckSource.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "opportunity_registered")
public class OpportunityRegistered implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// Constructors:
    public  OpportunityRegistered() {
    }
    
    // Persistent Fields:
    @Id 
    @Column(name="opportunity_registered_id")
    @GeneratedValue
    Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "opportunity_id", nullable = false)
    private Opportunity opportunity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private DuckUser user;
    @Column(name="registered_date")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date registeredDate;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Opportunity getOpportunity() {
		return opportunity;
	}
	public void setOpportunity(Opportunity opportunity) {
		this.opportunity = opportunity;
	}
	public DuckUser getUser() {
		return user;
	}
	public void setUser(DuckUser user) {
		this.user = user;
	}
	public Date getRegisteredDate() {
		return registeredDate;
	}
	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}

    
}
