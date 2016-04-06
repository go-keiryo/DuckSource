package edu.stevens.ssw690.DuckSource.model;

import java.io.Serializable;
import java.util.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * @author susan
 * 
 *
 */

@Entity
@Table(name = "opportunity_time")

//Hours worked by user for an opportunity for one time interval in day
public class OpportunityTime implements Serializable {

private static final long serialVersionUID = 1L;
	
	// Constructors:
    public  OpportunityTime() {
    }
    
    // Persistent Fields:
    @Id 
    @Column(name="opportunity_time_id")
    @GeneratedValue
    Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "opportunity_id", nullable = false)
    private Opportunity opportunity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private DuckUser user;
    @Column(name="work_date")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date workDate;
    @Column(name="start_time")
    @DateTimeFormat(pattern = "hh:mm:ss")
    private Time startTime;
	@Column(name="end_time")
    @DateTimeFormat(pattern = "hh:mm:ss")
    private Time endTime;
    @Formula("(select opportunity_id from Opportunity o where o.opportunity_id = opportunity_id)")
    private Integer opportunityId;
    @Formula("(select user_id from duck_user d where d.user_id = user_id)")
    private Integer userId;
    
    public void setOpportunityId(Integer opportunityId) {
		this.opportunityId = opportunityId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

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
	public Integer getOpportunityId() {
		return opportunityId;
	}
	public Integer getUserId() {
		return userId;
	}
	public DuckUser getUser() {
		return user;
	}
	public void setUser(DuckUser user) {
		this.user = user;
	}
	 public Date getWorkDate() {
		return workDate;
	}
	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	
}
