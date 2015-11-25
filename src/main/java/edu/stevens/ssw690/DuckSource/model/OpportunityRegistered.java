package edu.stevens.ssw690.DuckSource.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;
import org.springframework.format.annotation.DateTimeFormat;

import edu.stevens.ssw690.DuckSource.utilities.DuckUtilities;

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

}
