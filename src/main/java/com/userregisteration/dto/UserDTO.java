package com.userregisteration.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserDTO {

	@NotNull
	@NotEmpty
	private String firstname;

	@NotNull
	@NotEmpty
	private String lastname;

	@NotNull
	@NotEmpty
	private String password;

	private String confirmpassword;

	@NotNull
	@NotEmpty
	private String phonenumber;

	@NotNull
	@NotEmpty
	private String emailaddress;

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the confirmpassword
	 */
	public String getConfirmpassword() {
		return confirmpassword;
	}

	/**
	 * @param confirmpassword the confirmpassword to set
	 */
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

	/**
	 * @return the phonenumber
	 */
	public String getPhonenumber() {
		return phonenumber;
	}

	/**
	 * @param phonenumber the phonenumber to set
	 */
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	/**
	 * @return the emailaddress
	 */
	public String getEmailaddress() {
		return emailaddress;
	}

	/**
	 * @param emailaddress the emailaddress to set
	 */
	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	
	/** 
	 * @return String
	 */
	@Override
	public String toString() {
		return "UserDTO [firstname=" + firstname + ", lastname=" + lastname + ", password=" + password
				+ ", confirmpassword=" + confirmpassword + ", phonenumber=" + phonenumber + ", emailaddress="
				+ emailaddress + "]";
	}

}
