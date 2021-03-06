
package com.example.TBA.model;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity(name = "user")
@Table(name = "user_table")
public class UserBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "Email  cannot be empty")
	@Email(message = "Email Format is not valid")
	@Size(min = 3, max = 30, message = "Email can not be empty")
	@Id
	@Column(name="email")
	private String email;
	
	@NotNull(message = "First Name cannot be empty")
	@Size(min = 2, max = 30, message = "First Name cannot be less than 2 characters")
	@Column(name="firstName")
	private String firstName;

	@NotNull(message = "Last Name cannot be empty")
	@Size(min = 2, max = 30, message = "Last Name cannot be less than 2 characters")
	@Column(name="lastName")
	private String lastName;

	private String title;
	private String country;
	private String password;
	
	
	@Transient
	private String passwordConfirm;	
	private String provider;
	private String image;
	
	
	public String getEmail() {
		return email;
	}

	public String getImage() {
	    return image;
	}

	public void setImage(String image) {
	    this.image = image;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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


	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	 

}
