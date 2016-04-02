package edu.stevens.ssw690.DuckSource.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "duck_user")
public class DuckUser  implements Serializable {

	 private static final long serialVersionUID = 1L;
	    
	    // Persistent Fields:
	    @Id 
	    @Column(name="user_id")
	    @GeneratedValue
	    Integer id;
	    @Column(name="first_name")
	    private String firstName;
	    @Column(name="last_name")
	    private String lastName;
	    @Column(name="email_address")
	    private String emailAddress;
	    @Column(name="user_name")
	    private String userName;
	    @Column(name="password")
	    private String password;
	    @Transient
	    private String confirmPassword;
	    @Transient
	    private String newPassword;
		@Column(name="registration_date")
	    @DateTimeFormat(pattern = "MM/dd/yyyy")
	    private Date registrationDate;
		@Column(name="profile_image")
		private String profileImage;
	    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	    private Set<OpportunityRegistered> opportunitiesRegistered;
	    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	    private Set<OpportunitySubmitted> opportunitiesSubmitted;
	    
	    public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getEmailAddress() {
			return emailAddress;
		}

		public void setEmailAddress(String emailAddress) {
			this.emailAddress = emailAddress;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
		
		@Transient
		public String getNewPassword() {
			return newPassword;
		}

		@Transient
		public void setNewPassword(String newPasswsord) {
			this.newPassword = newPasswsord;
		}
		
		@Transient
		public String getConfirmPassword() {
			return confirmPassword;
		}

		@Transient
		public void setConfirmPassword(String confirmPasswsord) {
			this.confirmPassword = confirmPasswsord;
		}
		
		public Date getRegistrationDate() {
			return registrationDate;
		}

		public void setRegistrationDate(Date registrationDate) {
			this.registrationDate = registrationDate;
		}
		
		public String getProfileImage() {
			return profileImage;
		}
		
		public void setProfileImage(String profileImage) {
			this.profileImage = profileImage;
		}
		 public Set<OpportunityRegistered> getOpportunitiesRegistered() {
				return opportunitiesRegistered;
		}

		public void setOpportunitiesRegistered(Set<OpportunityRegistered> opportunitiesRegistered) {
				this.opportunitiesRegistered = opportunitiesRegistered;
		}

		public Set<OpportunitySubmitted> getOpportunitiesSubmitted() {
				return opportunitiesSubmitted;
		}

		public void setOpportunitiesSubmitted(Set<OpportunitySubmitted> opportunitiesSubmitted) {
				this.opportunitiesSubmitted = opportunitiesSubmitted;
		}

		// Constructors:
	    public DuckUser() {
	    }
	 
	    public DuckUser(String firstname, String lastname, String email, String username, String pswd, Date registrationdate) {
	        this.firstName = firstname;
	        this.lastName = lastname;
	        this.emailAddress = email;
	        this.userName = username;
	        this.password = pswd;
	        this.registrationDate = registrationdate;
	    }
	 
	    // String Representation:
	    @Override
	    public String toString() {
	        return lastName + "," + firstName + ")";
	    }
}

