package edu.stevens.ssw690.DuckSource.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import edu.stevens.ssw690.DuckSource.utilities.DuckUtilities;

@Entity
@Table(name = "opportunity")
public class Opportunity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	 
	// Persistent Fields:
    @Id 
    @Column(name="opportunity_id")
    @GeneratedValue
    Integer id;
    @Column(name="opportunity_type")
    private String opportunityType;
    @Column(name="opportunity_title")
    private String opportunityTitle;
    @Column(name="duck_bills")
    private BigDecimal duckbills;
    @Column(name="register_date")
    private Date registerDate;
    @Column(name="submit_date")
    private Date submitDate;
    @Column(name="description")
    private String description ;
    @Column(name="creator_id")
    private Integer creatorId;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOpportunityType() {
		return opportunityType;
	}
	public void setOpportunityType(String opportunitytype) {
		this.opportunityType = opportunitytype;
	}
	public String getOpportunityTitle() {
		return opportunityTitle;
	}
	public void setOpportunityTitle(String title) {
		this.opportunityTitle = title;
	}
	public BigDecimal getDuckbills() {
			return duckbills;
	}
	public void setDuckbills(BigDecimal duckBills) {
		this.duckbills = duckBills;
	}
	public Date getRegisterDate() {
			return registerDate;
	}
	public void setRegisterDate(Date registerdate) {
		this.registerDate = registerdate;
	}
	public Date getSubmitDate() {
			return submitDate;
	}
	public void setSubmitDate(Date submitdate) {
		this.submitDate = submitdate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String desc) {
		description = desc;
	}
	public Integer getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(Integer creatorid) {
		this.creatorId = creatorid;
	}
	
	// Constructors:
    public  Opportunity() {
    }
    
	public Opportunity(String oppType, String oppTitle, BigDecimal bills, Date registerDate, Date submitDate, String desc, Integer creator) {
	       this.opportunityType = oppType;
	       this.opportunityTitle = oppTitle;
	       this.duckbills = bills;
	       this.registerDate = registerDate;
	       this.submitDate = submitDate;
	       this.description = desc;
	       this.creatorId = creator;
	}
	
	public Opportunity(String oppType, String oppTitle, String bills, String registerDate, String submitDate, String desc, Integer creator) {
	       this.opportunityType = oppType;
	       this.opportunityTitle = oppTitle;
	       this.duckbills = DuckUtilities.getBigDecimalFromString(bills);
	       this.registerDate = DuckUtilities.getDateFromString(registerDate);
	       this.submitDate = DuckUtilities.getDateFromString(submitDate);
	       this.description = desc;
	       this.creatorId = creator;
	}
	 
	 @Override
	 public String toString() {
		 return "Opportunity [id=" + id + ", Type=" + opportunityType
				 + ", title=" + opportunityTitle + ", duckbill$=" + duckbills.toString() + "]";
	 }
	 
}
