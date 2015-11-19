package edu.stevens.ssw690.DuckSource.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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

		// Constructors:
	    public DuckUser() {
	    }
	 
	    public DuckUser(String firstname, String lastname, String email, String username, String pswd) {
	        this.firstName = firstname;
	        this.lastName = lastname;
	        this.emailAddress = email;
	        this.userName = username;
	        this.password = pswd;
	    }
	 
	    // String Representation:
	    @Override
	    public String toString() {
	        return lastName + "," + firstName + ")";
	    }
}

