package edu.stevens.ssw690.DuckSource.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    @Column(name="opportunity_id")
    Integer opportunity_id;
    @Column(name="user_id")
    Integer user_id;
    @Column(name="registered_date")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date registeredDate;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOpportunity_id() {
		return opportunity_id;
	}
	public void setOpportunity_id(Integer opportunity_id) {
		this.opportunity_id = opportunity_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Date getRegisteredDate() {
		return registeredDate;
	}
	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}
    
    
}
